package com.hgys.iptv.repository;

import com.hgys.iptv.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business,Integer> {
    Business findByBid(Integer bid);
    Business findByCode(String code);

    List<Business> findByStatusAndIsdelete(int status, int Isdelete);
}
