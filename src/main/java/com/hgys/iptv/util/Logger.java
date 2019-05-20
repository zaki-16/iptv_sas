package com.hgys.iptv.util;

import com.hgys.iptv.model.OperationLog;
import com.hgys.iptv.model.QOperationLog;
import com.hgys.iptv.model.QSysLog;
import com.hgys.iptv.model.SysLog;
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

    @Autowired
    private OperationLogRepository operationLogRepository;

    @Autowired
    private SysLogRepository sysLogRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    private Logger(){}

    private static class LoggerHolder{
        private final static Logger INSTANCE= new Logger();
    }
    public static Logger getLogger(){
        return LoggerHolder.INSTANCE;
    }
    //-------------------记录系统登录日志

    /**
     * 记录登录日志
     * @param loginName
     * @param realName
     * @param type
     * @param result
     * @return
     */
    public SysLog log(String loginName,String realName,String type,String result){
        SysLog sysLog = new SysLog();
        sysLog.setLoginName(loginName);
        sysLog.setRealName(realName);
        sysLog.setType(type);
        sysLog.setResult(result);
        sysLog.setTime(new Timestamp(System.currentTimeMillis()));
        return sysLogRepository.save(sysLog);
    }

    /**
     * 记录操作日志
     * @param loginName
     * @param realName
     * @param operObj
     * @param operType
     * @param operResult
     * @return
     */
    public OperationLog log(String loginName, String realName, String operObj, String operType, String operResult){
        OperationLog operationLog = new OperationLog();
        operationLog.setLoginName(loginName);
        operationLog.setRealName(realName);
        operationLog.setOperObj(operObj);
        operationLog.setOperType(operType);
        operationLog.setOperResult(operResult);
        operationLog.setOperTime(new Timestamp(System.currentTimeMillis()));
        return operationLogRepository.save(operationLog);
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
