
package com.mycompany.electronicagaletto.persistencia;

import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.Domicilio;
import com.mycompany.electronicagaletto.logica.Grupo;
import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.logica.ItemVenta;
import com.mycompany.electronicagaletto.logica.Localidad;
import com.mycompany.electronicagaletto.logica.Pago;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.logica.Venta;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ControladoraPersistencia {
    int idVen;
    int idDevolu;
    
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
                if(h.isEstado()){
                cmbGrupo.addItem(h);
                }
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

    public List<Articulo> traerArticulos() {
        return articuloJpa.findArticuloEntities();
    }

    public List<Grupo> traerGrupos() {
       return grupoJpa.findGrupoEntities();
    }

    public List<Cliente> traerClientes() {
        return clienteJpa.findClienteEntities();
    }

    public Grupo traerGrupos(int id) {
       return grupoJpa.findGrupo(id);
    }

    public void editarGrupo(Grupo grup) {
        try {
            grupoJpa.edit(grup);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Articulo> traerArticulos(int id) {
        return articuloJpa.findArticuloEntities();
    }

    public Articulo traerArticulo(int id) {
    return articuloJpa.findArticulo(id);
    }

    public void editarArticulo(Articulo art) {
        try {
            articuloJpa.edit(art);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente traerCliente(int id) {
    return clienteJpa.findCliente(id);
    }

    public void editarCliente(Cliente cli) {
        try {
            clienteJpa.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Domicilio traerDomicilio(int idDomicilio) {
       return domiJpa.findDomicilio(idDomicilio);
    }

    public void editarDomicilio(Domicilio dom) {
        try {
            domiJpa.edit(dom);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEstadoCliente(Cliente cli) {
        try {
            clienteJpa.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEstadoArticulo(Articulo art) {
        try {
            articuloJpa.edit(art);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarEstadoGrupo(Grupo gru) {
        try {
            grupoJpa.edit(gru);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Localidad> traerLocalidades() {
    return localJpa.findLocalidadEntities();
    }

    public Localidad traerLocalidad(int id) {
      return localJpa.findLocalidad(id);
    }

    public void editarLocalidad(Localidad loca) {
        try {
            localJpa.edit(loca);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cliente> traerClientesActivo() {
        EntityManager em= clienteJpa.getEntityManager();
            String jpql="SELECT i FROM Cliente i WHERE  i.estado=true";
            Query query= em.createQuery(jpql);
            return query.getResultList();
    }

    public List<Usuario> traerUsuarios() {
        List<Usuario> listaUsuario = usuJpa.findUsuarioEntities();
        return listaUsuario;
    }

    public void guardarVenta(Venta venta) {
        ventaJpa.create(venta);
        idVen=venta.getIdVenta();
    }
    public int traeridVenta() {
        return idVen;
    }
    public Venta traerVenta(int idVent) {
        return ventaJpa.findVenta(idVen);
    }
    public void guardarItemVenta(ItemVenta item) {
        itemVtaJpa.create(item);
    }
    public void editVenta(Venta iDVenta) {
        try {
            ventaJpa.edit(iDVenta);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<ItemVenta> traerItemVenta(Cliente idCliente) {
            EntityManager em= itemVtaJpa.getEntityManager();
            int clienteId = idCliente.getIdCliente();
            String jpql="SELECT i FROM ItemVenta i WHERE i.ven.client.idCliente=:idcliente";
            Query query= em.createQuery(jpql);
            query.setParameter("idcliente",clienteId);
        
            return query.getResultList();
    }
    public void guardarDevol(Devolucion devo) {
        devoJpa.create(devo);
        idDevolu=devo.getIdDevolucion();
    }

    public int traeridDevol() {
         
        return idDevolu;
    }

    public Devolucion traerDevolucion(int idDevo) {
        return devoJpa.findDevolucion(idDevo);
    }

    public void guardarItemDev(ItemDevolucion itemD) {
        itemDevoJpa.create(itemD);
    }

    public void editDevolucion(Devolucion devol) {
        try {
            devoJpa.edit(devol);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPago(Pago pag) {
        pagoJpa.create(pag);
    }

    public List<Venta> traerVentas(Cliente idCliente) {
            EntityManager em= ventaJpa.getEntityManager();
            int clienteId = idCliente.getIdCliente();
            String jpql="SELECT v FROM Venta v WHERE v.client.idCliente=:idcliente";
            Query query= em.createQuery(jpql);
            query.setParameter("idcliente",clienteId);
        
            return query.getResultList();
    }

    public List<Pago> traerPagos(Cliente idCliente) {
            EntityManager em= pagoJpa.getEntityManager();
            int clienteId = idCliente.getIdCliente();
            String jpql="SELECT p FROM Pago p WHERE p.clien.idCliente=:idcliente";
            Query query= em.createQuery(jpql);
            query.setParameter("idcliente",clienteId);
        
            return query.getResultList();
    }

    public List<ItemDevolucion> traerItemDevolucion(Cliente idCliente) {
            EntityManager em= itemDevoJpa.getEntityManager();
            int clienteId = idCliente.getIdCliente();
            String jpql="SELECT i FROM ItemDevolucion i WHERE i.devo.cliente.idCliente=:idcliente AND i.cambio=false";
            Query query= em.createQuery(jpql);
            query.setParameter("idcliente",clienteId);
        
            return query.getResultList();
    }

    public List<Articulo> traerArticulosAlta() {
        EntityManager em= articuloJpa.getEntityManager();
           
            String jpql="SELECT i FROM Articulo i WHERE  i.estado=true";
            Query query= em.createQuery(jpql);
          
        
            return query.getResultList();
    }

    public List<Articulo> traerArticulos(Grupo grupo) {
         EntityManager em= itemDevoJpa.getEntityManager();
            int grupoId = grupo.getIdGrupo();
            String jpql="SELECT i FROM Articulo i WHERE i.grupo.idGrupo=:idgrupo";
            Query query= em.createQuery(jpql);
            query.setParameter("idgrupo",grupoId);
        
            return query.getResultList();
    }
}
