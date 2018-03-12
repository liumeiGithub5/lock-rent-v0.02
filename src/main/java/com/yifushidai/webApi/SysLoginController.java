package com.yifushidai.webApi;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.utils.MD5;
import com.yifushidai.utils.R;
import com.yifushidai.webService.SysUserEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by liumei on 2017/6/27 17:27.
 * desc:登录注册相关
 */
@Controller
public class SysLoginController {
    private Logger logger = LoggerFactory.getLogger(SysLoginController.class);
    @Autowired
    private Producer producer;
    @Autowired
    private SysUserEntityService sysUserService;
//    /*注册*/
//    @ResponseBody
//    @PostMapping("/sys/register")
//    public R register(String username, String password,String mobile)throws IOException {//,String picked
//        boolean flag = sysUserService.register(username,password,mobile);//,picked
//        if(flag){
//            return R.ok();
//        }else{
//            return R.error();
//        }
//    }
    /*图片验证码*/
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpSession session)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
   /* 登录*/
   @ResponseBody
   @PostMapping("/sys/login")
   public R login(@RequestParam String username, @RequestParam String password, @RequestParam String captcha, HttpSession session)throws IOException {
       String text = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
       if(!captcha.equalsIgnoreCase(text)){
           return R.error("验证码不正确");
       }
       SysUserEntity loginUser = sysUserService.queryByusernameAndPwd(username, MD5.getMd5(password));
       if(loginUser == null){
          return R.error("用户名或密码错误");
       }
       if((loginUser != null) && (loginUser.getStatus()==0)){
           return R.error("该用户已被禁用");
       }
       session.setAttribute("loginUser",loginUser);
       return R.ok();
   }
   /* 退出*/
   @RequestMapping(value = "logout", method = RequestMethod.GET)
   public String logout(HttpSession session) {
       session.removeAttribute("loginUser");//redirect:login.html
       return "redirect:login.html";
   }
}
