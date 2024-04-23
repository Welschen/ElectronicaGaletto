package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Grupo;
import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.logica.ItemPresupuesto;
import com.mycompany.electronicagaletto.logica.ItemVenta;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-18T19:05:10", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Articulo.class)
public class Articulo_ { 

    public static volatile SingularAttribute<Articulo, Integer> idArticulo;
    public static volatile SingularAttribute<Articulo, Double> precio;
    public static volatile ListAttribute<Articulo, ItemDevolucion> itemDevol;
    public static volatile ListAttribute<Articulo, ItemVenta> itemVta;
    public static volatile SingularAttribute<Articulo, Boolean> estado;
    public static volatile SingularAttribute<Articulo, Double> costo;
    public static volatile SingularAttribute<Articulo, String> codBarra;
    public static volatile SingularAttribute<Articulo, Grupo> grupo;
    public static volatile SingularAttribute<Articulo, Integer> stock;
    public static volatile ListAttribute<Articulo, ItemPresupuesto> itemPresu;
    public static volatile SingularAttribute<Articulo, String> nombreArticulo;

}