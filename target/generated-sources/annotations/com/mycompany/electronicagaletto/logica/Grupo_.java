package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Articulo;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-07T21:56:33", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile SingularAttribute<Grupo, String> nombreGrupo;
    public static volatile SingularAttribute<Grupo, Double> beneficio;
    public static volatile SingularAttribute<Grupo, Boolean> estado;
    public static volatile SingularAttribute<Grupo, Integer> bajoStock;
    public static volatile ListAttribute<Grupo, Articulo> articulo;
    public static volatile SingularAttribute<Grupo, Integer> idGrupo;

}