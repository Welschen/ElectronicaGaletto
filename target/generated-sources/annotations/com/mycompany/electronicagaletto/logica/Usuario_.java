package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Auditoria;
import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.Pago;
import com.mycompany.electronicagaletto.logica.Venta;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-07T21:56:33", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile ListAttribute<Usuario, Venta> listaVenta;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile ListAttribute<Usuario, Pago> listaPago;
    public static volatile SingularAttribute<Usuario, String> contrasenia;
    public static volatile SingularAttribute<Usuario, String> id;
    public static volatile ListAttribute<Usuario, Devolucion> listaDevolucion;
    public static volatile SingularAttribute<Usuario, String> rol;
    public static volatile ListAttribute<Usuario, Auditoria> listaAuditoria;

}