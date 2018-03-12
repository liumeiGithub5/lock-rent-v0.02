package com.yifushidai.webApi;

import com.yifushidai.entity.ICEntity;
import com.yifushidai.entity.LockIdMacEntity;
import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.utils.R;
import com.yifushidai.webService.WebLockService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liumei on 2017/11/1 14:52.
 * desc:门锁id-mac 添加  excel 形式
 */
@RestController
@Api(description = "门锁id-mac-ic相关接口",tags = "WebLockController")
@RequestMapping("/webapi/lock")
public class WebLockController {
    private Logger logger = LoggerFactory.getLogger(WebLockController.class);
    @Autowired
    private WebLockService weblockService;


    //上传excel 续增 锁初始 id-mac 匹配信息
    @PostMapping("/uploadIdMacExcel")
    @ApiOperation(value = "上传id-mac匹配信息接口",notes = "上传id-mac匹配信息",produces = MediaType.APPLICATION_JSON_VALUE)
    public R uploadIdMacExcel(@RequestParam("file") MultipartFile upLoadFile, HttpServletRequest request, HttpSession session) throws IOException {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        InputStream is = upLoadFile.getInputStream();
        LockIdMacEntity lockIdMacEntity = new LockIdMacEntity();
        Boolean flag = weblockService.readIdMacExcel(lockIdMacEntity, upLoadFile, is,loginUser);//LockIdMacEntity  lockIdMacEntity,MultipartFile upLoadFile,InputStream is
        if(flag){
            logger.info("管理员："+loginUser.getUsername()+"上传id-mac匹配信息");
            return R.ok();
        }
        return R.error();
    }

    //上传excel ic初始信息 id-mac-ic 匹配信息
    @PostMapping("/uploadIdMacIcExcel")
    @ApiOperation(value = "上传id-mac-ic匹配信息接口",notes = "上传id-mac-ic匹配信息",produces = MediaType.APPLICATION_JSON_VALUE)
    public R uploadIdMacIcExcel(  @ApiParam(value = "file") @RequestBody MultipartFile upLoadFile, HttpSession session) throws IOException {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        InputStream is = upLoadFile.getInputStream();
        ICEntity icEntity = new ICEntity();
        Boolean flag = weblockService.readIdMacIcExcel(icEntity, upLoadFile, is,loginUser);
        if(flag ){
            logger.info("管理员："+loginUser.getUsername()+"上传id-mac-ic匹配信息");
            return R.ok();
        }
        return R.error();
    }
   /* @GetMapping("/getHistory")
    @ApiOperation(value = "获取id-mac/id-mac-ic上传记录接口",notes = "获取id-mac/id-mac-ic上传记录",produces = MediaType.APPLICATION_JSON_VALUE)
    public R getIdMacHistory(@RequestParam Map<String, Object> params, HttpSession session, byte type) throws IOException {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        params.put("type",type);
        Query query = new Query(params);
        List<UploadHistoryEntity> historyList = weblockService.getIdMacHistory(query);
        int total = weblockService.getIdMacHistoryTotal(query);
        PageUtils pageUtil = new PageUtils(historyList, total, query.getLimit(), query.getPage());
        logger.info("管理员："+loginUser.getUsername()+"上传id-mac-ic匹配信息");
        return R.ok().put("page",pageUtil);
    }

    @GetMapping("/getIdMacHistory")
    @ApiOperation(value = "获取id-mac/id-mac-ic上传记录接口",notes = "获取id-mac/id-mac-ic上传记录",produces = MediaType.APPLICATION_JSON_VALUE)
    public R getIdMacHistory(@RequestParam Map<String, Object> params, HttpSession session, byte type) throws IOException {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        params.put("type",type);
        Query query = new Query(params);
        List<UploadHistoryEntity> historyList = weblockService.getIdMacHistory(query);
        int total = weblockService.getIdMacHistoryTotal(query);
        PageUtils pageUtil = new PageUtils(historyList, total, query.getLimit(), query.getPage());
        logger.info("管理员："+loginUser.getUsername()+"上传id-mac-ic匹配信息");
        return R.ok().put("page",pageUtil);
    }
*/


    //下载id-mac匹配信息excel文件模板
    @PostMapping("/downloadIdMacExcel")
    @ApiOperation(value = "下载id-mac匹配信息excel文件模板接口",notes = "下载id-mac匹配信息excel文件模板",produces = MediaType.APPLICATION_JSON_VALUE)
    public R downloadIdMacExcel(  @ApiParam(value = "门锁图片文件流") @RequestBody MultipartFile upLoadFile, HttpSession session)  {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        Boolean flag = true;
        if(flag ){
            logger.info("管理员："+loginUser.getUsername()+"下载id-mac匹配信息excel文件模板");
            return R.ok();
        }
        return R.error();
    }

    //下载id-mac-ic匹配信息excel文件模板
    @PostMapping("/downloadIdMacIcExcel")
    @ApiOperation(value = "下载id-mac-ic匹配信息excel文件模板接口",notes = "下载id-mac-ic匹配信息excel文件模板",produces = MediaType.APPLICATION_JSON_VALUE)
    public R downloadIdMacIcExcel(  @ApiParam(value = "门锁图片文件流") @RequestBody MultipartFile upLoadFile, HttpSession session)  {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        Boolean flag = true;
        if(flag ){
            logger.info("管理员："+loginUser.getUsername()+"下载id-mac-ic匹配信息excel文件模板");
            return R.ok();
        }
        return R.error();
    }

}
