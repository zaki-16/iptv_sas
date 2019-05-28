package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.SettlementDocumentCPListExcelVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;


public interface SettlementDocumentService {

    Page<SettlementDocumentQueryListVM> findByConditions(String name, String code, Pageable pageable);

    Page<SettlementDocumentCPListExcelVM> documentQueryHistoryCpMySelfList(Integer masterId,String cpCode,String pageNum,String pageSize);

    Page<SettlementDocumentQueryListVM> documentHistoryQueryCpList(Integer masterId,String pageNum,String pageSize);

    ResultVO<?> findByIdQueryCpList(Integer id);

    ResultVO<?> settlementDocumentQueryCpMySelfList(Integer id);

    void excelSettlementInfo(Integer masterId, HttpServletResponse response);

    void excelSettlementInfoMode(Integer masterId, HttpServletResponse response);

    void excelCpSettlementInfo(Integer id,HttpServletResponse response);
}
