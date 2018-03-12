package com.yifushidai.config;

import com.yifushidai.annotation.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义注解配置 intercepter+resolver(这是token拦截器 )
 * Created by hai on 2017/4/8.
 */
@Configuration
public class IntercepterConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizationInterceptor interceptor;
//    @Autowired
//    private LoginUserHandlerMethodArgumentResolver argumentResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TODO 改成对应拦截路径
        registry.addInterceptor(interceptor).addPathPatterns("/api/**");
        super.addInterceptors(registry);
    }
    /**
     * 注入参数解析器，覆盖度的是WebMvcConfigurerAdapter中的方法
     * @param argumentResolvers
     */
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(argumentResolver);
//        super.addArgumentResolvers(argumentResolvers);
//    }
}
