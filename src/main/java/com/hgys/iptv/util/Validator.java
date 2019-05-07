package com.hgys.iptv.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName Validator
 * @Auther: wangz
 * @Date: 2019/5/7 00:15
 * @Description: TODO 校验工具
 */
public class Validator {

    /**
     * 字符数组有一个为空则为false
     * @param strs
     * @return
     */
    public static boolean validEmptyPass(String... strs){
        boolean f=true;
        for(String s:strs){
            if(StringUtils.isBlank(s))
                f=false;
            if(!f)
                break;
        }
        return f;
    }


}
