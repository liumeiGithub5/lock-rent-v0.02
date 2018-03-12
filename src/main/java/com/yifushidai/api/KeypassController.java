package com.yifushidai.api;

import com.yifushidai.annotation.UserHolder;
import com.yifushidai.service.KeyPassService;
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

import java.text.ParseException;

/**
 * Created by liumei on 2017/8/17 16:15.
 * desc:按键密码开门相关
 */
@RestController
@Api(description = "按键密码相关接口",tags = "KeypassController")
@RequestMapping("/api/keyPass")
public class KeypassController {
    private Logger logger = LoggerFactory.getLogger(KeypassController.class);

    @Autowired
    private KeyPassService keyPassService;
    @PostMapping("/modKey")
    @ApiOperation(value = "修改固定密码接口",notes = "修改固定密码",produces = MediaType.APPLICATION_JSON_VALUE)
    public R modKey(@ApiParam("锁mac")@RequestParam String mac,
                    @ApiParam("按键密码")@RequestParam String newKey) throws ParseException {
        String mb = UserHolder.get().getMobile();
        int result = keyPassService.modKey(mac,newKey);
        if(result == ErrorCodeConstant.SUCCESS ){
            logger.info(mb +",修改固定密码");
            return R.ok();
        }
        else if(result == ErrorCodeConstant.ONLINE_ERROR){
            return R.error(ErrorCodeConstant.ONLINE_ERROR,"门锁已掉线");
        }
        else {
            return R.error();
        }
    }

    @ApiOperation(value = "获取临时密码接口", notes = "获取临时密码",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/getTempKey")
    public R getTempKey(@ApiParam(value = "锁mac", required = true)@RequestParam String mac){
        String mb= UserHolder.get().getMobile();
        String tempKey = keyPassService.getTempKey(mac);
        if(tempKey.equalsIgnoreCase("513")){
            return R.error(ErrorCodeConstant.ONLINE_ERROR,"门锁已掉线");
        }
        else {
            logger.info(mb +",请求临时密码");
            return R.ok().put("datas",tempKey);
        }
    }

   /* @PostMapping("/keypassUpdate")
    @ApiOperation(value = "绑定或更新固定按键密码接口",notes = "绑定或更新  按键密码",produces = MediaType.APPLICATION_JSON_VALUE)
    public R keypassUpdate(@ApiParam("锁mac")@RequestParam String mac,
                           @ApiParam("按键密码")@RequestParam String newKeypass) throws ParseException {
        String mb = UserHolder.get().getMobile();
        boolean flag = keyPassService.keypassUpdate(mac,newKeypass);
        if(flag ){
            logger.info(mb +",更新固定密码");
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "请求锁临时密码接口", notes = "请求锁临时密码",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/getTempKey")
    public R getTempKey(@ApiParam(value = "锁mac", required = true)@RequestParam String mac){
        String mb= UserHolder.get().getMobile();
        String tempKey = keyPassService.getTempKey(mac);
        if(tempKey != null){
            logger.info(mb +",请求临时密码,"+tempKey);
            return R.ok().put("datas",tempKey);
        }
        return R.error();
    }*/
}
