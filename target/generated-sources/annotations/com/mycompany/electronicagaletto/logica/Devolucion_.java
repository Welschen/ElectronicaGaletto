package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.logica.Venta;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-08T15:34:24", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Devolucion.class)
public class Devolucion_ { 

    public static volatile ListAttribute<Devolucion, ItemDevolucion> itemDev;
    public static volatile SingularAttribute<Devolucion, Venta> venta;
    public static volatile SingularAttribute<Devolucion, Date> fechaDevolucion;
    public static volatile SingularAttribute<Devolucion, Double> totalDevolucion;
    public static volatile SingularAttribute<Devolucion, Usuario> usuario;
    public static volatile SingularAttribute<Devolucion, Integer> idDevolucion;

}