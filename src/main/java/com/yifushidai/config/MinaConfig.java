package com.yifushidai.config;


import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liumei on 2017/6/17 19:40.
 * desc:mina 配置
 *
 */
@Configuration
public class MinaConfig {
    @Bean
    public LoggingFilter loggingFilter() {
        LoggingFilter loggingFilter = new LoggingFilter();
//        loggingFilter.setSessionClosedLogLevel(LogLevel.NONE);
//        loggingFilter.setSessionCreatedLogLevel(LogLevel.NONE);
//        loggingFilter.setSessionOpenedLogLevel(LogLevel.INFO);
        loggingFilter.setSessionIdleLogLevel(LogLevel.NONE);
        return loggingFilter;
    }
    @Bean
    public IoHandler ioHandler() {
        return new TimeServerHandler();
    }
    @Bean
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(10088);
    }

    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        IoAcceptor acceptor=new NioSocketAcceptor(10);
        //设置过滤器链  添加log
        acceptor.getFilterChain().addLast( "logger", loggingFilter() );
        acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
        acceptor.setHandler(ioHandler());//ioHandler() //指定业务逻辑处理器
        acceptor.getSessionConfig().setReadBufferSize(2048); //设置读缓冲区大小
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 30 );//心跳监测   设置了，此处设置失效 单位s？
        //设置心跳协议 部分
        KeepAlive ka = new KeepAlive();
        KeepAliveFilter kaf = new KeepAliveFilter(ka,IdleStatus.BOTH_IDLE);//,IdleStatus.BOTH_IDLE //, KeepAliveRequestTimeoutHandler.CLOSE
        kaf.setForwardEvent(true);//设置是否forward到下一个filter //不设置默认false //idle事件回发  当session进入idle状态的时候 依然调用handler中的idled方法// 说明：尤其 注意该句话，使用了 KeepAliveFilter之后，IoHandlerAdapter中的 sessionIdle方法默认是不会再被调用的！ 所以必须加入这句话 sessionIdle才会被调用
        kaf.setRequestInterval(30);//心跳频率，不设置则默认60s//设置进入Idle状态的时间，单位为s
        //说明：设置心跳包请求时间间隔，其实对于被动型的心跳机制来说，设置心跳包请求间隔貌似是没有用的，因为它是不会发送心跳包的，
        // 但是它会触发 sessionIdle事件， 我们利用该方法，可以来判断客户端是否在该时间间隔内没有发心跳包，一旦 sessionIdle方法被调用，则认为 客户端丢失连接并将其踢出 。
        // 因此其中参数 heartPeriod其实就是服务器对于客户端的IDLE监控时间。
        //客户端会定时发送心跳请求（注意定时时间必须小于，服务器端的IDLE监控时间）,同时需要监听心跳反馈
        //kaf.setRequestTimeoutHandler(new KeepAliveTimeOutServerHandler());
        //kaf.setRequestTimeout(60);//心跳超时时间，不设置则默认30s//超时时间 如果当前发出一个心跳请求后需要反馈  若反馈超过此事件 默认则关闭连接
        acceptor.getFilterChain().addLast("heart",kaf);
        //设置自定义编解码
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyCodeFactory()));
        // acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));//编码过滤器，使得服务器可以处理string类型的消息
        // 启动服务
        acceptor.bind(inetSocketAddress());
        //System.out.println("服务器在端口："+"已经启动");
        return acceptor;
    }
}

/**
 * 心跳信息工厂类,采用的是被动心跳机制
 */
class KeepAlive implements KeepAliveMessageFactory{
    Logger logger = LoggerFactory.getLogger(KeepAlive.class);
    //客户端心跳请求信息的标志
    private static final String HEART_REQUEST = "0001";
    //返回给客户端的心跳反馈信息的标志
    private static final String HEART_RESPONSE = "0002";


    @Override//判断是否心跳请求包  是的话返回true
    public boolean isRequest(IoSession session, Object o) {
        String  msg = o.toString();
        if(o.equals(HEART_REQUEST)){
            logger.info("Object---------" + o);
            return true;
        }
        if(msg.contains("30 30 30 31")  ){//"client\n"  //HeapBuffer[pos=0 lim=6 cap=512: 30 30 30 31 0D 0A]
            logger.info("心跳请求---------" + msg);
            return true;
        }
        return false;
    }

    @Override//由于被动型心跳机制，没有请求当然也就不关注反馈 因此直接返回false
    public boolean isResponse(IoSession session, Object message) {
        return false;
    }

    @Override//被动型心跳机制无请求  因此直接返回null
    public String getRequest(IoSession session) {
        return null;
    }

    @Override //根据心跳请求request 反回一个心跳反馈消息 non-nul
    public String getResponse(IoSession session, Object request) {
        //心跳回应信息
        session.write(HEART_RESPONSE);
        return null;
    }
}

/**
 * 自定义编解码类
 * 此处使用了mina自带的TextLineEncoder编解码器，此解码器支持使用固定长度或者固定分隔符来区分上下两条消息。如果要使用自定义协议，则需要自己编写解码器
 */
class MyCodeFactory implements ProtocolCodecFactory {

    private final TextLineEncoder encoder;
    private final TextLineDecoder decoder;

    public MyCodeFactory() {
        this(Charset.forName("utf-8"));
    }
    public MyCodeFactory(Charset charset) {
        encoder = new TextLineEncoder(charset, LineDelimiter.UNIX);
        decoder = new TextLineDecoder(charset, LineDelimiter.AUTO);
    }

    public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return decoder;
    }
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        return encoder;
    }

}


