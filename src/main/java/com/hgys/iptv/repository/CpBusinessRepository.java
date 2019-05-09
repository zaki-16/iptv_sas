package com.hgys.iptv.repository;

import com.hgys.iptv.model.CpBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CpBusinessRepository extends JpaRepository<CpBusiness,Integer>, JpaSpecificationExecutor<CpBusiness> {
        
}
