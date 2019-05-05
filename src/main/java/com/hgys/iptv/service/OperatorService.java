package com.hgys.iptv.service;

import com.hgys.iptv.model.Operator;

import java.util.List;

public interface OperatorService {
    Operator findByOpNm(String name);

    Operator save(Operator op);

    List<Operator> findAll();

    void delete(Operator op);

}
