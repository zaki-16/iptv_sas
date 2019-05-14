package com.hgys.iptv.util;

import java.math.BigDecimal;

/**
 * @ClassName SettleUtil
 * @Auther: wangz
 * @Date: 2019/5/14 17:13
 * @Description: TODO
 */
public class SettleUtil {
    /**
     *  d1*d2
     * 高精度金额计算--返回 double
     * @param d1
     * @param d2
     * @return
     */
    public static double multi(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.multiply(bd2).doubleValue();
    }
    /**
     * d1*d2
     * 高精度金额计算--返回 String
     * @param d1
     * @param d2
     * @return
     */
    public static String multi(String d1,String d2){
        BigDecimal bd1 = new BigDecimal(Double.valueOf(d1));
        BigDecimal bd2 = new BigDecimal(Double.valueOf(d2));
        double result = bd1.multiply(bd2).doubleValue();
        return Double.toString(result);
    }


    /**
     * d1+d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.add(bd2).doubleValue();
    }

    public static double add(BigDecimal d1, BigDecimal d2) {
        return d1.add(d2).doubleValue();
    }

    /**
     * d1-d2
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue();
    }

}
