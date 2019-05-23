package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.SettlementDocumentCPListExcelVM;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SettlementDocumentService {

    Page<SettlementDocumentQueryListVM> findByConditions(String name, String code, Pageable pageable);

    ResultVO<?> findByIdQueryCpList(Integer id);

    ResultVO<?> settlementDocumentQueryCpMySelfList(String cpCode);

    SettlementDocumentCPListExcelVM settlementCpExcel(Integer id);
}
