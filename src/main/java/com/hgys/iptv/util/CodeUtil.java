package com.hgys.iptv.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author yangpeng
 */
public class CodeUtil {

    public synchronized static String  getOnlyCode(String prefix,Integer lenth){
        StringBuilder str=new StringBuilder();//定义变长字符串
        Random random=new Random();
        for (int i = 0; i < lenth; i++) {
            str.append(random.nextInt(10));
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());


        return prefix + newDate + str;
    }

}
