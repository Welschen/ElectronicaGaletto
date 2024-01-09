package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemDevolucion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idItemDevolucion;
    private int cantidadDevuelta;
    private double precioDevol;
    private boolean cambio;
    @ManyToOne
    private Articulo arti;
    @ManyToOne
    private Devolucion devo;

    public ItemDevolucion() {
    }

    public ItemDevolucion(int idItemDevolucion, int cantidadDevuelta, double precioDevol, boolean cambio, Articulo arti, Devolucion devo) {
        this.idItemDevolucion = idItemDevolucion;
        this.cantidadDevuelta = cantidadDevuelta;
        this.precioDevol = precioDevol;
        this.cambio = cambio;
        this.arti = arti;
        this.devo = devo;
    }

    public boolean isCambio() {
        return cambio;
    }

    public void setCambio(boolean cambio) {
        this.cambio = cambio;
    }

    public int getIdItemDevolucion() {
        return idItemDevolucion;
    }

    public void setIdItemDevolucion(int idItemDevolucion) {
        this.idItemDevolucion = idItemDevolucion;
    }

    public int getCantidadDevuelta() {
        return cantidadDevuelta;
    }

    public void setCantidadDevuelta(int cantidadDevuelta) {
        this.cantidadDevuelta = cantidadDevuelta;
    }

    public double getPrecioDevol() {
        return precioDevol;
    }

    public void setPrecioDevol(double precioDevol) {
        this.precioDevol = precioDevol;
    }

    public Articulo getArti() {
        return arti;
    }

    public void setArti(Articulo arti) {
        this.arti = arti;
    }

    public Devolucion getDevo() {
        return devo;
    }

    public void setDevo(Devolucion devo) {
        this.devo = devo;
    }
    
}
