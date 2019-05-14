package com.hgys.iptv.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

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

    /**
     * 校验权重是否是100%
     * 传入值如果是百分数的字符串
     * 先校验是否是%号结尾->切割->
     * @param validWeightList
     * @return
     */
    public static boolean validWeightForString(List<String> validWeightList){
        double sumWeight = Double.valueOf(0);
        for(String s:validWeightList){
            sumWeight = SettleUtil.add(sumWeight,Double.valueOf(s));
            if(sumWeight>100)
                return false;
        }
        if(sumWeight == 100)
            return true;
        else//不足
            return false;
    }

    /**
     * 校验权重是否是100%
     * 传入值需整型，如 80% = 80
     * @param validWeightList
     * @return
     */
    public static boolean validWeightForInt(List<Integer> validWeightList){
        assert(!validWeightList.isEmpty());
        Integer sumWeight=0;
        for(Integer i:validWeightList){
            sumWeight+=i;
            if(sumWeight>100)
                return false;
        }
        if(sumWeight == 100)
            return true;
        else//不足
            return false;
    }

    /**
     * 校验权重格式，1：百分数，2：小数或整数
     * -1：数据格式不对
     */
    public static Integer validWeightFormat(String weight){
        String str1 = "^\\d+\\.?\\d*\\%$";//是否是百分数格式
        String str2 = "^[0-9]+([.]{1}[0-9]{1,2})?$";//数字结尾，最多两位的数字
        if(Pattern.matches(str1, weight)){
            return 1;
        }else if(Pattern.matches(str2, weight)){
            return 2;
        }
        else
            return -1;
    }

}
