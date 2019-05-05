package com.hgys.iptv.controller;

import com.hgys.iptv.exception.BaseException;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/demo")
@Api(value = "Demo")
public class DemoContronller {

    @GetMapping("demoReturn")
    public ResultVO<?> signBaseAgreement(String phoneNum) {
        /**
         * 抛异常处理
         */
        if (StringUtils.isBlank(phoneNum)){
            throw new BaseException(ResultEnum.ACCESS_LINK_ERROR);
        }

        /**
         * 处理成功情况
         */
        if (true){
            //success方法对象为T型，Object、List等查询的对象都可放入其中
            return ResultVOUtil.success(Boolean.TRUE);
        }
        /**
         * 处理失败情况
         */
        return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);

    }
}
