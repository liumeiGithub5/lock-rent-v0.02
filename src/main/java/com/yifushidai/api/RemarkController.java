package com.yifushidai.api;

import com.yifushidai.annotation.UserHolder;
import com.yifushidai.service.RemarkService;
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
 * Created by liumei on 2017/11/16 10:32.
 * desc: 留言相关
 */
@RestController
@Api(description = "留言相关接口",tags = "RemarkController")
@RequestMapping("/api/remark")
public class RemarkController {
    private Logger logger = LoggerFactory.getLogger(RemarkController.class);

    @Autowired
    private RemarkService remarkService;

    @PostMapping("/upremark")
    @ApiOperation(value = "上传留言接口",notes = "上传留言",produces = MediaType.APPLICATION_JSON_VALUE)
    public R upremark(@ApiParam("留言内容")@RequestParam String remarkStr) throws ParseException {
        String mb = UserHolder.get().getMobile();
        boolean flag = false;
        flag = remarkService.upremark(mb,remarkStr);
        if(flag ){
            logger.info(mb+",上传留言成功");
            return R.ok();
        }
        return R.error();

    }
}
