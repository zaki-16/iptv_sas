package com.hgys.iptv.repository;

import com.hgys.iptv.model.BusinessComparisonRelation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessComparisonRelationRepository extends JpaRepository<BusinessComparisonRelation,Object>, JpaSpecificationExecutor<BusinessComparisonRelation> {

    List<BusinessComparisonRelation> findByMasterCode(String masterCode);

}
