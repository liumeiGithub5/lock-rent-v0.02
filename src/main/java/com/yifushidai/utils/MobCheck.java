package com.yifushidai.utils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liumei on 2017/4/18 21:42.
 * desc: 服务器 发送请求 验证手机验证码，返回状态
 */
public class MobCheck {

    /**
     * 服务端  验证结果，返回状态
     * @return
     * @throws Exception
     */
    public static String status(String phone,String checkCode) throws Exception{
        String address = "https://webapi.sms.mob.com/sms/verify";
        MobClient client = null;
        try {
            client = new MobClient(address);
            client.addParam("appkey", "21273d9cd2d88")//App Secret:117b26c362d1267d4f5446a007c44a4f //App Key:21273d9cd2d88
                    .addParam("phone", phone)
                    .addParam("zone", "86")
                    .addParam("code",checkCode);
            client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            client.addRequestProperty("Accept", "application/json");
            String result = client.post();
            return result;
        } finally {
            client.release();
        }
    }
}


class MobClient {
    public static String REQ_METHOD_GET = "GET";
    public static String REQ_METHOD_POST = "POST";
//	public static String REQ_METHOD_HEAD = "HEAD";
//	public static String REQ_METHOD_PUT = "OPTIONS";
//	public static String REQ_METHOD_TRACE = "TRACE";

    //请求地址
    private String address ;

    //请求参数
    private List<String> params = new ArrayList<String>() ;

    //链接超时时间
    public int conn_timeout= 10000;

    //读取超时
    public int read_timeout = 10000;

    //请求方式
    public String method = REQ_METHOD_POST;


    private HttpURLConnection conn ;


    public MobClient(String address) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        this.address = address;
        this.conn = build();
    }

    /**
     * 上传数据
     * @param data
     * @return
     * @throws Exception
     */
    public String post(String data) throws Exception{
        return requestData(address, data);
    }

    /**
     * 上传数据
     * @return
     * @throws Exception
     */
    public String post() throws Exception{
        String pstr = getParams();
        return post(pstr);
    }

    /**
     * 获得kv 参数
     * @return  k1=val&k2=val2
     */
    private String getParams(){
        StringBuffer buffer = new StringBuffer();
        boolean first = true ;
        for(String kv : params){
            if(first)
                first = false;
            else
                buffer.append("&");

            buffer.append(kv);
        }
        return buffer.toString();
    }


    /**
     * 发起https 请求
     * @param address
     * @param params
     * @return
     * @throws Exception
     */
    private  String requestData(String address, String params) throws Exception{

        // set params ;post params
        if (params!=null) {
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(params.getBytes(Charset.forName("UTF-8")));
            out.flush();
            out.close();
        }
        conn.connect();
        //get result
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String result = parsRtn(conn.getInputStream());
            return result;
        } else {
            throw new Exception(conn.getResponseCode() + " "+ conn.getResponseMessage());
        }
    }

    private HttpURLConnection build() throws NoSuchAlgorithmException, KeyManagementException, IOException{

        HttpURLConnection conn = null;
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public X509Certificate[] getAcceptedIssuers(){return null;}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());

        //ip host verify
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return urlHostName.equals(session.getPeerHost());
            }
        };

        //set ip host verify
        HttpsURLConnection.setDefaultHostnameVerifier(hv);

        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        URL url = new URL(address);
        conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);// POST
        conn.setConnectTimeout(conn_timeout);
        conn.setReadTimeout(read_timeout);

        return conn;
    }

    public void addRequestProperty(String key ,String val ){
        conn.addRequestProperty(key, val);
    }


    public void release(){
        if(conn !=null){
            conn.disconnect();
        }
    }


    /**
     * 获取返回数据
     * @param is
     * @return
     * @throws IOException
     */
    private  String parsRtn(InputStream is) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = null;
        boolean first = true;
        while ((line = reader.readLine()) != null) {
            if(first){
                first = false;
            }else{
                buffer.append("\n");
            }
            buffer.append(line);
        }
        return buffer.toString();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MobClient addParam(String key , String val){

        if(key!=null&&key.length()> 0 ){
            params.add(key + "=" + val);
        }
        return this;
    }
}

