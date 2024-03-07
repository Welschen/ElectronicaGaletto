package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Presupuesto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-07T21:56:33", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ItemPresupuesto.class)
public class ItemPresupuesto_ { 

    public static volatile SingularAttribute<ItemPresupuesto, Articulo> artic;
    public static volatile SingularAttribute<ItemPresupuesto, Integer> idItemPresupuesto;
    public static volatile SingularAttribute<ItemPresupuesto, Integer> cantiArticulo;
    public static volatile SingularAttribute<ItemPresupuesto, Double> precioPresup;
    public static volatile SingularAttribute<ItemPresupuesto, Presupuesto> presupuest;

}