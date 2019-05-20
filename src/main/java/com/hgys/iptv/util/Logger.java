package com.hgys.iptv.util;

import com.hgys.iptv.model.OperationLog;
import com.hgys.iptv.model.QOperationLog;
import com.hgys.iptv.model.QSysLog;
import com.hgys.iptv.model.SysLog;
import com.hgys.iptv.model.bean.UserSessionInfo;
import com.hgys.iptv.repository.OperationLogRepository;
import com.hgys.iptv.repository.SysLogRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

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

    private Logger(){}
//
//    private static class LoggerHolder{
//        private final static Logger INSTANCE= new Logger();
//    }
//    public static Logger getLogger(){
//        return LoggerHolder.INSTANCE;
//    }
    //-------------------记录系统登录日志

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

    /**
     * 记录操作日志
     */
    public void log(String operObj, String operType,String methodName, String result){
        try {
            UserSessionInfo info=UserSessionInfoHolder.getUserSessionInfo();

            OperationLog operationLog = new OperationLog();
            operationLog.setLoginName(info.getLoginName());
            operationLog.setRealName(info.getRealName());
            operationLog.setOperObj(operObj);
            operationLog.setOperType(operType);
            operationLog.setMethodName(methodName);
            operationLog.setResult(result);
            operationLog.setOperTime(new Timestamp(System.currentTimeMillis()));
            operationLogRepository.save(operationLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 分页加载系统日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<SysLog> loadSysLog(String pageNum,String pageSize){
//        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum)-1 ,Integer.parseInt(pageSize),null);
        return loadSysLog(pageable);
    }
    protected Page<SysLog> loadSysLog(Pageable pageable){
        QSysLog sysLog = QSysLog.sysLog;
        JPAQuery<SysLog> jpaQuery = queryFactory.selectFrom(sysLog)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return new PageImpl<>(jpaQuery.fetch(),pageable,jpaQuery.fetchResults().getTotal());
    }
    /**
     * 分页加载操作日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<OperationLog> loadOperationLog(String pageNum,String pageSize){
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum)-1 ,Integer.parseInt(pageSize),null);
        return loadOperationLog(pageable);
    }

    protected Page<OperationLog> loadOperationLog(Pageable pageable){
        QOperationLog operationLog = QOperationLog.operationLog;
        JPAQuery<OperationLog> jpaQuery = queryFactory.selectFrom(operationLog)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return new PageImpl<>(jpaQuery.fetch(),pageable,jpaQuery.fetchResults().getTotal());
    }
}
