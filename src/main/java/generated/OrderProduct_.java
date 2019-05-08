package generated;

import com.hgys.iptv.model.OrderProduct;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProduct.class)
public abstract class OrderProduct_ {

	public static volatile SingularAttribute<OrderProduct, String> note;
	public static volatile SingularAttribute<OrderProduct, String> code;
	public static volatile SingularAttribute<OrderProduct, Timestamp> modifyTime;
	public static volatile SingularAttribute<OrderProduct, String> name;
	public static volatile SingularAttribute<OrderProduct, Timestamp> inputTime;
	public static volatile SingularAttribute<OrderProduct, Integer> id;
	public static volatile SingularAttribute<OrderProduct, Integer> isdelete;
	public static volatile SingularAttribute<OrderProduct, Integer> col2;
	public static volatile SingularAttribute<OrderProduct, String> col3;
	public static volatile SingularAttribute<OrderProduct, Integer> status;
	public static volatile SingularAttribute<OrderProduct, String> col1;

	public static final String NOTE = "note";
	public static final String CODE = "code";
	public static final String MODIFY_TIME = "modifyTime";
	public static final String NAME = "name";
	public static final String INPUT_TIME = "inputTime";
	public static final String ID = "id";
	public static final String ISDELETE = "isdelete";
	public static final String COL2 = "col2";
	public static final String COL3 = "col3";
	public static final String STATUS = "status";
	public static final String COL1 = "col1";

}

