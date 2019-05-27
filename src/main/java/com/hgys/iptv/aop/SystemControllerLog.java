package com.hgys.iptv.aop;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
    // descption 描述方法的实际作用
    String description()  default "";
    //     * 操作对象
     String target() default "";
    //     * 操作类型
     String type() default "";
    //     * 操作的方法名
     String methodName() default "";


}
