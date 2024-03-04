package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Localidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-04T20:51:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Domicilio.class)
public class Domicilio_ { 

    public static volatile SingularAttribute<Domicilio, Integer> piso;
    public static volatile SingularAttribute<Domicilio, String> calle;
    public static volatile SingularAttribute<Domicilio, Integer> numCalle;
    public static volatile SingularAttribute<Domicilio, Character> depto;
    public static volatile SingularAttribute<Domicilio, Localidad> local;
    public static volatile SingularAttribute<Domicilio, Integer> idDomicilio;

}