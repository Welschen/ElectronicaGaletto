package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.logica.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-01-08T15:34:24", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Auditoria.class)
public class Auditoria_ { 

    public static volatile SingularAttribute<Auditoria, Date> horaModificacion;
    public static volatile SingularAttribute<Auditoria, Integer> idAuditoria;
    public static volatile SingularAttribute<Auditoria, Date> fechaModificacion;
    public static volatile SingularAttribute<Auditoria, Usuario> usuario;
    public static volatile SingularAttribute<Auditoria, String> estadoAnterior;

}