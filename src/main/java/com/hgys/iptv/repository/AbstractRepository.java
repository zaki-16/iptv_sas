package com.hgys.iptv.repository;

import java.util.List;

public interface AbstractRepository<T> {
    List<T> findListById(Class clazz, Integer id);
}
