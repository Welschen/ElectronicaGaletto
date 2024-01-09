
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Localidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idLocalidad;
    private int codigoPostal;
    private String provincia;
    private String localidad;
    @OneToMany (mappedBy="local")
    private LinkedList<Domicilio> listaDomicilios;

    public Localidad() {
    }

    public Localidad(int idLocalidad, int codigoPostal, String provincia, String localidad, LinkedList<Domicilio> listaDomicilios) {
        this.idLocalidad = idLocalidad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.localidad = localidad;
        this.listaDomicilios = listaDomicilios;
    }

    public LinkedList<Domicilio> getListaDomicilios() {
        return listaDomicilios;
    }

    public void setListaDomicilios(LinkedList<Domicilio> listaDomicilios) {
        this.listaDomicilios = listaDomicilios;
    }

   

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return localidad;
    }
    
}


