
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Grupo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idGrupo;
    private String nombreGrupo;
    private int bajoStock;
    private double beneficio;
    @OneToMany (mappedBy="grupo")
    private LinkedList<Articulo> articulo;
    private boolean estado;

    public Grupo() {
    }

    public Grupo(int idGrupo, String nombreGrupo, int bajoStock, double beneficio, LinkedList<Articulo> articulo, boolean estado) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
        this.bajoStock = bajoStock;
        this.beneficio = beneficio;
        this.articulo = articulo;
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
   
    public LinkedList<Articulo> getArticulo() {
        return articulo;
    }

    public void setArticulo(LinkedList<Articulo> articulo) {
        this.articulo = articulo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }


    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public int getBajoStock() {
        return bajoStock;
    }

    public void setBajoStock(int bajoStock) {
        this.bajoStock = bajoStock;
    }

    public double getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    
    @Override
    public String toString() {
        return  nombreGrupo;
    }

    public String getEstado() {
        if (isEstado()==true){
            return "Activo";
        }else{
        return "Inactivo";  
        }
    }
}