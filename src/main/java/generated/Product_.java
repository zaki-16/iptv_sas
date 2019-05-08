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
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> code;
	public static volatile SingularAttribute<Product, Timestamp> modifyTime;
	public static volatile ListAttribute<Product, Cp> cpList;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Timestamp> inputTime;
	public static volatile SingularAttribute<Product, Integer> id;
	public static volatile SingularAttribute<Product, Integer> isdelete;
	public static volatile ListAttribute<Product, Business> businessList;
	public static volatile SingularAttribute<Product, Integer> status;

	public static final String CODE = "code";
	public static final String MODIFY_TIME = "modifyTime";
	public static final String CP_LIST = "cpList";
	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String INPUT_TIME = "inputTime";
	public static final String ID = "id";
	public static final String ISDELETE = "isdelete";
	public static final String BUSINESS_LIST = "businessList";
	public static final String STATUS = "status";

}

