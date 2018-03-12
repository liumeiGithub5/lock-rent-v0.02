package com.yifushidai.config;

import com.yifushidai.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumei on 2017/6/10 18:24.
 * desc:登陆注册权限验证
 */
@WebFilter(filterName = "myFilter",urlPatterns = {"/webApi/v1/*","/sys/**"})//    /
public class MyWebFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(MyWebFilter.class);
    private List<String> freePathList = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==>Filter过滤器启动");
//        freePathList.add("/webApi/v1/manager/login");
//        freePathList.add("/webApi/v1/manager/resetpassword");
//        freePathList.add("/webApi/v1/manager/register");
//        freePathList.add("/webApi/v1/manager/captcha.jpg");
        freePathList.add("/index");
        freePathList.add("/test");
        freePathList.add("/login.html");
        freePathList.add("/register.html");
        freePathList.add("/sys/login");
        freePathList.add("/sys/register");
        freePathList.add("/statics/**");
        freePathList.add("/captcha.jpg");
        freePathList.add("/");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("==>Filter拦截请求");
        //解决中文乱码
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        //过滤操作
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        SysUserEntity loginUser = (SysUserEntity) req.getSession().getAttribute("loginUser");
        String servletPath=req.getServletPath();
        //servletPath:需要放行的路径
        if(loginUser!=null || checkPath(servletPath)){
            /*放行*/
            chain.doFilter(request, response);
        }else{
            resp.sendRedirect("/index");
        }
    }

    private boolean checkPath(String servletPath) {
        for(String path : freePathList){
            if(path.trim().equals(servletPath)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        logger.info("destroy");
    }
}