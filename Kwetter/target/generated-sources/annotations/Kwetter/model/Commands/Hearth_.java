package kwetter.model.commands;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Hearth.class)
public abstract class Hearth_ {

	public static volatile SingularAttribute<Hearth, Profile> owner;
	public static volatile SingularAttribute<Hearth, Kweet> kweet;
	public static volatile SingularAttribute<Hearth, Long> id;

}

