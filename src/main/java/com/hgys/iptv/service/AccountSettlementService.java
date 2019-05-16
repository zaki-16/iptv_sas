package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.AccountSettlementAddVM;
import com.hgys.iptv.model.bean.CpOrderCpExcelDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionListDTO;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AccountSettlementService {

    ResultVO<?> addAccountSettlement(AccountSettlementAddVM vm);

    List<?> excelExport(Integer type,String code);

    ResultVO<?> checkCp(List<CpOrderCpExcelDTO> dtos);

    ResultVO<?> checkCpAndDimension(List<OrderProductDimensionDTO> dtos);

    ResultVO<?> checkCpAndDimensionList(List<OrderProductDimensionListDTO> dtos);

    ResultVO<?> batchLogicDelete(String ids);

    AccountSettlementAddVM findById(String id);

    /**
     * 根据条件分页查询
     * @return
     */
    Page<AccountSettlementAddVM> findByConditions(String name, String code, String status, Pageable pageable);

}
