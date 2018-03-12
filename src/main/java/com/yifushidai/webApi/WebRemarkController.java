package com.yifushidai.webApi;

import com.yifushidai.entity.RemarkEntity;
import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.utils.PageUtils;
import com.yifushidai.utils.Query;
import com.yifushidai.utils.R;
import com.yifushidai.webService.WebRemarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by liumei on 2017/11/16 10:43.
 * desc:
 */
@RestController
@Api(description = "留言相关接口",tags = "WebRemarkController")
@RequestMapping("/webapi/remark")
public class WebRemarkController {
    private Logger logger = LoggerFactory.getLogger(WebRemarkController.class);

    @Autowired
    private WebRemarkService remarkService;

    @GetMapping("/queryRemark")
    @ApiOperation(value = "查询所有待处理留言/按条件查询接口",notes = "查询所有待处理留言/按条件查询",produces = MediaType.APPLICATION_JSON_VALUE)
    public R queryRemark(@RequestParam Map<String, Object> params, HttpSession session)  {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        Query query = new Query(params);
        List<RemarkEntity> remarkList = remarkService.queryRemark(query);
        int total = remarkService.queryRemarkTotal(query);
        PageUtils pageUtil = new PageUtils(remarkList, total, query.getLimit(), query.getPage());
        //logger.info("管理员："+loginUser.getUsername()+"，查询留言");
        return R.ok().put("page",pageUtil);

    }

    //处理，标记留言
    @PostMapping("/handleRemark")
    @ApiOperation(value = "处理留言接口",notes = "处理留言",produces = MediaType.APPLICATION_JSON_VALUE)
    public R handleRemark(@RequestBody Long[] ids , HttpSession session)  {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        Boolean flag = remarkService.handleRemark(ids);
        if(flag ){
            logger.info("管理员："+loginUser.getUsername()+"，处理留言");
            return R.ok();
        }
        return R.error();
    }

}
