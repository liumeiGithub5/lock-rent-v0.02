package com.yifushidai.webService;

import com.yifushidai.entity.ICEntity;
import com.yifushidai.mapper.ICEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by liumei on 2017/10/31 16:49.
 * desc:
 */
@Service
public class WebICService {
    @Autowired
    private ICEntityMapper icMapper;

    public Boolean icUpdate(String mac, String name, String outNo) {
        ICEntity icEntity = icMapper.queryByMacAndName(mac,name);
        icEntity.setOutno(outNo);
        icEntity.setIcStatus((byte) 0);
        return icMapper.updateByPrimaryKeySelective(icEntity) > 0;
    }

    public Boolean icUpdate2(Long icId, String name, String outNo) {
        ICEntity icEntity = icMapper.selectByPrimaryKey(icId);
        icEntity.setOutno(outNo);
        icEntity.setIcName(name);
        icEntity.setIcStatus((byte) 0);
        return icMapper.updateByPrimaryKeySelective(icEntity)>0;
    }

    public List<ICEntity> queryNeedUp(Map<String ,Object> map) {
        return icMapper.queryNeedUp(map);
    }

    public int queryNeedUpTotal(Map<String ,Object> map) {
        return icMapper.queryNeedUpTotal(map);
    }


}
