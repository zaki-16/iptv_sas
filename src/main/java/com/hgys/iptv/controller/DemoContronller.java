package com.hgys.iptv.controller;

import com.hgys.iptv.exception.BaseException;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Api(value = "Demo",tags = "demo演示接口")
public class DemoContronller {

    @GetMapping("demoReturn")
    @ApiOperation(value = "演示接口",notes = "返回json数据类型")
    public ResultVO<?> signBaseAgreement(@ApiParam(value = "用户名称",required = true) @RequestParam("name")String name) {
        /**
         * 抛异常处理
         */
        if (StringUtils.isBlank(name)){
            throw new BaseException(ResultEnum.ACCESS_DENIED_ERROR);
        }

        /**
         * 处理成功情况
         */
        if (true){
            //success方法对象为T型，Object、List等查询的对象都可放入其中
            //return ResultVOUtil.success(Boolean.TRUE);
            return ResultVOUtil.success(name);
        }
        /**
         * 处理失败情况
         */
        return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);

    }
}
