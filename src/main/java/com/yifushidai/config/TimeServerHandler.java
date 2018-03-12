package com.yifushidai.config;

/**
 * Created by liumei on 2017/8/29 17:28.
 * desc:mina 服务器收到锁端的推送   做出的回应
 */

import com.tencent.xinge.XingeApp;
import com.yifushidai.entity.*;
import com.yifushidai.mapper.*;
import com.yifushidai.utils.DateUtils;
import com.yifushidai.utils.XinGeUtils;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 逻辑处理类
 */
public class TimeServerHandler implements IoHandler {

    private Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);
    public static ConcurrentHashMap<String, IoSession> sessionsConcurrentHashMap = new ConcurrentHashMap<String, IoSession>();//线程安全

    @Autowired
    private OpenRecordEntityMapper openRecordMapper;
    @Autowired
    private KeypassBandEntityMapper keyPassMapper;
    @Autowired
    private XingeTokenEntityMapper xingeMapper;
    @Autowired
    private BandLockEntityMapper lockMapper;
    @Autowired
    private PowerEntityMapper powerMapper;
    @Autowired
    private SaveETEntityMapper saveEMapper;
    @Autowired
    private ICEntityMapper icEntityMapper;
    @Autowired
    private WeeksDetailEntityMapper weeksMapper;
    @Autowired
    private MonthsDetailEntityMapper monthsMapper;
    @Autowired
    private QuartersDetailEntityMapper quartersMapper;
    @Autowired
    private YearsDetailEntityMapper yearsMapper;
    @Autowired
    private UserEntityMapper userMapper;

    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        //String remoteAddressAndSocket = ioSession.getRemoteAddress().toString();//192.168.1.3:55813
        String remoteAddress = ((InetSocketAddress) ioSession.getRemoteAddress()).getAddress().getHostAddress();//192.168.1.3
        ioSession.setAttribute("ip", remoteAddress);
        // 设置IoSession闲置时间，参数单位是秒
        ioSession.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
    }
    /*ioSession闲置时*/
    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) {
        // 如果IoSession闲置，则关闭连接
        if (idleStatus == IdleStatus.BOTH_IDLE)
        {
            //  ioSession.write("heartbeat");
            ioSession.closeNow();
        }
    }


    @Override
    public void messageReceived(IoSession ioSession, Object o) throws Exception {
        String message = o.toString();
        System.out.println("message---------------"+message);
        //业务处理
        if(message != null && message.length()>0 && message.contains(":")) {
            System.out.println("message---------------" + message);
            ioSession.write("$recived$");
            //System.out.println("ioSession.write()之后 会不会执行 接下来的语句？ 答案： 会 ");
            String[] msg = message.split(":");
            String head = msg[0];
            String body = msg[1];
            String mac = "";
            logger.info("Unique_mac:" + body);

            //1.首次连接或每次断开重连 Unique_mac:  先发mac作为唯一标识
            if (head.equalsIgnoreCase("Unique_mac")) {
                mac = body;
                ioSession.setAttribute("Unique_mac", mac);
                sessionsConcurrentHashMap.put(mac, ioSession);//添加当前ioSession 到静态集合
                //标志在线
                BandLockEntity bandlock = lockMapper.queryByMac(mac);
                bandlock.setLs((byte) 0);
                lockMapper.updateByPrimaryKeySelective(bandlock);
                // ioSession.write("$recived$");
            }

            //2.开门结果Result:mac_type_result
            else if (head.equalsIgnoreCase("Result")) {
                String[] infos = body.split("_");
                mac = infos[0];
                String type = infos[1];
                int result = Integer.valueOf(infos[2]);
                //同时修改tempkey 的状态 //0：临时密码开门  1：固定密码开门  卡号：ic卡开门（卡号）
                if (type.equalsIgnoreCase("0")) {
                    KeypassBandEntity keypassBandEntity = keyPassMapper.queryByMac(mac);
                    keypassBandEntity.setTempstatus((byte) 1);
                    keyPassMapper.updateByPrimaryKeySelective(keypassBandEntity);
                }
                //插入开门记录
                OpenRecordEntity openRecord = new OpenRecordEntity();
                openRecord.setResult(result);
                openRecord.setTyp(type);
                openRecord.setMac(mac);
                openRecordMapper.insertSelective(openRecord);
                // ioSession.write("$recived$");
                Date openTime = DateUtils.getCurrent();
                //3分钟四次报警
                if (result == 1) {
                    // System.out.println("进入  3分钟四次报警 ");
                    Date lastTime = DateUtils.subTime(180000L, openTime);
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("openTime", openTime);//当前失败时间
                    map.put("lastTime", lastTime); //3分钟之前  上限时间
                    map.put("mac", mac);
                    List<OpenRecordEntity> failOpenRecordList = openRecordMapper.queryWarnList(map);
                    //  System.out.println("size ==  "+failOpenRecordList.size());
                    int count = 0;
                    if (failOpenRecordList.size() >= 4) {
                        for (OpenRecordEntity failOpenRecord : failOpenRecordList) {
                            if (failOpenRecord.getResult() == 1) {
                                count = count + 1;
                                // System.out.println("count ==  "+count);
                                if (count == 4) {
                                    //推送
                                    sendNotice(mac, "三分钟内已连续四次开门失败，请及时关注是否为恶意开门！", "安全提醒");
                                    break;
                                }
                            } else {
                                count = 0;
                            }
                        }
                    }
                }
            }

            //3.门锁电量提醒 Power:mac_pl/pf 移动端显示 + 推送
            else if (head.equalsIgnoreCase("Power")) {
                String[] infos = body.split("_");
                mac = infos[0];
                String warning = infos[1];
                BandLockEntity bandlock = lockMapper.queryByMac(mac);
                if (warning.equalsIgnoreCase("pl")) {
                    bandlock.setLes((byte) 1);
                    //推送。。。。。
                    sendNotice(mac, "门锁电池电量低，请及时更换电池", "电量提醒");
                } else {
                    bandlock.setLes((byte) 0);
                }
                lockMapper.updateByPrimaryKeySelective(bandlock);
            }

            //4.卡取电设备电量提醒CaPower:mac_cpl/cpf  推送
            else if (head.equalsIgnoreCase("CaPower")) {
                String[] infos = body.split("_");
                mac = infos[0];
                String warning = infos[1];
                BandLockEntity bandlock = lockMapper.queryByMac(mac);
                if (warning.equalsIgnoreCase("pl")) {
                    bandlock.setCales((byte) 1);
                    //推送。。。。。
                    sendNotice(mac, "卡取电设备电量过低,请及时更换电池", "电量提醒");
                } else {
                    bandlock.setCales((byte) 0);
                }
                lockMapper.updateByPrimaryKeySelective(bandlock);
            }

            //5.来断电记录PowerRecords:mac_pOn/pOff
            else if (head.equalsIgnoreCase("PowerRecords")) {
                String[] infos = body.split("_");
                String OnOrOff = infos[1]; ///////
                BandLockEntity lock = lockMapper.queryByMac(mac);//控制没绑定时 单机测试 不插入信息
                if (lock != null) {
                    PowerEntity powerEntity = new PowerEntity();
                    powerEntity.setMac(mac);
                    //处理节约用电时间
                    Boolean flag = false;
                    if (OnOrOff.equalsIgnoreCase("pOn")) {
                        flag = true;
                    }
                    powerEntity.setTyp(flag ? 0 : 1);
                    powerMapper.insertSelective(powerEntity);//插入来断电记录

                    //来电触发  来电结束 断电开始
                    if (flag) {
                        //flag = false;
                        SaveETEntity saveE = new SaveETEntity();
                        Date curTime = DateUtils.getCurrent();
                        Calendar c = Calendar.getInstance();
                        c.setTime(curTime);
                        String curYears = String.valueOf(c.get(Calendar.YEAR));
                        int month = c.get(Calendar.MONTH) + 1;
                        String curMonths = String.valueOf(c.get(Calendar.MONTH) + 1);
                        String curWeeks = String.valueOf(c.get(Calendar.WEEK_OF_YEAR));
                        String curQuarters = "";
                        if (month >= 1 && month <= 3) {
                            curQuarters = "1";
                        } else if (month >= 4 && month <= 6) {
                            curQuarters = "2";
                        } else if (month >= 7 && month <= 9) {
                            curQuarters = "3";
                        } else {
                            curQuarters = "4";
                        }
                        saveE.setCreatetime(curTime);
                        saveE.setId(null);
                        saveE.setMac(mac);
                        //拼凑
                        List<PowerEntity> ps = powerMapper.queryLatestByMac(mac);
                        PowerEntity p = ps.get(1);
                        if (p.getTyp() == 1) {
                            Long timeRange = curTime.getTime() - p.getPowerTime().getTime();
                            Double tr = timeRange.doubleValue() / (1000 * 60 * 60);//毫秒 除以3600000  = 小时
                            saveE.setSaveetime(tr);
                            saveEMapper.insertSelective(saveE);
                            //累加该锁 总的省电时间  今日省电时间
                            Double trToday = 0.00;
                            //  BandLockEntity lock = lockMapper.queryByMac(mac);//
                            lock.setSavetotal(lock.getSavetotal() + tr);
                            List<SaveETEntity> saves = saveEMapper.queryByToday(mac);
                            for (SaveETEntity saveET : saves) {
                                trToday = trToday + saveET.getSaveetime();
                            }
                            lock.setSavetoday(trToday);
                            lockMapper.updateByPrimaryKeySelective(lock);
                            //周月季度年 省电详情
                            WeeksDetailEntity weekDetail = weeksMapper.queryCur(mac, curWeeks, curYears);
                            MonthsDetailEntity monthDetail = monthsMapper.queryCur(mac, curMonths, curYears);
                            QuartersDetailEntity quarterDetail = quartersMapper.queryCur(mac, curYears, curQuarters);
                            YearsDetailEntity yearDetail = yearsMapper.queryCur(mac, curYears);
                            if (weekDetail == null) {
                                weekDetail = new WeeksDetailEntity(tr, mac);
                                weeksMapper.insertSelective(weekDetail);
                            } else {
                                weekDetail.setSavetimes(weekDetail.getSavetimes() + tr);
                                weeksMapper.updateByPrimaryKeySelective(weekDetail);
                            }
                            if (monthDetail == null) {
                                monthDetail = new MonthsDetailEntity(tr, mac);
                                monthsMapper.insertSelective(monthDetail);
                            } else {
                                monthDetail.setSavetimes(monthDetail.getSavetimes() + tr);
                                monthsMapper.updateByPrimaryKeySelective(monthDetail);
                            }
                            if (quarterDetail == null) {
                                quarterDetail = new QuartersDetailEntity(tr, mac);
                                quartersMapper.insertSelective(quarterDetail);
                            } else {
                                quarterDetail.setSavetimes(quarterDetail.getSavetimes() + tr);
                                quartersMapper.updateByPrimaryKeySelective(quarterDetail);
                            }
                            if (yearDetail == null) {
                                yearDetail = new YearsDetailEntity(tr, mac);
                                yearsMapper.insertSelective(yearDetail);
                            } else {
                                yearDetail.setSavetimes(yearDetail.getSavetimes() + tr);
                                yearsMapper.updateByPrimaryKeySelective(yearDetail);
                            }
                        }
                    }
                }
            }
            //6.ic卡补办信息：Ic_update：mac_name_inNo
            else if (head.equalsIgnoreCase("Ic_update")) {
                String[] infos = body.split("_");
                String name = infos[1];
                String inNo = infos[2];
                ICEntity ic = icEntityMapper.queryByMacAndName(mac, name);
                ic.setInno(inNo);
                ic.setUpdatetime(DateUtils.getCurrent());
                icEntityMapper.updateByPrimaryKeySelective(ic);
            }

            else {
                // System.out.println(" else  包不包含心跳请求包？？？");
            }
        }
    }

    //会话关闭处理
    @Override
    public void sessionClosed(IoSession ioSession){
        String unique_mac = (String)ioSession.getAttribute("Unique_mac");
        sessionsConcurrentHashMap.remove(unique_mac);//移除当前ioSession
        ioSession.closeNow();
        //标志掉线
        BandLockEntity bandlock = lockMapper.queryByMac(unique_mac);
        bandlock.setLs((byte) 1);
        lockMapper.updateByPrimaryKeySelective(bandlock);
    }
    //异常断开处理
    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable){
        //打印异常
        logger.info("session occured exception, so close it.");
        throwable.printStackTrace();
        //连接抛异常，断开
        ioSession.closeNow();
    }


    @Override
    public void messageSent (IoSession ioSession, Object o) throws Exception {
    }

    @Override
    public void inputClosed (IoSession ioSession) throws Exception {
    }

   /* mina 发通知 方法*/
    public void sendNotice(String mac, String content, String title) {
        BandLockEntity lock = lockMapper.queryByMac(mac);
        String alias = lock.getAlias();
        String mb = lock.getMobile();
        String xingeToken = xingeMapper.selectByPrimaryKey(mb).getToken();
       // logger.info("ios  xingeToken::"+xingeToken);
        String appType = userMapper.selectByMobile(mb).getApptype();
       // System.out.println("appType-------------"+appType);
        boolean flag = XinGeUtils.pushByTokenAndApptype(appType, alias, xingeToken, content, title, XingeApp.IOSENV_PROD);
        //boolean flag = XinGeUtils.pushByTokenAndApptype(appType, alias, xingeToken, content, title, XingeApp.IOSENV_DEV);
        if(flag ){
            logger.info(appType + "推送成功");
        }else{
            logger.info(appType + "推送失败");
        }
    }
}

