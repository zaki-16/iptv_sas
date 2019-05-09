package com.hgys.iptv.controller;

import com.hgys.iptv.controller.vm.NoteAddControllerVM;
import com.hgys.iptv.controller.vm.NoteQueryControllerVM;
import com.hgys.iptv.controller.vm.SettlementDimensionControllerListVM;
import com.hgys.iptv.model.Note;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.NoteService;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noteController")
@Api(value = "noteController",tags = "备注模板Api接口")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/addNote")
    @ApiOperation(value = "新增结算类型-业务定比例",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> addNote(@ApiParam(value = "新增备注模板VM") @RequestBody() NoteAddControllerVM vm){
        return noteService.addNote(vm);
    }


    @DeleteMapping("/batchLogicDelete")
    @ApiOperation(value = "通过Id批量逻辑删除",notes = "返回处理结果，false或true")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO batchLogicDelete(@ApiParam(value = "备注模板ids",required = true) @RequestParam("ids")String ids){

        if (StringUtils.isBlank(ids)){
            return ResultVOUtil.error("1","备注模板ids不能为空");
        }

        ResultVO<?> resultVO = noteService.batchLogicDelete(ids);
        return resultVO;
    }

    @GetMapping("/findByConditions")
    @ApiOperation(value = "通过条件查询",notes = "返回Json数据")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO<?> findByConditions(@ApiParam(value = "结算单维度名称") @RequestParam(value = "content",required = false )String content,
                                                                      @ApiParam(value = "用户id(0:表示公共类型)") @RequestParam(value = "userId",required = false)String userId,
                                                                      @ApiParam(value = "类型") @RequestParam(value = "noteType",required = false)String noteType){
        Sort sort = new Sort(Sort.Direction.DESC,"create_time");
        List<NoteQueryControllerVM> byConditions = noteService.findByConditions(content, userId, noteType);
        return ResultVOUtil.success(byConditions);
    }
}
