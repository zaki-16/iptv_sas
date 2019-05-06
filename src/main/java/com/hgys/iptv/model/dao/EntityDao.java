//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hgys.iptv.model.dao;

import com.hgys.iptv.model.vo.QueryResult;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface EntityDao {
    <T> T get(Class<T> var1, Object var2) throws SQLException;

    <T> T get(Class<T> var1, String var2, Object[] var3) throws SQLException;

    <T> QueryResult<T> getPagingData(Class<T> var1, int var2, int var3, String var4, Object[] var5, LinkedHashMap<String, String> var6) throws SQLException;

    <T> QueryResult<T> getPagingData(Class<T> var1, int var2, int var3, String var4, Object[] var5) throws SQLException;

    <T> QueryResult<T> getPagingData(Class<T> var1, int var2, int var3, LinkedHashMap<String, String> var4) throws SQLException;

    <T> QueryResult<T> getPagingData(Class<T> var1, int var2, int var3) throws SQLException;

    <T> QueryResult<T> getPagingData(Class<T> var1, String var2, Object[] var3) throws SQLException;

    <T> QueryResult<T> getPagingData(Class<T> var1) throws SQLException;

    <T> List<T> getPagingDataByJpql(Class<T> var1, String var2, Object[] var3, int var4, int var5) throws SQLException;

    List<Map<String, Object>> getPagingDataByHql(String var1, Object[] var2, int var3, int var4) throws SQLException;

    List<Map<String, Object>> getPagingDataBySql(String var1, Object[] var2, int var3, int var4) throws SQLException;

    <T> List<T> getPagingDataBySql(Class<T> var1, String var2, Object[] var3, int var4, int var5) throws SQLException;

    Integer save(Object var1) throws SQLException;

    boolean update(Object var1) throws SQLException;

    void delete(Object var1) throws SQLException;

    EntityManager getEntityManager();
}
