package kwetter.model.commands;

import kwetter.model.models.Profile;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(React.class)
public abstract class React_ extends kwetter.model.commands.Command_ {

	public static volatile SingularAttribute<React, Profile> owner;
	public static volatile SingularAttribute<React, Long> id;
	public static volatile SingularAttribute<React, String> content;

}

