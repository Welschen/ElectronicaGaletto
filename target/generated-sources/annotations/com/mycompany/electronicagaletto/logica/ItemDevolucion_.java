package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Devolucion;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-18T19:05:10", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ItemDevolucion.class)
public class ItemDevolucion_ { 

    public static volatile SingularAttribute<ItemDevolucion, Double> precioDevol;
    public static volatile SingularAttribute<ItemDevolucion, Boolean> cambio;
    public static volatile SingularAttribute<ItemDevolucion, Devolucion> devo;
    public static volatile SingularAttribute<ItemDevolucion, Articulo> arti;
    public static volatile SingularAttribute<ItemDevolucion, Integer> cantidadDevuelta;
    public static volatile SingularAttribute<ItemDevolucion, Integer> idItemDevolucion;

}