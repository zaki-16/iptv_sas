package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.OperLogVM;
import com.hgys.iptv.controller.vm.SysLogVM;
import com.hgys.iptv.model.OperationLog;
import com.hgys.iptv.model.SysLog;
import com.hgys.iptv.util.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoggerController
 * @Auther: wangz
 * @Date: 2019/5/17 15:23
 * @Description: TODO
 */
@RestController
@RequestMapping("/log")
@Api(value = "LoggerController",tags = "日志管理Api接口")
public class LoggerController {
    @Autowired
    protected Logger logger;

    /**
     * 按时间段、登录账号、姓名、类型、结果、ip地址进行查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/loadSysLog")
    @ApiOperation(value="加载系统日志",notes="")
    public Page<SysLog> loadSysLog(
            SysLogVM sysLogVM,
            @ApiParam(value = "当前页",required = true,example = "1")
                                   @RequestParam(value = "pageNum")Integer pageNum,
                                   @ApiParam(value = "当前页数量",required = true,example = "10")
                                   @RequestParam(value = "pageSize")Integer pageSize){
        return logger.loadSysLog(sysLogVM,pageNum,pageSize);
    }

    @GetMapping("/loadOperLog")
    @ApiOperation(value="加载操作日志",notes="")
    public Page<OperationLog> loadOperationLog(OperLogVM operLogVM,
            @ApiParam(value = "当前页",required = true,example = "1")
            @RequestParam(value = "pageNum")Integer pageNum,
            @ApiParam(value = "当前页数量",required = true,example = "10")
            @RequestParam(value = "pageSize")Integer pageSize){

        return logger.loadOperationLog(operLogVM,pageNum,pageSize);
    }
}
