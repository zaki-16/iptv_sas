package com.hgys.iptv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CpOrderBusinessComparison.class)
public abstract class CpOrderBusinessComparison_ {

	public static volatile SingularAttribute<CpOrderBusinessComparison, String> masterCode;
	public static volatile SingularAttribute<CpOrderBusinessComparison, Integer> proportion;
	public static volatile SingularAttribute<CpOrderBusinessComparison, Integer> money;
	public static volatile SingularAttribute<CpOrderBusinessComparison, Timestamp> create_time;
	public static volatile SingularAttribute<CpOrderBusinessComparison, String> cp_name;
	public static volatile SingularAttribute<CpOrderBusinessComparison, Integer> id;
	public static volatile SingularAttribute<CpOrderBusinessComparison, String> cp_code;

}

