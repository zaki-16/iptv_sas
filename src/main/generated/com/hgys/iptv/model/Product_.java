package com.hgys.iptv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> code;
	public static volatile SingularAttribute<Product, Timestamp> modifyTime;
	public static volatile ListAttribute<Product, Cp> cpList;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Timestamp> inputTime;
	public static volatile SingularAttribute<Product, Integer> pid;
	public static volatile SingularAttribute<Product, Integer> isdelete;
	public static volatile ListAttribute<Product, Business> businessList;
	public static volatile SingularAttribute<Product, Integer> status;

}

