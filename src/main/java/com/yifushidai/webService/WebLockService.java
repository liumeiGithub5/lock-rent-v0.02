package com.yifushidai.webService;

import com.yifushidai.entity.ICEntity;
import com.yifushidai.entity.LockIdMacEntity;
import com.yifushidai.entity.SysUserEntity;
import com.yifushidai.entity.UploadHistoryEntity;
import com.yifushidai.mapper.ICEntityMapper;
import com.yifushidai.mapper.LockIdMacEntityMapper;
import com.yifushidai.mapper.UploadHistoryEntityMapper;
import com.yifushidai.utils.ExcelUtils;
import com.yifushidai.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liumei on 2017/11/30 15:09.
 * desc:
 */
@Service
public class WebLockService {
    @Autowired
    private LockIdMacEntityMapper idMacMapper;
    @Autowired
    private ICEntityMapper icMapper;
    @Autowired
    private UploadHistoryEntityMapper historyMapper;

    public Boolean  readIdMacExcel(LockIdMacEntity lockIdMacEntity, MultipartFile upLoadFile, InputStream is,SysUserEntity loginUser) {
        Boolean isexcel = ExcelUtils.isExcel(upLoadFile);
        //System.out.println("----isexcel----"+isexcel);
        if(!isexcel){
            return false;
        }
        List<LockIdMacEntity> list = new ArrayList<>();
        List<Object> objects = ExcelUtils.importDataFromExcel(lockIdMacEntity, is, upLoadFile);
        for(Object vo:objects){
           // System.out.println("----vo----"+vo.toString());
            list.add((LockIdMacEntity) vo);
        }
        //插入对应上传历史信息
        if(list.size() != 0 ){
            saveHistory((byte) 0,list,loginUser);
        }
        return idMacMapper.insertlist(list)>0;
    }

    public Boolean readIdMacIcExcel(ICEntity icEntity, MultipartFile upLoadFile, InputStream is,SysUserEntity loginUser) {
        Boolean isexcel = ExcelUtils.isExcel(upLoadFile);
        if(!isexcel){
            return false;
        }
        List<ICEntity> list = new ArrayList<>();
        List<Object> objects = ExcelUtils.importDataFromExcel(icEntity, is, upLoadFile);
        for(Object vo:objects){
            list.add((ICEntity) vo);
        }
        //插入对应上传历史信息
        if(list.size() != 0 ){
            saveHistory((byte) 1,list,loginUser);
        }
        return icMapper.insertlist(list)>0;
    }

    public void saveHistory(Byte type,List<?> list,SysUserEntity loginUser){
        int length = list.size();
        int i=0;
        Long[] ids = new Long[length];

            for(Object ob:list){
                if(type == 0){
                    ids[i] = ((LockIdMacEntity)ob).getLockId();
                }
                if(type == 1){
                    ids[i] = ((ICEntity)ob).getIcId();
                }
                i++;
            }
        Long[] idscopy = Arrays.copyOf(ids,ids.length);
        Arrays.sort(idscopy);
        String numRange = idscopy[0]+"-"+idscopy[length-1];
        System.out.println("numRange:"+numRange);
        UploadHistoryEntity history = new UploadHistoryEntity(loginUser.getUsername(),length,numRange, type); //上传类型 0：锁id_mac   1:锁Id_mac_ic
        historyMapper.insertSelective(history);
    }

    public List<UploadHistoryEntity> getHistory(Query query) {
        return historyMapper.query(query);
    }

    public int getHistoryTotal(Query query) {
        return historyMapper.queryTotal(query);
    }
}
