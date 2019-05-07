package com.hgys.iptv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cp.class)
public abstract class Cp_ {

	public static volatile SingularAttribute<Cp, String> contactNm;
	public static volatile SingularAttribute<Cp, String> note;
	public static volatile SingularAttribute<Cp, String> code;
	public static volatile SingularAttribute<Cp, Integer> cpid;
	public static volatile SingularAttribute<Cp, String> cpAbbr;
	public static volatile SingularAttribute<Cp, String> contactTel;
	public static volatile SingularAttribute<Cp, Timestamp> regisTime;
	public static volatile SingularAttribute<Cp, Timestamp> modifyTime;
	public static volatile SingularAttribute<Cp, Timestamp> cancelTime;
	public static volatile SingularAttribute<Cp, String> name;
	public static volatile SingularAttribute<Cp, String> contactMail;
	public static volatile SingularAttribute<Cp, Integer> isdelete;
	public static volatile ListAttribute<Cp, Business> businessList;
	public static volatile ListAttribute<Cp, Product> productList;
	public static volatile SingularAttribute<Cp, Integer> status;

}

