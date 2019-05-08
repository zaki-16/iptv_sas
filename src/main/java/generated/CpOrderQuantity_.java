package generated;

import com.hgys.iptv.model.CpOrderQuantity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CpOrderQuantity.class)
public abstract class CpOrderQuantity_ {

	public static volatile SingularAttribute<CpOrderQuantity, Timestamp> createtime;
	public static volatile SingularAttribute<CpOrderQuantity, String> cpname;
	public static volatile SingularAttribute<CpOrderQuantity, String> oqname;
	public static volatile SingularAttribute<CpOrderQuantity, Integer> id;
	public static volatile SingularAttribute<CpOrderQuantity, Integer> isdelete;
	public static volatile SingularAttribute<CpOrderQuantity, Integer> col2;
	public static volatile SingularAttribute<CpOrderQuantity, String> col3;
	public static volatile SingularAttribute<CpOrderQuantity, String> col1;

	public static final String CREATETIME = "createtime";
	public static final String CPNAME = "cpname";
	public static final String OQNAME = "oqname";
	public static final String ID = "id";
	public static final String ISDELETE = "isdelete";
	public static final String COL2 = "col2";
	public static final String COL3 = "col3";
	public static final String COL1 = "col1";

}

