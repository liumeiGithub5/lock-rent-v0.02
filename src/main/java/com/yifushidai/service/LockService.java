package com.yifushidai.service;

import com.yifushidai.entity.BandLockEntity;
import com.yifushidai.entity.KeypassBandEntity;
import com.yifushidai.mapper.*;
import com.yifushidai.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by liumei on 2017/8/24 11:23.
 * desc:
 */
@Service
public class LockService {
    @Autowired
    private BandLockEntityMapper lockMapper;
    @Autowired
    private LockIdMacEntityMapper lockIdMacMapper;
    @Autowired
    private KeypassBandEntityMapper keyPassMapper;
    @Autowired
    private YearsDetailEntityMapper yearsMapper;
    @Autowired
    private MonthsDetailEntityMapper monthMapper;
    @Autowired
    private WeeksDetailEntityMapper weeksMapper;
    @Autowired
    private QuartersDetailEntityMapper quartersMapper;
    @Autowired
    private OpenRecordEntityMapper openMapper;
    @Autowired
    private SaveETEntityMapper saveEEMapper;
    @Autowired
    private PowerEntityMapper powerMapper;


    public List<BandLockEntity> getMainLocks(String mb) {
        return lockMapper.getMainLocks(mb);
    }

    public int band(String mb, Long lockId, String alias, String addr, Double eprice, Double pow,String blueteeth,MultipartFile upLoadFile, HttpServletRequest request) throws ParseException, IOException {
        //对应mac
        String mac = lockIdMacMapper.queryByLockId(lockId);
        //是否已绑定过
        BandLockEntity bandLockEntity = lockMapper.queryByMac(mac);
        if(bandLockEntity != null){
            return 508;
        }
        //如果上传图片文件
        String imgUrl = "";
        if(upLoadFile != null){
            imgUrl = uploadImg(upLoadFile,request);
        }
        //绑定锁
        BandLockEntity bandLock = new BandLockEntity();
        bandLock.setMac(mac);
        bandLock.setLockId(lockId);
        bandLock.setAlias(alias);
        bandLock.setMobile(mb);
        bandLock.setAddr(addr);
        bandLock.setBluetooth(blueteeth);
        bandLock.setPow(pow);
        bandLock.setEprice(eprice);
        bandLock.setImg(imgUrl);
        int  i = lockMapper.insertSelective(bandLock);
        //绑定初始密码
        KeypassBandEntity keypassEntity = new KeypassBandEntity();
        keypassEntity.setMac(mac);
        int j = keyPassMapper.insertSelective(keypassEntity);
        if(i>0 && j>0){
          return 0;
        }else{
            return 500;
        }
    }

    public String uploadImg(MultipartFile upLoadFile, HttpServletRequest request) throws IOException {
        String[] arr = upLoadFile.getOriginalFilename().split("\\.");
        String filename = arr[0] + UUIDUtils.creatUUID() +"." + arr[1];
        String path = request.getSession().getServletContext().getRealPath("fileStore"+ File.separator+"imgDirectory");
        //logger.info("-----path------"+path);
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";//获取的服务器地址
        String basePath2 = "https" + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";//获取的服务器地址
        //logger.info("-----basePath------"+basePath);
        //logger.info("-----basePath2------"+basePath2);
        File file = new File(path,filename);
        upLoadFile.transferTo(file);
        String imgUrl = basePath + "fileStore/imgDirectory/" + filename;
        String imgUrl2 = basePath2 + "fileStore/imgDirectory/" + filename;
        //logger.info("-----imgUrl------"+imgUrl2);
        return imgUrl2;
    }

    public Boolean modImg(MultipartFile upLoadFile, Long lockId, HttpServletRequest request) throws IOException {
        String imgUrl = uploadImg(upLoadFile,  request);
        //logger.info("mod  imgUrl :"+imgUrl);
        BandLockEntity lock = lockMapper.selectByPrimaryKey(lockId);
        if(lock == null ){
            //logger.info("select lock null" );
            return false;
        }
       // logger.info("select lock " + lock.toString());
        lock.setImg(imgUrl);
       // logger.info("select lock after " + lock.toString());
        return  lockMapper.updateByPrimaryKeySelective(lock) > 0;
    }

    public Boolean del(Long lockId) {
        //门锁相关信息所有都要删除 含有mac 和 lockid 的表，除了静态表不删
        String mac = lockMapper.queryByLockId(lockId);
        keyPassMapper.deleteByMac(mac);
        openMapper.deleteByMac(mac);
        powerMapper.deleteByMac(mac);
        yearsMapper.deleteByMac(mac);
        quartersMapper.deleteByMac(mac);
        monthMapper.deleteByMac(mac);
        weeksMapper.deleteByMac(mac);
        saveEEMapper.deleteByMac(mac);
        return lockMapper.deleteByLockId(lockId) > 0;
    }

    public String getBlueTeeth(Long lockId) {
        return lockMapper.getBlueTeeth(lockId);
    }

// 100把锁  ID mac 随机生成
//
//    @Test
//    public void  test(){
////        System.out.println("uuid:---------"+UUIDUtils.creatUUID());
////        System.out.println("len:---------"+UUIDUtils.creatUUID().length());
////        System.out.println("shortUuid:---------"+UUIDUtils.generateShortUuid());
////        System.out.println("shortLen:---------"+UUIDUtils.generateShortUuid().length());
//        for(int i = 1; i<= 100; i++){
//            System.out.println(UUIDUtils.generateShortUuid());
//        }
//
//    }


}
