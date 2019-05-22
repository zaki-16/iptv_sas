package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.SettlementDocumentControllerAssemlber;
import com.hgys.iptv.controller.vm.SettlementDocumentQueryListVM;
import com.hgys.iptv.repository.SettlementDocumentRepository;
import com.hgys.iptv.service.SettlementDocumentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SettlementDocumentServiceImpl implements SettlementDocumentService {

    @Autowired
    private SettlementDocumentRepository settlementDocumentRepository;

    @Autowired
    private SettlementDocumentControllerAssemlber settlementDocumentControllerAssemlber;

    /**
     * 查询结算文档列表信息
     * @param name
     * @param code
     * @param pageable
     * @return
     */
    @Override
    public Page<SettlementDocumentQueryListVM> findByConditions(String name, String code, Pageable pageable) {
       return settlementDocumentRepository.findAll(((root, query, builder) -> {
           List<Predicate> predicates = new ArrayList<>();
           if (StringUtils.isNotBlank(name)){
               Predicate condition = builder.like(root.get("name"), "%"+name+"%");
               predicates.add(condition);
           }
           if (StringUtils.isNotBlank(code)){
               Predicate condition = builder.like(root.get("code"), "%"+code+"%");
               predicates.add(condition);
           }

           /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回;7:已结算*/
           Predicate condition = builder.equal(root.get("status"), 7);
           predicates.add(condition);

           if (!predicates.isEmpty()){
               return builder.and(predicates.toArray(new Predicate[0]));
           }
           return builder.conjunction();
        }),pageable).map(settlementDocumentControllerAssemlber :: getListVM);
    }
}
