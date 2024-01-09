
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
public class Pago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPago;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPago;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaPago;
    private double monto;
    private String formaPago;
    @ManyToOne
    private Cliente clien;

    public Pago(int idPago, Date fechaPago, Date horaPago, double monto, String formaPago, Cliente clien) {
        this.idPago = idPago;
        this.fechaPago = fechaPago;
        this.horaPago = horaPago;
        this.monto = monto;
        this.formaPago = formaPago;
        this.clien = clien;
    }

    public Pago() {
    }
    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

 
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getHoraPago() {
        return horaPago;
    }

    public void setHoraPago(Date horaPago) {
        this.horaPago = horaPago;
    }

    public Cliente getClien() {
        return clien;
    }

    public void setClien(Cliente clien) {
        this.clien = clien;
    }
    
}
