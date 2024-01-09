package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Venta;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-08T15:34:24", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ItemVenta.class)
public class ItemVenta_ { 

    public static volatile SingularAttribute<ItemVenta, Integer> idItemVenta;
    public static volatile SingularAttribute<ItemVenta, Double> precioVta;
    public static volatile SingularAttribute<ItemVenta, Articulo> articu;
    public static volatile SingularAttribute<ItemVenta, Integer> cantidad;
    public static volatile SingularAttribute<ItemVenta, Venta> ven;

}