
package com.mycompany.electronicagaletto.persistencia;

import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.Domicilio;
import com.mycompany.electronicagaletto.logica.Grupo;
import com.mycompany.electronicagaletto.logica.Localidad;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.swing.JComboBox;

public class ControladoraPersistencia {
    ArticuloJpaController articuloJpa = new ArticuloJpaController();
    AuditoriaJpaController audiJpa = new AuditoriaJpaController();
    ClienteJpaController clienteJpa = new ClienteJpaController();
    DevolucionJpaController devoJpa = new DevolucionJpaController();
    DomicilioJpaController domiJpa = new DomicilioJpaController();
    GrupoJpaController grupoJpa = new GrupoJpaController();
    ItemDevolucionJpaController itemDevoJpa = new ItemDevolucionJpaController();
    ItemPresupuestoJpaController itemPresupJpa = new ItemPresupuestoJpaController();
    ItemVentaJpaController itemVtaJpa = new ItemVentaJpaController();
    LocalidadJpaController localJpa = new LocalidadJpaController();
    PagoJpaController pagoJpa = new PagoJpaController();
    PresupuestoJpaController presupJpa = new PresupuestoJpaController();
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    VentaJpaController ventaJpa = new VentaJpaController();

    //GRUPO
    public void guardar(Grupo grup) {
        grupoJpa.create(grup);
    }
    public void getGrupoCmb(JComboBox<Grupo> cmbGrupo){
        EntityManager em = grupoJpa.getEntityManager();
        Iterator it = em.createQuery("SELECT g FROM Grupo g").getResultList().iterator();
        Grupo h;
        try{
            while(it.hasNext()){
                h = (Grupo) it.next();
                cmbGrupo.addItem(h);
            }       
        }catch (Exception e){
            System.out.println("Error, no existen grupos para listar");
        }
    }
    //Articulo
    public void guardar(Articulo arti) {
        articuloJpa.create(arti);
    }
    //Localidad
    public void guardarLoc(Localidad locali) {
       localJpa.create(locali);
    }
    public void getLocalidadCmb(JComboBox<Localidad> cmbLocalidad){
    EntityManager em = localJpa.getEntityManager();
    Iterator it = em.createQuery("SELECT l FROM Localidad l").getResultList().iterator();
    Localidad h;
    try{
        while(it.hasNext()){
            h = (Localidad) it.next();
                  cmbLocalidad.addItem(h);
            }       
        }catch (Exception e){
            System.out.println("Error, no existen localidades para listar");
        }
    }

    public void guardar(Domicilio dom, Cliente cli) {
        domiJpa.create(dom);
        clienteJpa.create(cli);
    }
}
