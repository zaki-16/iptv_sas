package generated;

import com.hgys.iptv.model.CpOrderBusinessComparison;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

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

	public static final String MASTER_CODE = "masterCode";
	public static final String PROPORTION = "proportion";
	public static final String MONEY = "money";
	public static final String CREATE_TIME = "create_time";
	public static final String CP_NAME = "cp_name";
	public static final String ID = "id";
	public static final String CP_CODE = "cp_code";

}

