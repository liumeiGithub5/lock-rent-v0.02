package com.yifushidai.api;

import com.yifushidai.annotation.UserHolder;
import com.yifushidai.dtoAndvo.ICVo;
import com.yifushidai.service.ICService;
import com.yifushidai.utils.PageUtils;
import com.yifushidai.utils.Query;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liumei on 2017/10/31 14:17.
 * desc:
 */
@RestController
@Api(description = "ic卡相关接口",tags = "ICController")
@RequestMapping("/api/IC")
public class ICController {
    private Logger logger = LoggerFactory.getLogger(ICController.class);

    @Autowired
    private ICService icService;

    //ic卡查询
    @PostMapping("/queryIc")
    @ApiOperation(value = "ic卡查询接口",notes = "ic卡查询",produces = MediaType.APPLICATION_JSON_VALUE)
    public R queryIc(@ApiParam("锁mac")@RequestParam String mac,
                     @ApiParam("起始页")@RequestParam int pageIndex,
                     @ApiParam("页大小")@RequestParam int pageSize)  {
        String mb = UserHolder.get().getMobile();
        Map<String ,Object> params = new LinkedHashMap<>();
        params.put("page",pageIndex);
        params.put("limit",pageSize);
        params.put("offset", (pageIndex - 1) * pageSize);
        params.put("mac", mac);
        Query query = new Query(params);
        List<ICVo> icvos = icService.queryIc(query);
        int total = icService.queryIcTotal(query);
        PageUtils pageUtil = new PageUtils(icvos, total, query.getLimit(), query.getPage());
        logger.info(mb +",ic卡查询");
        return R.ok().put("datas",pageUtil);
    }

    //客户ic补卡请求 (暂时没有 ，直接打电话，然后插数据库)
    @PostMapping("/updateIc")
    @ApiOperation(value = "客户ic补卡请求接口",notes = "客户ic补卡请求",produces = MediaType.APPLICATION_JSON_VALUE)
    public R updateIc(@ApiParam("锁mac")@RequestParam String mac,
                      @ApiParam("ic卡名称")@RequestParam String name) throws ParseException {
        String mb = UserHolder.get().getMobile();
        Boolean flag = icService.updateIc(mac,name);
        if(flag){
            logger.info(mb +",手机端ic补卡请求");
            return R.ok();
        }
        return R.error();
    }


}
