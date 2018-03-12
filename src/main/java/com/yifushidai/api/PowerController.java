package com.yifushidai.api;

import com.yifushidai.annotation.UserHolder;
import com.yifushidai.service.*;
import com.yifushidai.utils.*;
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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liumei on 2017/9/28 15:25.
 * desc: (来断电记录  以及 节约用电时间查询  省电列表)
 */
@RestController
@Api(description = "省电详情相关接口",tags = "PowerController")
@RequestMapping("/api/power")
public class PowerController {
    private Logger logger = LoggerFactory.getLogger(PowerController.class);

    @Autowired
    private PowerService powerService;

    @PostMapping("/querySaveDetails")
    @ApiOperation(value = "省电详情接口",notes = "省电详情",produces = MediaType.APPLICATION_JSON_VALUE)
    public R querySaveDetails(@ApiParam("锁mac")@RequestParam String mac,
                              @ApiParam("查询类型 1：周 2：月 3：季度 4：年")@RequestParam int qtype,
                              @ApiParam("起始页")@RequestParam int pageIndex,
                              @ApiParam("页大小")@RequestParam int pageSize) throws ParseException {
        String mb = UserHolder.get().getMobile();
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("page",pageIndex);
        params.put("limit",pageSize);
        params.put("offset", (pageIndex - 1) * pageSize);
        params.put("mac",mac);
        Query query = new Query(params);
        PageUtils pageUtils = powerService.queryDetails(qtype,query);
        logger.info(mb +",查询省电详情记录");
        return R.ok().put("datas", pageUtils);
    }

}
