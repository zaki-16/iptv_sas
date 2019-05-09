package com.hgys.iptv.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hgys.iptv.controller.vm.*;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.OrderQuantity;
import com.hgys.iptv.model.SettlementDimension;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.CpRepository;
import com.hgys.iptv.service.CpService;
import com.hgys.iptv.service.OrderQuantityService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/orderquantity")
@Api(value = "orderquantity",tags = "新增结算类型-订购量Api接口")
public class OrderQuantityController {
    @Autowired
    private OrderQuantityService orderquantityService;

    @Autowired
    private CpService cpService;

    @Autowired
    private CpRepository cpRepository;

    /**
     * 根据ID查询结算类型-订购量
     * @param code
     * @return
     */
    @GetMapping("/selectByCode")
    @ApiOperation(value="通过code查询", notes="返回json数据类型..")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findById(@ApiParam(value = "Code查询",required = true) @RequestParam("code")String code){
        if (StringUtils.isBlank(code)){
            return ResultVOUtil.error("1","id不能为空");
        }
        OrderQuantity or = orderquantityService.findByCode(code.trim()).orElseThrow(()-> new IllegalArgumentException("未查询到code为：" + code + "的信息"));

        return ResultVOUtil.success(or);
    }


    @PostMapping("/addOrderQuantity")
    @ApiOperation(value = "新增",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> addOrderQuantity(@ApiParam(value = "结算类型订单量VM") @RequestBody() OrderQuantityAddVM vo){

        return orderquantityService.addOrderQuantity(vo);
    }


    @PostMapping("/batchDelete")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> batchDelete(@ApiParam(value = "名称",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","ids不能为空");
        }

        return orderquantityService.batchDelete(ids);
    }


    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件，分页查询",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderQuantityWithCPListVM> findByConditions(@ApiParam(value = "名称") @RequestParam(value = "name",required = false )String name,
                                                                @ApiParam(value = "编码") @RequestParam(value = "code",required = false)String code,
                                                                @ApiParam(value = "状态") @RequestParam(value = "status",required = false)String status,
                                                                @ApiParam(value = "当前页",required = true,example = "1") @RequestParam(value = "pageNum")String pageNum,
                                                                @ApiParam(value = "当前页数量",required = true,example = "10") @RequestParam(value = "pageSize")String pageSize){

        Sort sort = new Sort(Sort.Direction.DESC,"inputTime");
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNum) -1 ,Integer.parseInt(pageNum),sort);
        Page<OrderQuantityWithCPListVM> byConditions = orderquantityService.findByConditions(name, code, status, pageable);
        return byConditions;
    }




    @PutMapping("/updateOrderQuantity")
    @ApiOperation(value = "修改",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> updateOrderQuantity(@ApiParam(value = "名称") @RequestBody() OrderQuantityAddVM vo){
        return orderquantityService.updateOrderQuantity(vo);
    }



   @GetMapping("/queryCPList")
    @ApiOperation(value = "查询CP列表")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> queryCPList(){
        List<Cp> all = cpRepository.findAll();
        return ResultVOUtil.success(all);
    }

    @GetMapping("/getOrderQuantityWithCp")
    @ApiOperation(value = "通过结算类型订单量与CP关系表的编码查询",notes = "返回json数据")
    @ResponseStatus(HttpStatus.OK)
    public OrderQuantityWithCPListVM getOrderQuantityWithCp(@ApiParam(value = "编码",required = true) @RequestParam("code")String code){
        if (StringUtils.isBlank(code)){
            new IllegalArgumentException("Code不能为空");
        }

        return orderquantityService.getOrderQuantityWithCp(code);
    }


}
