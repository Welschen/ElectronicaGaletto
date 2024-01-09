
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
public class Venta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idVenta;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaVenta;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaVenta;
    private double total;
    @ManyToOne
    private Cliente client;
    @OneToMany (mappedBy="venta")
    private LinkedList<Devolucion> listaDevolucion;
    @OneToMany (mappedBy="ven")
    private LinkedList<ItemVenta> listaItemVenta;
    @ManyToOne
    private Usuario usu;

    public Venta() {
    }

    public Venta(int idVenta, Date horaVenta, Date fechaVenta, double total, Cliente client, LinkedList<Devolucion> listaDevolucion, LinkedList<ItemVenta> listaItemVenta, Usuario usu) {
        this.idVenta = idVenta;
        this.horaVenta = horaVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.client = client;
        this.listaDevolucion = listaDevolucion;
        this.listaItemVenta = listaItemVenta;
        this.usu = usu;
    }
    

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getHoraVenta() {
        return horaVenta;
    }

    public void setHoraVenta(Date horaVenta) {
        this.horaVenta = horaVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public LinkedList<Devolucion> getListaDevolucion() {
        return listaDevolucion;
    }

    public void setListaDevolucion(LinkedList<Devolucion> listaDevolucion) {
        this.listaDevolucion = listaDevolucion;
    }

    public LinkedList<ItemVenta> getListaItemVenta() {
        return listaItemVenta;
    }

    public void setListaItemVenta(LinkedList<ItemVenta> listaItemVenta) {
        this.listaItemVenta = listaItemVenta;
    }

  
    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }  
}
