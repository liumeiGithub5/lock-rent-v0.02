package com.yifushidai.service;

import com.yifushidai.entity.XingeTokenEntity;
import com.yifushidai.mapper.XingeTokenEntityMapper;
import com.yifushidai.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by liumei on 2017/5/8 20:11.
 * desc: 信鸽token
 */
@Service
public class XingeTokenService {
    @Autowired
    private XingeTokenEntityMapper xingeMapper;

    /**
     * 处理信鸽 token
     * @param mb
     * @param xingeToken
     */
    public void handleXinge(String mb, String xingeToken) throws ParseException {
        if(mb.equalsIgnoreCase("18628107232")){
            return ;
        }
        XingeTokenEntity xingeTokenEntity = xingeMapper.selectByPrimaryKey(mb);
        if(xingeTokenEntity == null){
            //插入xingeToken
            xingeTokenEntity = new XingeTokenEntity();
            xingeTokenEntity.setToken(xingeToken);
            xingeTokenEntity.setMobile(mb);
            xingeTokenEntity.setUpdateTime(DateUtils.getCurrent());
            xingeMapper.insertSelective(xingeTokenEntity);
            return;
        }
        if(! (xingeTokenEntity.getToken().equals(xingeToken))){
                //刷新 xingeToken
                xingeTokenEntity.setToken(xingeToken);
                xingeTokenEntity.setUpdateTime(DateUtils.getCurrent());
                xingeMapper.updateByPrimaryKeySelective(xingeTokenEntity);
        }

    }



}
