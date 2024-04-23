package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.Domicilio;
import com.mycompany.electronicagaletto.logica.Pago;
import com.mycompany.electronicagaletto.logica.Venta;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-18T19:05:10", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Boolean> estado;
    public static volatile SingularAttribute<Cliente, Integer> idCliente;
    public static volatile ListAttribute<Cliente, Pago> listaPagos;
    public static volatile SingularAttribute<Cliente, String> apellido;
    public static volatile SingularAttribute<Cliente, Domicilio> domi;
    public static volatile ListAttribute<Cliente, Venta> listaVentas;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile ListAttribute<Cliente, Devolucion> listaDevolucion;
    public static volatile SingularAttribute<Cliente, Integer> dni;
    public static volatile SingularAttribute<Cliente, String> email;

}