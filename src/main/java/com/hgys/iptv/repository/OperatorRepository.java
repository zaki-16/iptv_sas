package com.hgys.iptv.repository;

import com.hgys.iptv.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends JpaRepository<Operator,Integer> {
        Operator findByOpNm(String name);
}
