package com.yifushidai.api;

import com.yifushidai.annotation.UserHolder;
import com.yifushidai.entity.BandLockEntity;
import com.yifushidai.entity.KeypassBandEntity;
import com.yifushidai.service.LockService;
import com.yifushidai.utils.ErrorCodeConstant;
import com.yifushidai.utils.PageUtils2;
import com.yifushidai.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by liumei on 2017/8/17 16:14.
 * desc:锁相关接口
 */
@RestController
@Api(description = "锁相关接口（包含省电列表）",tags = "LockController")
@RequestMapping("/api/lock")
public class LockController {
    private Logger logger = LoggerFactory.getLogger(LockController.class);

    @Autowired
    private LockService lockService;

    @ApiOperation(value = "上传绑定锁接口", notes = "上传绑定锁（扫码绑定）",produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/band")
    public R band(@ApiParam(value = "锁编号", required = true)@RequestParam Long lockId,
                  @ApiParam(value = "锁别名", required = true)@RequestParam String alias,
                  @ApiParam(value = "锁地址", required = true)@RequestParam String addr,
                  @ApiParam(value = "电费单价", required = true)@RequestParam Double eprice,
                  @ApiParam(value = "家电功率", required = true)@RequestParam Double pow,
                  @ApiParam(value = "蓝牙信息", required = true)@RequestParam String blueteeth,//存储蓝牙信息
                  @ApiParam(value = "门锁图片文件流",required = false) @RequestBody MultipartFile upLoadFile,
                  HttpServletRequest request) throws ParseException, IOException {
        String mb= UserHolder.get().getMobile();
        int result = lockService.band(mb,lockId,alias,addr,eprice,pow,blueteeth,upLoadFile,request);
        if(result == 0){
            logger.info(mb+",成功绑定锁");
            return R.ok();
        }else if(result == 508){
            return R.error(ErrorCodeConstant.DUPLICATE,"数据已存在");
        }else{
            return R.error();
        }
    }

    @PostMapping("/getBlueTeeth")
    @ApiOperation(value = "获取锁蓝牙信息接口",notes = "获取蓝牙信息",produces = MediaType.APPLICATION_JSON_VALUE)
    public R getBlueTeeth(@ApiParam(value = "锁编号", required = true)@RequestParam Long lockId){
        String mb = UserHolder.get().getMobile();
        String blueteeth = lockService.getBlueTeeth(lockId);
        logger.info(mb+",成功获取主锁列表/省电列表");
        return R.ok().put("datas",blueteeth);
    }

    @PostMapping("/modImg")
    @ApiOperation(value = "修改图片接口",notes = "修改图片",produces = MediaType.APPLICATION_JSON_VALUE)
    public R modImg(@ApiParam(value = "头像文件流",required = true) @RequestBody MultipartFile upLoadFile,
                       @ApiParam(value = "锁编号", required = true)@RequestParam Long lockId,
                       HttpServletRequest request) throws IOException {
        String mb= UserHolder.get().getMobile();
        Boolean flag = lockService.modImg(upLoadFile, lockId, request);
        if(flag){
            logger.info("手机号："+mb+",成功修改图片");
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除门锁接口",notes = "删除门锁",produces = MediaType.APPLICATION_JSON_VALUE)
    public R del(@ApiParam(value = "锁编号", required = true)@RequestParam Long lockId) throws IOException {
        String mb= UserHolder.get().getMobile();
        Boolean flag = lockService.del(lockId);
        if(flag){
            logger.info("手机号："+mb+",成功删除门锁");
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/getMainLocks")
    @ApiOperation(value = "获取锁列表/省电列表接口",notes = "获取当前用户主锁列表(锁列表+省电列表)",produces = MediaType.APPLICATION_JSON_VALUE)
    public R getMainLocks(){
        String mb = UserHolder.get().getMobile();
        List<BandLockEntity> mainLocks = lockService.getMainLocks(mb);
        PageUtils2 pageUtils2 = new PageUtils2(mainLocks,mainLocks.size());
        logger.info(mb+",成功获取主锁列表/省电列表");
        return R.ok().put("datas",pageUtils2);
    }
}
