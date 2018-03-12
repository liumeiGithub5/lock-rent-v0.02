package com.yifushidai.annotation;

import com.yifushidai.entity.TokenEntity;
import com.yifushidai.service.TokenService;
import com.yifushidai.service.UserService;
import com.yifushidai.utils.ErrorCodeConstant;
import com.yifushidai.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证 + 异常注解验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IgnoreAuth annotation;
        //AppTypeAuth annotation2;

        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
            //annotation2 = ((HandlerMethod) handler).getMethodAnnotation(AppTypeAuth.class);
        }else{
            return true;
        }

        /*//万能测试账号18628107232
        String mobile = request.getParameter("mobile");
        if(mobile.equalsIgnoreCase("18628107232")){
            UserInfo userInfo = new UserInfo(mobile,token);
            UserHolder.put(userInfo);
            return true;
        }*/

        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null){
            return true;
        }

        //从header中 或从参数 获取 token
        String token = StringUtils.isBlank(request.getHeader("token"))?request.getParameter("token"):request.getHeader("token");
        //token为空
        if(StringUtils.isBlank(token)){
            throw new RRException("token不能为空", ErrorCodeConstant.TOKEN_NULL);
        }
        //验证token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new RRException("token失效，请重新登录",ErrorCodeConstant.TOKEN_EXPIRE);
        }

        //设置userId到request里，后续根据userId，获取用户信息
        // request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());
        //TODO 再存一个Threadlocal副本,在当前线程执行流的任意地方都可以使用拿到当前用户信息，也可以不用参数解析来获取数据用户数据了，
        // TODO 测试在getUserInfo之前是使用的@LoginUser来获取的user对象，现在可以直接用UserHolder.get().getUserId()来获取userId

        //String appType = userService.getAppType(tokenEntity.getMobile());
        //System.out.println("auth  appType   ------------------"+appType);
        //UserInfo userInfo = new UserInfo(tokenEntity.getMobile(),token,appType);
        UserInfo userInfo = new UserInfo(tokenEntity.getMobile(),token);
        UserHolder.put(userInfo);
        return true;
    }
}
