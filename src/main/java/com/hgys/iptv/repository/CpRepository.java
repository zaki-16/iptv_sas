package com.hgys.iptv.repository;

import com.hgys.iptv.model.Cp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpRepository extends JpaRepository<Cp,Integer> {
}
