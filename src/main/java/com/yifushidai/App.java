package com.yifushidai;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

/**
 * Created by liumei on 2017/4/8.
 * desc: 启动类
 */
@SpringBootApplication
@EnableTransactionManagement//声明式事务
@MapperScan(basePackages = "com.yifushidai.mapper")
//@ServletComponentScan//webCongig 配置扫描
//webCongig 配置扫描
@ComponentScan
public class App extends SpringBootServletInitializer{

    public static void main(String[] args){
        SpringApplication.run(App.class);
    }

    /*实现SpringBootServletInitializer可以让spring-boot项目在外部web容器中运行*/
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false);//errorPage
        builder.sources(this.getClass());
        return super.configure(builder);
    }
    /* 内嵌：实现http 自动转到 https (可选择不自动跳转)*/
    //使用外部tomcat时，直接修改外部tomcat 的配置文件,不需要代码设置http自动跳转https
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                //SecurityConstraint必须存在，可以通过其为不同的URL设置不同的重定向策略。
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                //collection.addPattern("/api/**");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        tomcat.setSessionTimeout(10, TimeUnit.MINUTES);
//        return tomcat;
//    }
//    private Connector initiateHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        //Connector监听的http的端口号
//        connector.setScheme("http");//https
//        connector.setPort(8081);//内嵌默认8080  这里设置为8081  避免与服务器上tomcat端口占用
//        connector.setRedirectPort(8444);// 默认8443
//        connector.setSecure(false);
//        return connector;
//    }

//    /* 设置session 过期时间*/
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setSessionTimeout(3600);//单位为S
//            }
//        };
//    }


}
