package com.yifushidai.service;

import com.yifushidai.dtoAndvo.SaveDetailVo;
import com.yifushidai.entity.MonthsDetailEntity;
import com.yifushidai.entity.QuartersDetailEntity;
import com.yifushidai.entity.WeeksDetailEntity;
import com.yifushidai.entity.YearsDetailEntity;
import com.yifushidai.mapper.MonthsDetailEntityMapper;
import com.yifushidai.mapper.QuartersDetailEntityMapper;
import com.yifushidai.mapper.WeeksDetailEntityMapper;
import com.yifushidai.mapper.YearsDetailEntityMapper;
import com.yifushidai.utils.PageUtils;
import com.yifushidai.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liumei on 2017/10/10 18:01.
 * desc:
 */
@Service
public class PowerService {
    @Autowired
    private WeeksDetailEntityMapper weeksMapper;
    @Autowired
    private MonthsDetailEntityMapper monthsMapper;
    @Autowired
    private YearsDetailEntityMapper yearsMapper;
    @Autowired
    private QuartersDetailEntityMapper quartersMapper;

    public PageUtils queryDetails( int qtype,Query query) {
        List<SaveDetailVo> resultlist = new ArrayList<>();
        int total = 0;
        //遍历计算 百分比
        Double totalSave = 0.00;
        if(qtype == 1){//周
            List<WeeksDetailEntity> weeksDetailEntities = weeksMapper.queryByMac(query);
            for(WeeksDetailEntity week:weeksDetailEntities){
                totalSave =totalSave + week.getSavetimes();
            }
            for(WeeksDetailEntity week:weeksDetailEntities){
               Double persent = (week.getSavetimes()/totalSave);
               SaveDetailVo saveDetailVo = new SaveDetailVo(week,persent);
                resultlist.add(saveDetailVo);
            }
            total = weeksMapper.queryByMacTotal(query);
        }
        if(qtype == 2){//月
            List<MonthsDetailEntity> monthsDetailEntities = monthsMapper.queryByMac(query);
            for(MonthsDetailEntity month:monthsDetailEntities){
                totalSave = totalSave + month.getSavetimes();
            }
            for(MonthsDetailEntity month:monthsDetailEntities){
                Double persent = (month.getSavetimes()/totalSave);
                SaveDetailVo saveDetailVo = new SaveDetailVo(month,persent);
                resultlist.add(saveDetailVo);
            }
            total = monthsMapper.queryByMacTotal(query);
        }
        if(qtype == 3){//季度
            List<QuartersDetailEntity> quartersDetailEntities = quartersMapper.queryByMac(query);
            for(QuartersDetailEntity quarter:quartersDetailEntities){
                totalSave = totalSave + quarter.getSavetimes();
            }
            for(QuartersDetailEntity quarter:quartersDetailEntities){
                Double persent = (quarter.getSavetimes()/totalSave);
                SaveDetailVo saveDetailVo = new SaveDetailVo(quarter,persent);
                resultlist.add(saveDetailVo);
            }
            total = quartersMapper.queryByMacTotal(query);
        }
        if(qtype == 4){//年
            List<YearsDetailEntity> yearsDetailEntities = yearsMapper.queryByMac(query);
            for(YearsDetailEntity years:yearsDetailEntities){
                totalSave = totalSave + years.getSavetimes();
            }
            for(YearsDetailEntity years:yearsDetailEntities){
                Double persent = (years.getSavetimes()/totalSave);
                SaveDetailVo saveDetailVo = new SaveDetailVo(years,persent);
                resultlist.add(saveDetailVo);
            }
            total = yearsMapper.queryByMacTotal(query);
        }
        PageUtils pageUtil = new PageUtils(resultlist,total, query.getLimit(), query.getPage());
        return pageUtil;
    }

   /* @Test
    public void test() throws ParseException {
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(DateUtils.toDate("2017-10-7 10:00:00"));
        aft.setTime(new Date());
        int days = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int weeks = aft.get(Calendar.WEEK_OF_YEAR) - bef.get(Calendar.WEEK_OF_YEAR);
        int months = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int quarters = (aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH))/3;
        int years = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);

        System.out.println("days："+days);
        System.out.println("weeks："+weeks);
        System.out.println("months："+months);
        System.out.println("quarters："+quarters);
        System.out.println("years："+years);

        int daydiff = CalendarUtils.getDayDiff2("2016-10-7 10:00:00",DateUtils.getCurrent2());
        int daydiff2 = CalendarUtils.getDayDiff2("2016-10-7 10:00:00",DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        System.out.println("daydiff2："+daydiff2);
        System.out.println("daydiff："+daydiff);

    }
*/
   /* @Test
    public void test2(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        System.out.println(String.valueOf(c.get(Calendar.YEAR)) );
        System.out.println(String.valueOf( c.get(Calendar.WEEK_OF_YEAR)));
        System.out.println(String.valueOf( c.get(Calendar.MONTH))) ;//为 calendar 设置“月份（MONTH）”时需要 “-1”;通过 calendar 获取“月份（month）”时需要 “+1”


        String curWeeks = String.valueOf(c.getWeeksInWeekYear());
        String cur = String.valueOf(c.getWeekYear());
        System.out.println("weeks" + curWeeks);
        System.out.println("cur" + cur);
    }*/
/*
   @Test
   public void test(){
       Double persent = (50.00/88.00) + (50.00%88.00);
       System.out.println("persent1:"+(50.00/88.00));
       System.out.println("persent2:"+(50.00%88.00));
       System.out.println("persent3:"+(50.00%100));
   }*/
}
