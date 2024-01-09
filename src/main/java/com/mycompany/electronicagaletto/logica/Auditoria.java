
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
@Entity
public class Auditoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idAuditoria;
    private String estadoAnterior;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaModificacion;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaModificacion;
    @ManyToOne
    private Usuario usuario;

    public Auditoria() {
    }

    public Auditoria(int idAuditoria, String estadoAnterior, Date fechaModificacion, Date horaModificacion, Usuario usuario) {
        this.idAuditoria = idAuditoria;
        this.estadoAnterior = estadoAnterior;
        this.fechaModificacion = fechaModificacion;
        this.horaModificacion = horaModificacion;
        this.usuario = usuario;
    }

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(Date horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
