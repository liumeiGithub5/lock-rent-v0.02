package com.yifushidai.api;

import com.yifushidai.annotation.IgnoreAuth;
import com.yifushidai.annotation.UserHolder;
import com.yifushidai.service.TokenService;
import com.yifushidai.service.UserService;
import com.yifushidai.service.XingeTokenService;
import com.yifushidai.utils.ErrorCodeConstant;
import com.yifushidai.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by liumei on 2017/8/17 15:11.
 * desc:用户相关接口
 */
@RestController
@Api(description = "用户相关接口",tags = "UserController")
@RequestMapping("/api/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private XingeTokenService xingeService;

    @IgnoreAuth
    //@AppTypeAuth
    @PostMapping("/login")
    @ApiOperation(value = "登录接口",notes = "登录接口(忽略token)",produces = MediaType.APPLICATION_JSON_VALUE)
    public R login(@ApiParam("电话号")@RequestParam String mobile,
                   @ApiParam("短信验证码")@RequestParam String checkCode,
                   @ApiParam(value = "XingeToken") @RequestParam String xingeToken,
                   @ApiParam(value = "app类型(ios,android)") @RequestParam String appType) throws Exception {
        int result = userService.login(mobile,checkCode,appType);
        if(result == ErrorCodeConstant.TESTACCOUNTWRONG){
            return R.error(ErrorCodeConstant.TESTACCOUNTWRONG,"测试账号错误");
        }
        if(result == ErrorCodeConstant.SUCCESS){
            logger.info(mobile+",登录成功");
            //生成token
            Map<String, Object> map = tokenService.createToken(mobile);
            //处理信鸽
            xingeService.handleXinge(mobile,xingeToken);
            return R.ok().put("datas",map);
        }
        return R.error(ErrorCodeConstant.CHECK_CODE_WRONG,"验证码错误");
    }

    @PostMapping("/modMobile")
    @ApiOperation(value = "修改手机号接口",notes = "修改手机号",produces = MediaType.APPLICATION_JSON_VALUE)
    public R modPwd(@ApiParam("旧手机号")@RequestParam String oldMobile,@ApiParam("新手机号")@RequestParam String newMobile)  {
        String mb = UserHolder.get().getMobile();
        if(mb.equalsIgnoreCase(oldMobile) ){
            int result = userService.modMobile(oldMobile,newMobile);
            if(result == ErrorCodeConstant.SUCCESS ){
                logger.info(oldMobile+",修改手机号成功");
                return R.ok();
            }else {
                return R.error();
            }
        }
        return R.error(ErrorCodeConstant.DATA_NULL,"原手机号不存在");
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出接口",notes = "退出",produces = MediaType.APPLICATION_JSON_VALUE)
    public R logout(){
        UserHolder.remove();
        logger.info("退出登录");
        return R.ok();
    }
}
