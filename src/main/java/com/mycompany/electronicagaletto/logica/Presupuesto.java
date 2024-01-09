
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Presupuesto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPresupuesto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPresup;
    private double totalPresup;
    @OneToMany (mappedBy="presupuest")
    private LinkedList<ItemPresupuesto> listaPresupuesto;
    @OneToOne
    private Venta venta;


    public Presupuesto() {
    }

    public Presupuesto(int idPresupuesto, Date fechaPresup, double totalPresup, LinkedList<ItemPresupuesto> listaPresupuesto, Venta venta) {
        this.idPresupuesto = idPresupuesto;
        this.fechaPresup = fechaPresup;
        this.totalPresup = totalPresup;
        this.listaPresupuesto = listaPresupuesto;
        this.venta = venta;
    }

    public LinkedList<ItemPresupuesto> getListaPresupuesto() {
        return listaPresupuesto;
    }

    public void setListaPresupuesto(LinkedList<ItemPresupuesto> listaPresupuesto) {
        this.listaPresupuesto = listaPresupuesto;
    }


    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Date getFechaPresup() {
        return fechaPresup;
    }

    public void setFechaPresup(Date fechaPresup) {
        this.fechaPresup = fechaPresup;
    }

    public double getTotalPresup() {
        return totalPresup;
    }

    public void setTotalPresup(double totalPresup) {
        this.totalPresup = totalPresup;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

 
    
}
