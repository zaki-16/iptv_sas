package com.hgys.iptv.service;

import com.hgys.iptv.model.Cp;

import java.util.List;

public interface CpService {
    Cp saveOrUpdate(Cp cp);
    void delete(Cp cp);
    void batchDelete(List<Cp> cps);
    Cp findById(Integer id);
    List<Cp> findAll();
}
