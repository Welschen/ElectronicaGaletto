
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idUsuario;
    private String contrasenia;
    private String id;
    @OneToMany (mappedBy="usuario")
    private LinkedList<Devolucion> listaDevolucion;
    @OneToMany (mappedBy="usu")
    private LinkedList<Venta> listaVenta;
    @OneToMany (mappedBy="usuario")
    private LinkedList<Auditoria> listaAuditoria;

    public Usuario() {
    }

    public Usuario(int idUsuario, String contrasenia, String id, LinkedList<Devolucion> listaDevolucion, LinkedList<Venta> listaVenta, LinkedList<Auditoria> listaAuditoria) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.id = id;
        this.listaDevolucion = listaDevolucion;
        this.listaVenta = listaVenta;
        this.listaAuditoria = listaAuditoria;
    }

   

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Devolucion> getListaDevolucion() {
        return listaDevolucion;
    }

    public void setListaDevolucion(LinkedList<Devolucion> listaDevolucion) {
        this.listaDevolucion = listaDevolucion;
    }

    public LinkedList<Venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(LinkedList<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public LinkedList<Auditoria> getListaAuditoria() {
        return listaAuditoria;
    }

    public void setListaAuditoria(LinkedList<Auditoria> listaAuditoria) {
        this.listaAuditoria = listaAuditoria;
    }

   
    
}
