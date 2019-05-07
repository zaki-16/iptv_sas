package com.hgys.iptv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Business.class)
public abstract class Business_ {

	public static volatile SingularAttribute<Business, String> code;
	public static volatile SingularAttribute<Business, Integer> bizType;
	public static volatile SingularAttribute<Business, Timestamp> modifyTime;
	public static volatile ListAttribute<Business, Cp> cpList;
	public static volatile SingularAttribute<Business, String> name;
	public static volatile SingularAttribute<Business, Timestamp> inputTime;
	public static volatile SingularAttribute<Business, Integer> isdelete;
	public static volatile SingularAttribute<Business, Integer> bid;
	public static volatile SingularAttribute<Business, Integer> settleType;
	public static volatile ListAttribute<Business, Product> productList;
	public static volatile SingularAttribute<Business, Integer> status;

}

