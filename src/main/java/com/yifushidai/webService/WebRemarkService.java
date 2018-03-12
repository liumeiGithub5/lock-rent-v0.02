package com.yifushidai.webService;

import com.yifushidai.entity.RemarkEntity;
import com.yifushidai.mapper.RemarkEntityMapper;
import com.yifushidai.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liumei on 2017/11/16 10:45.
 * desc:
 */
@Service
public class WebRemarkService {
    @Autowired
    private RemarkEntityMapper remarkMapper;


    public List<RemarkEntity> queryRemark(Query query) {
        return remarkMapper.queryRemark(query);
    }

    public int queryRemarkTotal(Query query) {
        return remarkMapper.queryRemarkTotal(query);
    }

    public Boolean handleRemark(Long[] ids) {
        return remarkMapper.handleRemark(ids) > 0;
    }
}
