package com.hgys.iptv.aop;

import com.hgys.iptv.util.Logger;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName SystemLogAspect
 * @Auther: wangz
 * @Date: 2019/5/27 10:45
 * @Description: TODO 日志切面
 */
/**
 * 切点类
 */
@Aspect
@Component
public class SystemLogAspect {

    private static String clazzAndMethodName = "";
    @Resource
    private Logger logger;

    //Controller层切点
    @Pointcut("@annotation(com.hgys.iptv.aop.SystemControllerLog)")
    public  void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public  void doBefore(JoinPoint joinPoint) {
//        UserSessionInfo info = UserSessionInfoHolder.getUserSessionInfo();
//        String username = info==null?"null":info.getLoginName();
        try {
            //*========记录操作日志日志=========*//
            logger.log(getAnnotationMethod(joinPoint).target(),
                    getAnnotationMethod(joinPoint).type(),
                    clazzAndMethodName,"成功");
            //System.out.println("=====前置通知结束=====");
        }  catch (Exception e) {
            try {
                logger.log(getAnnotationMethod(joinPoint).target(),
                        getAnnotationMethod(joinPoint).type(),
                        clazzAndMethodName,"失败");
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }


    /**
     * 获取注解中对方法的描述等信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
        //获取类的全限定名
        String targetName = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }



    private static SystemControllerLog getAnnotationMethod(JoinPoint joinPoint)  throws Exception {
        //获取类的全限定名
        String targetName = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        if(targetName.contains(".")){
            Arrays.asList(StringUtils.split(targetName,".")).forEach(m->{
                clazzAndMethodName=m;
            });
        }
        clazzAndMethodName += "."+methodName;
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        SystemControllerLog annotation = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    annotation = method.getAnnotation(SystemControllerLog.class);
                    break;
                }
            }
        }
        return annotation;
    }
}