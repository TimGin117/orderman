package com.mydemo.orderman.utils;

import java.util.Random;

public class KeyUtil {

    public static synchronized String genUniquekey(){
        Random random = new Random();
        //生成6位随机数
        Integer number = random.nextInt(900000)+100000;

        //加上时间戳，返回唯一键值
        return System.currentTimeMillis()+String.valueOf(number);
    }

}
