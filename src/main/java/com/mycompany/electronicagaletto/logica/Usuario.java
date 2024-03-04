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
    private String rol;
    @OneToMany (mappedBy="usuario")
    private LinkedList<Devolucion> listaDevolucion;
    @OneToMany (mappedBy="usu")
    private LinkedList<Venta> listaVenta;
    @OneToMany (mappedBy="usuario")
    private LinkedList<Auditoria> listaAuditoria;
    @OneToMany (mappedBy="usu")
    private LinkedList<Pago> listaPago;

    public Usuario() {
    }

    public Usuario(int idUsuario, String contrasenia, String id, String rol, LinkedList<Devolucion> listaDevolucion, LinkedList<Venta> listaVenta, LinkedList<Auditoria> listaAuditoria, LinkedList<Pago> listaPago) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.id = id;
        this.rol = rol;
        this.listaDevolucion = listaDevolucion;
        this.listaVenta = listaVenta;
        this.listaAuditoria = listaAuditoria;
        this.listaPago = listaPago;
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

    public LinkedList<Pago> getListaPago() {
        return listaPago;
    }

    public void setListaPago(LinkedList<Pago> listaPago) {
        this.listaPago = listaPago;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
      public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
