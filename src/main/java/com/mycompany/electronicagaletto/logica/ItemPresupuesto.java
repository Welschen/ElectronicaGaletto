
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPresupuesto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idItemPresupuesto;
    private int cantiArticulo;
    private double precioPresup;
    @ManyToOne
    private Articulo artic;
    @ManyToOne
    private Presupuesto presupuest;

    public ItemPresupuesto() {
    }

    public ItemPresupuesto(int idItemPresupuesto, int cantiArticulo, double precioPresup, Articulo artic, Presupuesto presupuest) {
        this.idItemPresupuesto = idItemPresupuesto;
        this.cantiArticulo = cantiArticulo;
        this.precioPresup = precioPresup;
        this.artic = artic;
        this.presupuest = presupuest;
    }

    public int getIdItemPresupuesto() {
        return idItemPresupuesto;
    }

    public void setIdItemPresupuesto(int idItemPresupuesto) {
        this.idItemPresupuesto = idItemPresupuesto;
    }

    public int getCantiArticulo() {
        return cantiArticulo;
    }

    public void setCantiArticulo(int cantiArticulo) {
        this.cantiArticulo = cantiArticulo;
    }

    public double getPrecioPresup() {
        return precioPresup;
    }

    public void setPrecioPresup(double precioPresup) {
        this.precioPresup = precioPresup;
    }

    public Articulo getArtic() {
        return artic;
    }

    public void setArtic(Articulo artic) {
        this.artic = artic;
    }

    public Presupuesto getPresupuest() {
        return presupuest;
    }

    public void setPresupuest(Presupuesto presupuest) {
        this.presupuest = presupuest;
    }
    
}
