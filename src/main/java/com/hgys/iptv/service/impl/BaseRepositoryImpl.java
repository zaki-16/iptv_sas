package com.hgys.iptv.service.impl;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.JpaPersistableEntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Metamodel;

/**
 * @ClassName BaseRepositoryImpl
 * @Auther: wangz
 * @Date: 2019/5/6 22:56
 * @Description: TODO
 */
public class BaseRepositoryImpl {


    @SuppressWarnings({"rawtypes","unchecked"})
    public static <T> JpaEntityInformation<T,?> getMetadata(Class<T> domainClass, EntityManager em){
        Metamodel metamodel=em.getMetamodel();
        if (Persistable.class.isAssignableFrom(domainClass)) {
            return new JpaPersistableEntityInformation(domainClass,metamodel);
        }
        else {
            return new JpaMetamodelEntityInformation(domainClass,metamodel);
        }
    }
}
