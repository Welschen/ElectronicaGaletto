package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.ItemPresupuesto;
import com.mycompany.electronicagaletto.logica.Venta;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-07T21:56:33", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Presupuesto.class)
public class Presupuesto_ { 

    public static volatile SingularAttribute<Presupuesto, Venta> venta;
    public static volatile ListAttribute<Presupuesto, ItemPresupuesto> listaPresupuesto;
    public static volatile SingularAttribute<Presupuesto, Integer> idPresupuesto;
    public static volatile SingularAttribute<Presupuesto, Double> totalPresup;
    public static volatile SingularAttribute<Presupuesto, Date> fechaPresup;

}