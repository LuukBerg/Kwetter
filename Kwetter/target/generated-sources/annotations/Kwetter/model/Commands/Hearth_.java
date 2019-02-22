package Kwetter.model.Commands;

import Kwetter.model.Models.Kweet;
import Kwetter.model.Models.Profile;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Hearth.class)
public abstract class Hearth_ {

	public static volatile SingularAttribute<Hearth, Profile> owner;
	public static volatile SingularAttribute<Hearth, Kweet> kweet;
	public static volatile SingularAttribute<Hearth, Long> id;

}

