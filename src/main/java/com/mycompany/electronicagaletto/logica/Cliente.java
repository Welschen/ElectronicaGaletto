
package com.mycompany.electronicagaletto.logica;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idCliente;
    private String apellido;
    private String nombre;
    private int dni;
    private String email;
    private String telefono;
    @OneToOne
    private Domicilio domi;
    @OneToMany (mappedBy="clien")
    private  LinkedList<Pago> listaPagos;
    @OneToMany (mappedBy="client")
    private  LinkedList<Venta> listaVentas;
    @OneToMany (mappedBy="cliente")
    private LinkedList<Devolucion> listaDevolucion;
    private boolean estado;
   
    public Cliente() {
    }

    public Cliente(int idCliente, String apellido, String nombre, int dni, String email, String telefono, Domicilio domi, LinkedList<Pago> listaPagos, LinkedList<Venta> listaVentas, LinkedList<Devolucion> listaDevolucion, boolean estado) {
        this.idCliente = idCliente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.domi = domi;
        this.listaPagos = listaPagos;
        this.listaVentas = listaVentas;
        this.listaDevolucion = listaDevolucion;
        this.estado = estado;
    }

    public LinkedList<Devolucion> getListaDevolucion() {
        return listaDevolucion;
    }

    public void setListaDevolucion(LinkedList<Devolucion> listaDevolucion) {
        this.listaDevolucion = listaDevolucion;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     @Override
    public String toString() {
        String datos = dni+ " - "+nombre +" "+ apellido;
        return  datos;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Domicilio getDomi() {
        return domi;
    }

    public void setDomi(Domicilio domi) {
        this.domi = domi;
    }

    public LinkedList<Pago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(LinkedList<Pago> listaPagos) {
        this.listaPagos = listaPagos;
    }

    public LinkedList<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(LinkedList<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public Object getEstado() {
        if (isEstado()==true){
            return "Activo";
        }else{
        return "Inactivo";
        }
    }
}
