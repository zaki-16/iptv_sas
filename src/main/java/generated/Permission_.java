package generated;

import com.hgys.iptv.model.Permission;
import com.hgys.iptv.model.Role;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile ListAttribute<Permission, Role> roles;
	public static volatile SingularAttribute<Permission, String> name;
	public static volatile SingularAttribute<Permission, String> permission;
	public static volatile SingularAttribute<Permission, Long> id;
	public static volatile SingularAttribute<Permission, String> uri;

	public static final String ROLES = "roles";
	public static final String NAME = "name";
	public static final String PERMISSION = "permission";
	public static final String ID = "id";
	public static final String URI = "uri";

}

