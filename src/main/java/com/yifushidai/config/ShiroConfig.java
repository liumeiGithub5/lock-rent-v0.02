//package com.yifushidai.config;
//
//import com.yifushidai.shiro.UserRealm;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import java.util.LinkedHashMap;
//
///**
// * Created by liumei on 2017/6/16 11:59.
// * desc:shiro 相关配置
// */
//@Configuration
//public class ShiroConfig {
//    //管理生命周期
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    //配置自定义的权限登录器
//    @Bean(name = "userRealm")
//    public UserRealm userRealm() {
//        UserRealm realm = new UserRealm();
//        //realm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return realm;
//    }
//
//    //配置核心安全事务管理器SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类
//    @Bean(name = "securityManager")
//    public SecurityManager securityManager() {
//        System.err.println("--------------shiro已经加载----------------");
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        manager.setRealm(userRealm());//注入
//       // manager.setCacheManager(ehCacheManager());//注入缓存管理器;
//        manager.setSessionManager(sessionManager());
//        return manager;
//    }
//
//    public DefaultWebSessionManager sessionManager(){
//        System.err.println("--------------sessionManager已开始----------------");
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        sessionManager.setGlobalSessionTimeout(3600000);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        return sessionManager;
//    }
//
//    /*开启缓存 避免每次查询数据库*/
////    @Bean(name = "ehCacheManager")
////    public EhCacheManager ehCacheManager() {
////        EhCacheManager ehCacheManager = new EhCacheManager();
////        return ehCacheManager;
////    }
//    //shiroFilter factorybean，为了生成ShiroFilter。它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
//    @Bean
//    @DependsOn("securityManager")
//    public ShiroFilterFactoryBean shiroFilter() {
//        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        bean.setSecurityManager(securityManager());
//        //配置登录的url和登录成功的url
//        bean.setLoginUrl("/login.html");
//        bean.setSuccessUrl("/main.html");
//        bean.setUnauthorizedUrl("/");// /403
//        //配置访问权限
//        // 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/api/**", "anon"); //表示可以匿名访问
//        filterChainDefinitionMap.put("/login.html", "anon");
//        //filterChainDefinitionMap.put("/main.html", "anon");
//        filterChainDefinitionMap.put("/register.html", "anon");
//        filterChainDefinitionMap.put("/statics/**", "anon");
//        filterChainDefinitionMap.put("/captcha.jpg", "anon");
//        filterChainDefinitionMap.put("/sys/login", "anon");
//        filterChainDefinitionMap.put("/sys/register", "anon");
//        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
//       // filterChainDefinitionMap.put("/*.*", "authc");
//        //filterChainDefinitionMap.put("/admin/**", "authc,roles[admin]");
//        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return bean;
//    }
//
//    //AOP式方法级权限检查
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager());
//        return advisor;
//    }
//
//    //方言
////    @Bean(name = "shiroDialect")
////    public ShiroDialect shiroDialect(){
////        return new ShiroDialect();
////    }
//
//    //HashedCredentialsMatcher，这个类是为了对密码进行编码的，防止密码在数据库里明码保存，当然在登陆认证的生活，这个类也负责对form里输入的密码进行编码。
////    @Bean(name = "hashedCredentialsMatcher")
////    public HashedCredentialsMatcher hashedCredentialsMatcher() {
////        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
////        credentialsMatcher.setHashAlgorithmName("sha256");
////        credentialsMatcher.setHashIterations(1);
////        credentialsMatcher.setStoredCredentialsHexEncoded(true);
////        return credentialsMatcher;
////    }
//}