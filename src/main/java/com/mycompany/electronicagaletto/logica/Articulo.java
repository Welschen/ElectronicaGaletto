
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;

import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idArituclo;
    private String codBarra;
    private double costo;
    private String nombreArticulo;
    private double precio;
    private int stock;
    @OneToMany (mappedBy="arti")
    private LinkedList<ItemDevolucion> itemDevol;
    @OneToMany (mappedBy="articu")
    private LinkedList<ItemVenta> itemVta;
    @OneToMany (mappedBy="artic")
    private LinkedList<ItemPresupuesto> itemPresu;
    @ManyToOne
    private Grupo grupo;
    private boolean estado;

    public Articulo() {
    }

    public Articulo(int idArituclo, String codBarra, double costo, String nombreArticulo, double precio, int stock, LinkedList<ItemDevolucion> itemDevol, LinkedList<ItemVenta> itemVta, LinkedList<ItemPresupuesto> itemPresu, Grupo grupo, boolean estado) {
        this.idArituclo = idArituclo;
        this.codBarra = codBarra;
        this.costo = costo;
        this.nombreArticulo = nombreArticulo;
        this.precio = precio;
        this.stock = stock;
        this.itemDevol = itemDevol;
        this.itemVta = itemVta;
        this.itemPresu = itemPresu;
        this.grupo = grupo;
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public int getIdArituclo() {
        return idArituclo;
    }

    public void setIdArituclo(int idArituclo) {
        this.idArituclo = idArituclo;
    }


    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LinkedList<ItemDevolucion> getItemDevol() {
        return itemDevol;
    }

    public void setItemDevol(LinkedList<ItemDevolucion> itemDevol) {
        this.itemDevol = itemDevol;
    }

    public LinkedList<ItemVenta> getItemVta() {
        return itemVta;
    }

    public void setItemVta(LinkedList<ItemVenta> itemVta) {
        this.itemVta = itemVta;
    }

    public LinkedList<ItemPresupuesto> getItemPresu() {
        return itemPresu;
    }

    public void setItemPresu(LinkedList<ItemPresupuesto> itemPresu) {
        this.itemPresu = itemPresu;
    }



    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
}
