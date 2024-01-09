
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idDomicilio;
    private String calle;
    private int numCalle;
    private char depto;
    private int piso;
    @ManyToOne
    private Localidad local;

    public Domicilio(int idDomicilio, String calle, int numCalle, char depto, int piso, Localidad local) {
        this.idDomicilio = idDomicilio;
        this.calle = calle;
        this.numCalle = numCalle;
        this.depto = depto;
        this.piso = piso;
        this.local = local;
    }

    public Domicilio() {
    }

    public int getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(int idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumCalle() {
        return numCalle;
    }

    public void setNumCalle(int numCalle) {
        this.numCalle = numCalle;
    }

    public char getDepto() {
        return depto;
    }

    public void setDepto(char depto) {
        this.depto = depto;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public Localidad getLocal() {
        return local;
    }

    public void setLocal(Localidad local) {
        this.local = local;
    }
}
