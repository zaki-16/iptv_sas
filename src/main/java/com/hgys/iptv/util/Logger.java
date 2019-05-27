package com.hgys.iptv.util;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import com.hgys.iptv.controller.vm.OperLogVM;
import com.hgys.iptv.controller.vm.SysLogVM;
import com.hgys.iptv.model.OperationLog;
import com.hgys.iptv.model.QOperationLog;
import com.hgys.iptv.model.QSysLog;
import com.hgys.iptv.model.SysLog;
import com.hgys.iptv.model.bean.UserSessionInfo;
import com.hgys.iptv.model.enums.LogResultEnum;
import com.hgys.iptv.model.enums.LogTypeEnum;
import com.hgys.iptv.repository.OperationLogRepository;
import com.hgys.iptv.repository.SysLogRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @ClassName Logger
 * @Auther: wangz
 * @Date: 2019/5/17 11:05
 * @Description: TODO
 */
@Component
public class Logger {
    /**
      @Autowired
      private Logger logger;
     */

    @Autowired
    private OperationLogRepository operationLogRepository;

    @Autowired
    private SysLogRepository sysLogRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private RepositoryManager repositoryManager;

    private Logger(){}
//
//    private static class LoggerHolder{
//        private final static Logger INSTANCE= new Logger();
//    }
//    public static Logger getLogger(){
//        return LoggerHolder.INSTANCE;
//    }



    //----==============--=======------记录日志==============-==========--===-=============

    /**
     * 记录登录日志
     */
    public void log(String loginType,String result){
        try {
            UserSessionInfo info=UserSessionInfoHolder.getUserSessionInfo();

            SysLog sysLog = new SysLog();
            sysLog.setLoginName(info.getLoginName());
            sysLog.setRealName(info.getRealName());
            sysLog.setType(loginType);
            sysLog.setResult(result);
            sysLog.setIp(info.getIp());
            sysLog.setTime(new Timestamp(System.currentTimeMillis()));
            sysLogRepository.save(sysLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void log(String username,String realName,String ip,String loginType,String result){
        try {
            SysLog sysLog = new SysLog();
            sysLog.setLoginName(username);
            sysLog.setRealName(realName);
            sysLog.setType(loginType);
            sysLog.setResult(result);
            sysLog.setIp(ip);
            sysLog.setTime(new Timestamp(System.currentTimeMillis()));
            sysLogRepository.save(sysLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 记录操作日志
     */
    public void log(String menuName, String operType,String methodName,String result){
        //操作对象，操作类型，方法名，结果
        try {
            UserSessionInfo info=UserSessionInfoHolder.getUserSessionInfo();

            OperationLog operationLog = new OperationLog();
            operationLog.setLoginName(info.getLoginName());
            operationLog.setRealName(info.getRealName());
            operationLog.setOperObj(menuName);
            operationLog.setOperType(operType);
            operationLog.setMethodName(methodName);
            operationLog.setIp(info.getIp());
            operationLog.setResult(result);
            operationLog.setOperTime(new Timestamp(System.currentTimeMillis()));
            operationLogRepository.save(operationLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//==============-==-=========-------=====加载日志===================--===-==========================
    /**
     * 分页加载系统日志
     * 按时间段、登录账号、姓名、类型、结果、ip地址进行查询
     */
    public Page<SysLog> loadSysLog(SysLogVM sysLogVM,Integer pageNum, Integer pageSize){
        HashMap<String, Object> map = Maps.newHashMap();
        if(sysLogVM.getLoginName()!=null)
            map.put("loginName","%"+sysLogVM.getLoginName()+"%");
        if(sysLogVM.getRealName()!=null)
            map.put("realName","%"+sysLogVM.getRealName()+"%");
        if(sysLogVM.getType()!=null)
            map.put("type","%"+sysLogVM.getType()+"%");
        map.put("result",sysLogVM.getResult());
        map.put("ip",sysLogVM.getIp());
        //排序
        Sort sort = new Sort(Sort.Direction.DESC,"time");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        return repositoryManager.findByCriteriaPage(sysLogRepository,map,pageable);
    }
    /**
     * 分页加载操作日志
     * @param pageNum
     * @param pageSize
     * @return 时间段、账号、姓名、操作对象、操作结果、ip地址进行查询
     */
    public Page<OperationLog> loadOperationLog(OperLogVM operLogVM,Integer pageNum, Integer pageSize){
        HashMap<String, Object> map = Maps.newHashMap();
        if(operLogVM.getLoginName()!=null)
            map.put("loginName","%"+operLogVM.getLoginName()+"%");
        if(operLogVM.getRealName()!=null)
            map.put("realName","%"+operLogVM.getRealName()+"%");
        if(operLogVM.getOperObj()!=null)
            map.put("operObj","%"+operLogVM.getOperObj()+"%");
        map.put("result",operLogVM.getResult());
        map.put("ip",operLogVM.getIp());
        Sort sort = new Sort(Sort.Direction.DESC,"operTime");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        return repositoryManager.findByCriteriaPage(operationLogRepository,map,pageable);
    }

//    protected Page<OperationLog> loadOperationLog(Pageable pageable){
//        QOperationLog operationLog = QOperationLog.operationLog;
//        JPAQuery<OperationLog> jpaQuery = queryFactory.selectFrom(operationLog)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize());
//        return new PageImpl<>(jpaQuery.fetch(),pageable,jpaQuery.fetchResults().getTotal());
//    }



    //=======================-=--------针对 增删改查的便捷日志调用-=-=-=----------========
    public void log_add_success(String menuName,String methodName){
        log(menuName, LogTypeEnum.ADD.getType(),methodName, LogResultEnum.SUCCESS.getResult());
    }
    public void log_add_fail(String menuName,String methodName){
        log(menuName, LogTypeEnum.ADD.getType(),methodName, LogResultEnum.FAILURE.getResult());
    }

    public void log_up_success(String menuName,String methodName){
        log(menuName, LogTypeEnum.MODIFY.getType(),methodName, LogResultEnum.SUCCESS.getResult());
    }
    public void log_up_fail(String menuName,String methodName){
        log(menuName, LogTypeEnum.MODIFY.getType(),methodName, LogResultEnum.FAILURE.getResult());
    }

    public void log_rm_success(String menuName,String methodName){
        log(menuName, LogTypeEnum.REMOVE.getType(),methodName, LogResultEnum.SUCCESS.getResult());
    }
    public void log_rm_fail(String menuName,String methodName){
        log(menuName, LogTypeEnum.REMOVE.getType(),methodName, LogResultEnum.FAILURE.getResult());
    }


}
