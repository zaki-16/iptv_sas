package generated;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Product;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Business.class)
public abstract class Business_ {

	public static volatile SingularAttribute<Business, String> code;
	public static volatile SingularAttribute<Business, Integer> bizType;
	public static volatile SingularAttribute<Business, Timestamp> modifyTime;
	public static volatile ListAttribute<Business, Cp> cpList;
	public static volatile SingularAttribute<Business, String> name;
	public static volatile SingularAttribute<Business, Timestamp> inputTime;
	public static volatile SingularAttribute<Business, Integer> id;
	public static volatile SingularAttribute<Business, Integer> isdelete;
	public static volatile SingularAttribute<Business, Integer> settleType;
	public static volatile ListAttribute<Business, Product> productList;
	public static volatile SingularAttribute<Business, Integer> status;

	public static final String CODE = "code";
	public static final String BIZ_TYPE = "bizType";
	public static final String MODIFY_TIME = "modifyTime";
	public static final String CP_LIST = "cpList";
	public static final String NAME = "name";
	public static final String INPUT_TIME = "inputTime";
	public static final String ID = "id";
	public static final String ISDELETE = "isdelete";
	public static final String SETTLE_TYPE = "settleType";
	public static final String PRODUCT_LIST = "productList";
	public static final String STATUS = "status";

}

