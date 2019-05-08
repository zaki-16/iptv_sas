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
@StaticMetamodel(Cp.class)
public abstract class Cp_ {

	public static volatile SingularAttribute<Cp, String> contactNm;
	public static volatile SingularAttribute<Cp, String> note;
	public static volatile SingularAttribute<Cp, String> code;
	public static volatile SingularAttribute<Cp, String> cpAbbr;
	public static volatile SingularAttribute<Cp, String> contactTel;
	public static volatile SingularAttribute<Cp, Timestamp> regisTime;
	public static volatile SingularAttribute<Cp, Timestamp> modifyTime;
	public static volatile SingularAttribute<Cp, Timestamp> cancelTime;
	public static volatile SingularAttribute<Cp, String> name;
	public static volatile SingularAttribute<Cp, Integer> id;
	public static volatile SingularAttribute<Cp, String> contactMail;
	public static volatile SingularAttribute<Cp, Integer> isdelete;
	public static volatile ListAttribute<Cp, Business> businessList;
	public static volatile ListAttribute<Cp, Product> productList;
	public static volatile SingularAttribute<Cp, Integer> status;

	public static final String CONTACT_NM = "contactNm";
	public static final String NOTE = "note";
	public static final String CODE = "code";
	public static final String CP_ABBR = "cpAbbr";
	public static final String CONTACT_TEL = "contactTel";
	public static final String REGIS_TIME = "regisTime";
	public static final String MODIFY_TIME = "modifyTime";
	public static final String CANCEL_TIME = "cancelTime";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String CONTACT_MAIL = "contactMail";
	public static final String ISDELETE = "isdelete";
	public static final String BUSINESS_LIST = "businessList";
	public static final String PRODUCT_LIST = "productList";
	public static final String STATUS = "status";

}

