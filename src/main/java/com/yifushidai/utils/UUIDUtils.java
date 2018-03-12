package com.yifushidai.utils;

import java.util.UUID;

/**
 * Created by liumei on 2017/4/7.
 * desc:
 */
public class UUIDUtils {

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    //32位uuid
    public static String creatUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    //8位uuid
    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = creatUUID();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
    //产生n位随机数
    public static String creatRandomNums(int numLength){
        if(numLength<1){
            throw new IllegalArgumentException("随机数位数必须大于0");
        }
        return String.valueOf((long)((Math.random()*9+1)*Math.pow(10,numLength-1)));
    }
}
