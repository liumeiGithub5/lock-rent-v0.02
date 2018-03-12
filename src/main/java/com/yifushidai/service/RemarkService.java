package com.yifushidai.service;

import com.yifushidai.entity.RemarkEntity;
import com.yifushidai.mapper.RemarkEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by liumei on 2017/11/16 10:34.
 * desc:
 */
@Service
public class RemarkService {
    @Autowired
    private RemarkEntityMapper remarkMapper;

    public boolean upremark(String mb, String remarkStr) throws ParseException {
        return remarkMapper.insertSelective(new RemarkEntity(mb,remarkStr)) > 0;
    }
}
