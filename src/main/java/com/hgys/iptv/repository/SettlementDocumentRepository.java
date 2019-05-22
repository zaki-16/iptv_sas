package com.hgys.iptv.repository;

import com.hgys.iptv.model.AccountSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SettlementDocumentRepository extends JpaRepository<AccountSettlement,Object>, JpaSpecificationExecutor<AccountSettlement> {
}
