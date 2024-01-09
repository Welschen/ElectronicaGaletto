package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idItemVenta;
    private int cantidad;
    private double precioVta;
    @ManyToOne
    private Venta ven;
    @ManyToOne
    private Articulo articu;

    public ItemVenta() {
    }

    public ItemVenta(int idItemVenta, int cantidad, double precioVta, Venta ven, Articulo articu) {
        this.idItemVenta = idItemVenta;
        this.cantidad = cantidad;
        this.precioVta = precioVta;
        this.ven = ven;
        this.articu = articu;
    }

    public int getIdItemVenta() {
        return idItemVenta;
    }

    public void setIdItemVenta(int idItemVenta) {
        this.idItemVenta = idItemVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVta() {
        return precioVta;
    }

    public void setPrecioVta(double precioVta) {
        this.precioVta = precioVta;
    }


    public Venta getVenta() {
        return ven;
    }

    public void setVenta(Venta ven) {
        this.ven = ven;
    }

    public Articulo getArticu() {
        return articu;
    }

    public void setArticu(Articulo articu) {
        this.articu = articu;
    }
    
}
