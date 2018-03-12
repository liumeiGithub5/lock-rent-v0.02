package com.yifushidai.webApi;

import com.yifushidai.entity.ICEntity;
import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.utils.PageUtils;
import com.yifushidai.utils.Query;
import com.yifushidai.utils.R;
import com.yifushidai.webService.WebICService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by liumei on 2017/10/31 14:17.
 * desc:
 */
@RestController
@Api(description = "ic卡相关接口",tags = "WebICController")
@RequestMapping("/webapi/IC")
public class WebICController {
    private Logger logger = LoggerFactory.getLogger(WebICController.class);

    @Autowired
    private WebICService webIcService;

    //后台客服 ic补卡
    @PostMapping("/icUpdate")
    @ApiOperation(value = "ic补卡接口",notes = "ic补卡",produces = MediaType.APPLICATION_JSON_VALUE)
    public R icUpdate(//@ApiParam("锁mac")@RequestParam String mac,
                      @ApiParam("ic卡名称")@RequestParam String name,
                      @ApiParam("ic外卡号")@RequestParam String outNo,
                      @ApiParam("ic编号")@RequestParam Long icId,
                      HttpSession session)  {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        //Boolean flag = webIcService.icUpdate(mac,name,outNo);
        Boolean flag = webIcService.icUpdate2(icId,name,outNo);
        if(flag ){
            logger.info("管理员："+loginUser.getUsername()+"补办ic卡");
            return R.ok();
        }
        return R.error();
    }

    //查询待补的门锁-ic 信息
    @GetMapping("/queryNeedUp")
    @ApiOperation(value = "查询待补ic卡接口",notes = "查询待补ic卡",produces = MediaType.APPLICATION_JSON_VALUE)
    public R queryNeedUp(@RequestParam Map<String, Object> params, HttpSession session)  {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute("loginUser");
        Query query = new Query(params);
        List<ICEntity> ics = webIcService.queryNeedUp(query);
        int total = webIcService.queryNeedUpTotal(query);
        PageUtils pageUtil = new PageUtils(ics, total, query.getLimit(), query.getPage());
        logger.info("管理员："+loginUser.getUsername()+"查询待补ic");
        return R.ok().put("page",pageUtil);
    }

    //上传续增初始IC卡信息
//上传最新apk 文件 到服务器 （与用户无关）
//    @PostMapping("/updateApp")
//    @ApiOperation(value = "上传最新apk 文件",produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public R updateApp(HttpServletRequest request, @RequestParam("file") MultipartFile upLoadFile) throws IOException {
//        String filename = dissUpdateService.uploadApk(upLoadFile, request);
//        if (filename != null) {
//            DiffSingleMapUtil.getInstance().put(filename);
//            return R.ok();
//        }
//        return R.error();
//    }
//
//    /**
//     * 上传最新 apk
//     * @param upLoadFile
//     * @param request
//     * @return
//     * @throws IOException
//     */
//    public String uploadApk(MultipartFile upLoadFile, HttpServletRequest request) throws IOException {
//        String[] arr = upLoadFile.getOriginalFilename().split("\\.(?=[^\\.]+$)");
//        String filename = upLoadFile.getOriginalFilename();
//        String path = request.getSession().getServletContext().getRealPath("fileStore"+ File.separator+"dissapkDirectory");
//        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";//获取的服务器地址
//        File appFile = new File(path,filename);
//        upLoadFile.transferTo(appFile);
//        String version = arr[0];
//        String  apkUrl = basePath + "dissapkDirectory/" + filename;
//
//        DissApkEntity apk = new DissApkEntity();
//        apk.setId(null);
//        apk.setUrl(apkUrl);
//        apk.setVersion(version);
//        apk.setUpdate(null);
//        int i = dissMapper.insertSelective(apk);
//
//        if(i>0){
//            //推送所有安卓设备，有可用更新
//            String content ="更新至"+version;
//            String title = "有可用更新";
//            XinGeUtils.pushAllDevice(content,title);
//            return filename;
//        }
//        return null;
//    }
}
