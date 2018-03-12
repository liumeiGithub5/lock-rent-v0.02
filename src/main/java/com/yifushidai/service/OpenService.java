package com.yifushidai.service;

import com.yifushidai.dtoAndvo.OpenVo;
import com.yifushidai.entity.OpenRecordEntity;
import com.yifushidai.mapper.OpenRecordEntityMapper;
import com.yifushidai.utils.CalendarUtils;
import com.yifushidai.utils.DateUtils;
import com.yifushidai.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by liumei on 2017/8/25 14:24.
 * desc:开门记录相关
 */
@Service
public class OpenService {
    @Autowired
    private OpenRecordEntityMapper openMapper;

    public PageUtils queryOpenRecords(String mac, String startTime, String endTime, int pageIndex, int pageSize,String lastdate) throws ParseException {
        List<OpenVo> openList = new ArrayList<>();
        String eTime = DateUtils.format(DateUtils.toDate(endTime));
       // System.out.println("lastdate ini-- "+lastdate);
        String startQuery = getstartQuery(startTime,pageIndex,pageSize,lastdate);//"yyyy-MM-dd"
        String todayStr = startQuery;
        String curDate = DateUtils.format(DateUtils.getCurrent());
        int[] evedayCount = openMapper.queryEveDaycount(mac,startTime,CalendarUtils.dayaddOrSub(eTime,1));
        //System.out.println("evedayCount array---" + Arrays.toString(evedayCount) );
        int totalCount = evedayCount.length;
        //System.out.println("totalCount---" + totalCount );
        for(int i = 0 ;i < pageSize ; i++){
            //判断是否可提前结束查找
            todayStr = DateUtils.format(DateUtils.toDate2(CalendarUtils.dayaddOrSub(todayStr, 1)));//"yyyy-MM-dd"
            //System.out.println("todayStr 开始:"+todayStr);
            List<OpenRecordEntity> todayOpenList = openMapper.queryByCurday(mac,todayStr);
            //忽略空值  直到有数据  从有数据开始  分页
            if(todayOpenList != null && todayOpenList.size()!=0){
                //System.out.println("todayStr:"+todayStr);
                //System.out.println("todayOpenList for:"+todayOpenList.size());
                OpenVo openVo = new OpenVo(todayStr,todayOpenList);
                openList.add(openVo);
               // System.out.println("openVo  for"+openVo.toString());
                lastdate = DateUtils.format(todayOpenList.get(0).getOpenTime(),"yyyy-MM-dd HH:mm:ss");
                //System.out.println("lastdate  for--------"+lastdate);
            }else{
                i= i-1;
            }
            //累加到当前日 或者 超过结束日期 就不循环查询了
            if(DateUtils.toDate2(todayStr).getTime() >= DateUtils.toDate2(curDate).getTime()){
                System.out.println("break:curDate"+curDate );
                break;
            }
            if(DateUtils.toDate2(todayStr).getTime() >= DateUtils.toDate2(eTime).getTime()){
                System.out.println("break:endTime"+eTime);
                break;
            }
        }
//        System.out.println("----------------------------------------------------------------");
//        System.out.println("totalCount-openlist--total---" + openList.size());
//        System.out.println("finalDay----"+lastdate);
        PageUtils pageUtils = new PageUtils(openList,totalCount,pageSize, pageIndex,lastdate);
        return pageUtils;
    }


    public String getstartQuery( String startTime, int pageIndex, int pageSize,String lastdate) throws ParseException {
        String sTime = DateUtils.format(DateUtils.toDate(startTime));//"yyyy-MM-dd"
        //System.out.println("sTime:"+sTime);
        //System.out.println("pageSize:"+pageSize);
        String startQueryTime = "";
        if(pageIndex == 1) {
            startQueryTime = CalendarUtils.dayaddOrSub(sTime, -1);
           // System.out.println("finalDay -- :"+lastdate);
           // System.out.println("startQuery -- index=1 --:"+startQueryTime);
        }else{
            startQueryTime =CalendarUtils.dayaddOrSub(DateUtils.format(DateUtils.toDate(lastdate)),0) ;
            //System.out.println("finalDay -- :"+lastdate);
            //System.out.println("startQuery -- index 非第一页 --:"+startQueryTime);
        }
        return startQueryTime;
    }
}
