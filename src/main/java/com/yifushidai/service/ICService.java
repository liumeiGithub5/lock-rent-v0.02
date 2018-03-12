package com.yifushidai.service;

import com.yifushidai.dtoAndvo.ICVo;
import com.yifushidai.entity.ICEntity;
import com.yifushidai.mapper.ICEntityMapper;
import com.yifushidai.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liumei on 2017/10/31 15:32.
 * desc:
 */
@Service
public class ICService {
    @Autowired
    private ICEntityMapper icMapper;

    public List<ICVo> queryIc(Map<String ,Object> map) {
        List<ICEntity> ics = icMapper.queryIc(map);
        List<ICVo> icvos = new ArrayList<>();
        if(ics == null){
            return null;
        }
        for(ICEntity ic:ics){
            ICVo icvo = new ICVo();
            BeanUtils.copyProperties(ic,icvo);
            icvos.add(icvo);
        }
        return icvos;
    }

    public int queryIcTotal(Map<String ,Object> map) {
        return icMapper.queryIcTotal(map);
    }

    public Boolean updateIc(String mac, String name) throws ParseException {
        ICEntity icEntity = icMapper.queryByMacAndName(mac, name);
        icEntity.setOutno("");
        icEntity.setInno("");
        icEntity.setUpdatetime(DateUtils.getCurrent());
        icEntity.setIcStatus((byte) 0);
        return icMapper.updateByPrimaryKeySelective(icEntity)>0;
    }
}
