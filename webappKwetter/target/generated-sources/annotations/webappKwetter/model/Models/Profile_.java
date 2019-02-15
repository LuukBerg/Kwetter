package webappKwetter.model.Models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profile.class)
public abstract class Profile_ {

	public static volatile SingularAttribute<Profile, User> owner;
	public static volatile ListAttribute<Profile, Profile> followers;
	public static volatile ListAttribute<Profile, Profile> following;
	public static volatile ListAttribute<Profile, Kweet> kweets;
	public static volatile SingularAttribute<Profile, Details> details;
	public static volatile SingularAttribute<Profile, Long> id;

}

