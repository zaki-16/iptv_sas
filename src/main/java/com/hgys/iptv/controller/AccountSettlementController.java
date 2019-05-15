package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.AccountSettlementAddVM;
import com.hgys.iptv.model.OrderProduct;
import com.hgys.iptv.model.bean.CpOrderCpExcelDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionListDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderProductRepository;
import com.hgys.iptv.service.AccountSettlementService;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.excel.ExcelForWebUtil;
import com.xuxueli.poi.excel.ExcelExportUtil;
import com.xuxueli.poi.excel.ExcelImportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/accountSettlementController")
@Api(value = "accountSettlementController",tags = "分账结算Api接口")
public class AccountSettlementController {

    @Autowired
    private AccountSettlementService accountSettlementService;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @RequestMapping(value = "/accountSettlement", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    @ApiOperation(value = "新增分配结算",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> addAccountSettlement(@ApiParam(value = "新增分配结算VM") @RequestBody AccountSettlementAddVM vm){
        //数据校验（1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算）
        if (StringUtils.isBlank(vm.getName())){
            ResultVOUtil.error("1","分配结算名称不能为空！");
        }else if (null == vm.getSet_type()){
            ResultVOUtil.error("1","分配结算类型不能为空！");
        }else if (StringUtils.isBlank(vm.getSet_ruleCode())){
            ResultVOUtil.error("1","分配结算结算规则编码不能为空！");
        }else if (StringUtils.isBlank(vm.getStartTime())){
            ResultVOUtil.error("1","分配结算结算开始时间不能为空！");
        }else if (StringUtils.isBlank(vm.getEndTime())){
            ResultVOUtil.error("1","分配结算结算截止时间不能为空！");
        }

        if (1 == vm.getSet_type()){
            if (vm.getCpAddVMS().isEmpty()){
                ResultVOUtil.error("1","分配结算订购量信息集合不能为空！");
            }
        }else if (2 == vm.getSet_type()){
            if (StringUtils.isBlank(vm.getBusinessMoney().toString())){
                ResultVOUtil.error("1","分配结算业务级结算总收入不能为空！");
            }
        }else if (3 == vm.getSet_type()){
            OrderProduct byCode = orderProductRepository.findByCode(vm.getSet_ruleCode().trim());
            //查看是单维度还是多维度
            Integer mode = byCode.getMode();
            if (mode == 1){
                if (vm.getDimensionAddVM().isEmpty()){
                    ResultVOUtil.error("1","分配结算产品级单维度信息集合不能为空！");
                }else {
                    ResultVOUtil.error("1","分配结算产品级多维度信息集合不能为空！");
                }
            }
        }else if (4 == vm.getSet_type()){
            if (StringUtils.isBlank(vm.getCpAllMoney().toString())){
                ResultVOUtil.error("1","分配结算CP定比例结算总收入不能为空！");
            }
        }else if (5 == vm.getSet_type()){
            if (vm.getBelielAddVMS().isEmpty()){
                ResultVOUtil.error("1","分配结算业务定比例结算信息集合不能为空！");
            }
        }
        return accountSettlementService.addAccountSettlement(vm);
    }

    @GetMapping("/excelExport")
    @ApiOperation(value = "根据选择查询数据并返回excel",notes = "如果查询到数据则会返回execl文件，如没有数据则不会返回文件")
    @ResponseStatus(HttpStatus.CREATED)
    public void excelExport(@ApiParam(value = "分配结算类型",required = true) @RequestParam(value = "type") Integer type,
                            @ApiParam(value = "编码",required = true) @RequestParam(value = "code") String code,
                            HttpServletResponse response){
        if (type == 1){
            List<CpOrderCpExcelDTO> dtos = (List<CpOrderCpExcelDTO>) accountSettlementService.excelExport(type, code);
            //浏览器返回Excel
            Workbook sheets = ExcelExportUtil.exportWorkbook(dtos);
            ExcelForWebUtil.workBookExportExcel(response,sheets,"订购量结算");
        }else if (type == 3){
            //查询是单维度还是多维度
            OrderProduct byCode = orderProductRepository.findByCode(code);
            if (byCode.getMode() == 1){
                List<OrderProductDimensionDTO> result = (List<OrderProductDimensionDTO>) accountSettlementService.excelExport(type,code);
                //浏览器返回Excel
                Workbook sheets = ExcelExportUtil.exportWorkbook(result);
                ExcelForWebUtil.workBookExportExcel(response,sheets,"产品级单维度结算");
            }else {
                List<OrderProductDimensionListDTO> result = (List<OrderProductDimensionListDTO>) accountSettlementService.excelExport(type,code);
                //浏览器返回Excel
                Workbook sheets = ExcelExportUtil.exportWorkbook(result);
                ExcelForWebUtil.workBookExportExcel(response,sheets,"产品级多维度结算");
            }
        }
    }

    @PostMapping("/excelImport")
    @ApiOperation(value = "导入数据",notes = "如果查询到数据则会返回execl文件，如没有数据则不会返回文件")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO<?> excelImport(@ApiParam(value = "分配结算类型",required = true) @RequestParam(value = "type") Integer type,
                            @ApiParam(value = "编码",required = true) @RequestParam(value = "code") String code,
                            @ApiParam(value = "Excel",required = true) @RequestParam MultipartFile multipartFile) throws IOException {


        if (type == 1){
            List<Object> list = ExcelImportUtil.importExcel(multipartFile.getInputStream(), CpOrderCpExcelDTO.class);
            List<CpOrderCpExcelDTO> dtos = new ArrayList<>();
            for (Object o : list){
                CpOrderCpExcelDTO dto = (CpOrderCpExcelDTO)o;
                dtos.add(dto);
            }
            //验证数据可靠性，通过返回数据
            ResultVO<?> resultVO = accountSettlementService.checkCp(dtos);
            if ("0".equals(resultVO.getCode())){
                return ResultVOUtil.success(dtos);
            }else {
                return resultVO;
            }
        }else if (type == 3){
            //查询是单维度还是多维度
            OrderProduct byCode = orderProductRepository.findByCode(code);
            if (byCode.getMode() == 1){
                List<Object> list = ExcelImportUtil.importExcel(multipartFile.getInputStream(), OrderProductDimensionDTO.class);
                List<OrderProductDimensionDTO> dtos = new ArrayList<>();
                for (Object o : list){
                    OrderProductDimensionDTO dto = (OrderProductDimensionDTO)o;
                    dtos.add(dto);
                }
                //验证数据可靠性，通过返回数据
                ResultVO<?> resultVO = accountSettlementService.checkCpAndDimension(dtos);
                if ("0".equals(resultVO.getCode())){
                    return ResultVOUtil.success(dtos);
                }else {
                    return resultVO;
                }
            }else {
                List<Object> list = ExcelImportUtil.importExcel(multipartFile.getInputStream(), OrderProductDimensionListDTO.class);
                List<OrderProductDimensionListDTO> dtos = new ArrayList<>();
                for (Object o : list){
                    OrderProductDimensionListDTO dto = (OrderProductDimensionListDTO)o;
                    dtos.add(dto);
                }
                //验证数据可靠性，通过返回数据
                ResultVO<?> resultVO = accountSettlementService.checkCpAndDimensionList(dtos);
                if ("0".equals(resultVO.getCode())){
                    return ResultVOUtil.success(dtos);
                }else {
                    return resultVO;
                }
            }
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    @DeleteMapping("/batchLogicDelete")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO batchLogicDelete(@ApiParam(value = "结算单维度ids",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","结算单维度ids不能为空");
        }

        ResultVO<?> resultVO = accountSettlementService.batchLogicDelete(ids);
        return resultVO;
    }

}
