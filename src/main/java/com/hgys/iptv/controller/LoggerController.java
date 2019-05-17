package com.hgys.iptv.controller;

import com.hgys.iptv.model.OperationLog;
import com.hgys.iptv.model.SysLog;
import com.hgys.iptv.util.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/loadSysLog")
    @ApiOperation(value="加载系统日志",notes="")
    public Page<SysLog> loadSysLog(@ApiParam(value = "当前页",required = true,example = "1")
                                   @RequestParam(value = "pageNum")String pageNum,
                                   @ApiParam(value = "当前页数量",required = true,example = "10")
                                   @RequestParam(value = "pageSize")String pageSize){
        return logger.loadSysLog(pageNum,pageSize);
    }

    @GetMapping("/loadOperLog")
    @ApiOperation(value="加载操作日志",notes="")
    public Page<OperationLog> loadOperationLog(
            @ApiParam(value = "当前页",required = true,example = "1")
            @RequestParam(value = "pageNum")String pageNum,
            @ApiParam(value = "当前页数量",required = true,example = "10")
            @RequestParam(value = "pageSize")String pageSize){

        return logger.loadOperationLog(pageNum,pageSize);
    }
}
