package com.yifushidai.api;

import com.yifushidai.annotation.UserHolder;
import com.yifushidai.service.OpenService;
import com.yifushidai.utils.PageUtils;
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
 * Created by liumei on 2017/8/17 16:13.
 * desc:开门记录相关
 */
@RestController
@Api(description = "开门相关接口",tags = "OpenController")
@RequestMapping("/api/open")
public class OpenController {
    private Logger logger = LoggerFactory.getLogger(OpenController.class);

    @Autowired
    private OpenService openService;

    @PostMapping("/queryOpenRecords")
    @ApiOperation(value = "查询开锁记录接口",notes = "查询开锁记录",produces = MediaType.APPLICATION_JSON_VALUE)
    public R queryOpenRecords(@ApiParam("锁mac")@RequestParam String mac,
                              @ApiParam("开始时间")@RequestParam String startTime,
                              @ApiParam("结束时间")@RequestParam String endTime,
                              @ApiParam("起始页")@RequestParam int pageIndex,
                              @ApiParam("页大小")@RequestParam int pageSize,
                              @ApiParam("上页最后一条记录日期(pageIndex=1)时，lastdate= 1")@RequestParam String lastdate) throws ParseException {
        String mb = UserHolder.get().getMobile();
        PageUtils pageUtils = openService.queryOpenRecords(mac,startTime,endTime,pageIndex,pageSize,lastdate);
        logger.info(mb+",成功获取开门记录");
        return R.ok().put("datas",pageUtils);
    }

}
