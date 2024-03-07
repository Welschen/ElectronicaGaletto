package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-03-07T21:56:33", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pago.class)
public class Pago_ { 

    public static volatile SingularAttribute<Pago, Double> monto;
    public static volatile SingularAttribute<Pago, Usuario> usu;
    public static volatile SingularAttribute<Pago, Integer> idPago;
    public static volatile SingularAttribute<Pago, Date> horaPago;
    public static volatile SingularAttribute<Pago, String> formaPago;
    public static volatile SingularAttribute<Pago, Cliente> clien;
    public static volatile SingularAttribute<Pago, Date> fechaPago;

}