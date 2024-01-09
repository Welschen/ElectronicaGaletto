package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Devolucion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idDevolucion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDevolucion;
    private double totalDevolucion;
    @OneToMany (mappedBy="devo")
    private LinkedList<ItemDevolucion> itemDev;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Venta venta;

    public Devolucion() {
    }

    public Devolucion(int idDevolucion, Date fechaDevolucion, double totalDevolucion, LinkedList<ItemDevolucion> itemDev, Usuario usuario, Venta venta) {
        this.idDevolucion = idDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.totalDevolucion = totalDevolucion;
        this.itemDev = itemDev;
        this.usuario = usuario;
        this.venta = venta;
    }


    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getTotalDevolucion() {
        return totalDevolucion;
    }

    public void setTotalDevolucion(double totalDevolucion) {
        this.totalDevolucion = totalDevolucion;
    }

    public LinkedList<ItemDevolucion> getItemDev() {
        return itemDev;
    }

    public void setItemDev(LinkedList<ItemDevolucion> itemDev) {
        this.itemDev = itemDev;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
}
