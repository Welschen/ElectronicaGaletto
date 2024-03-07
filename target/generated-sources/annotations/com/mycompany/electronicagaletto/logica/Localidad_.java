package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Domicilio;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-07T21:56:33", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Localidad.class)
public class Localidad_ { 

    public static volatile ListAttribute<Localidad, Domicilio> listaDomicilios;
    public static volatile SingularAttribute<Localidad, Integer> codigoPostal;
    public static volatile SingularAttribute<Localidad, String> localidad;
    public static volatile SingularAttribute<Localidad, String> provincia;
    public static volatile SingularAttribute<Localidad, Integer> idLocalidad;

}