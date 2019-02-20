package Kwetter.model.Models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Kweet.class)
public abstract class Kweet_ {

	public static volatile SingularAttribute<Kweet, Date> date;
	public static volatile SingularAttribute<Kweet, Profile> owner;
	public static volatile SingularAttribute<Kweet, Long> id;
	public static volatile SingularAttribute<Kweet, String> content;

}

