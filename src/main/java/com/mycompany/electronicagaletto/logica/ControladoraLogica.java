package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ControladoraLogica {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    Date fechaActual = new Date();
    
    //Articulo
    public void guardar(String text, double costo, String barras, int stock, Grupo gru) {
        Articulo arti = new Articulo();
        arti.setNombreArticulo(text);
        arti.setCosto(costo);
        arti.setCodBarra(barras);
        arti.setStock(stock);
        arti.setGrupo(gru);
        arti.setEstado(true);
        double precio = costo+(costo*arti.getGrupo().getBeneficio());
        arti.setPrecio(precio);
        controlPersis.guardar(arti);
    }
    //Grupo
    public void guardar(double bene, int bstock, String text) {
       LinkedList<Articulo> listaArti = new LinkedList<Articulo>();
       Grupo grup = new Grupo();
       grup.setBeneficio(bene);
       grup.setBajoStock(bstock);
       grup.setNombreGrupo(text);
       grup.setArticulo(listaArti);
       grup.setEstado(true);
       controlPersis.guardar(grup);
    }
    //Localidad
    public void guardarLoc(String loca, int cod, String prov) {
       Localidad locali = new Localidad();
       locali.setLocalidad(loca);
       locali.setProvincia(prov);
       locali.setCodigoPostal(cod);
       controlPersis.guardarLoc(locali);
    }

    public void guardar(String nom, String ape, int doc, String tel, String mail, 
            String calle, int nume, char dpto, int piso, Localidad localidad) {
        //crear direccion
        Domicilio dom = new Domicilio();
        dom.setCalle(calle);
        dom.setDepto(dpto);
        dom.setNumCalle(nume);
        dom.setPiso(piso);
        dom.setLocal(localidad);
        
        //crear cliente y asignar direccion
        Cliente cli = new Cliente();
        cli.setApellido(ape);
        cli.setDni(doc);
        cli.setEmail(mail);
        cli.setNombre(nom);
        cli.setTelefono(tel);
        cli.setDomi(dom);
        cli.setEstado(true);
        
        //guardamos
        controlPersis.guardar(dom, cli);
    }

    public List<Articulo> traerArticulos() {
       return controlPersis.traerArticulos();
    }

    public List<Grupo> traerGrupos() {
        return controlPersis.traerGrupos();
    }

    public List<Cliente> traerClientes() {
        return controlPersis.traerClientes();
    }

    public Grupo traerGrupo(int id) {
      return controlPersis.traerGrupos(id);
       
    }

    public void editarGrupo(Grupo grup, double bene, int bstock, String text) {
        grup.setNombreGrupo(text);
        grup.setBeneficio(bene);
        grup.setBajoStock(bstock);
        
        controlPersis.editarGrupo(grup);
        
        List<Articulo> lista = traerArticulos();
        for(Articulo art : lista){
            if(art.getGrupo().getIdGrupo()==grup.getIdGrupo()){
                actualizarPrecio(art);
            }
        }
    }

    public Articulo traerArticulo(int id) {
        return controlPersis.traerArticulo(id);
    }

    public void editar(Articulo art, String nom, double costo, String barras, int stock, Grupo grupo) {
    art.setNombreArticulo(nom);
    art.setCosto(costo);
    art.setCodBarra(barras);
    art.setStock(stock);
    art.setGrupo(grupo);
    double precio = costo+(costo*art.getGrupo().getBeneficio());
    art.setPrecio(precio);
    controlPersis.editarArticulo(art);
    }

    public Cliente traerCliente(int id) {
        return controlPersis.traerCliente(id);
    }

    public void editarCliente(Cliente cli, String nom, String ape, int doc, String tel, String mail, 
            String calle, int nume, char dpto, int piso, Localidad localidad) {
       
        cli.setNombre(nom);
        cli.setApellido(ape);
        cli.setDni(doc);
        cli.setTelefono(tel);
        cli.setEmail(mail);
        
        controlPersis.editarCliente(cli);
        
        //set nuevos valores domicilio
        Domicilio dom = this.traerDomicilio(cli.getDomi().getIdDomicilio());
        dom.setCalle(calle);
        dom.setNumCalle(nume);
        dom.setDepto(dpto);
        dom.setPiso(piso);
        dom.setLocal(localidad);
        
        //llamar a modificar dueño
        this.editarDomicilio(dom);
    }

    private Domicilio traerDomicilio(int idDomicilio) {
       return controlPersis.traerDomicilio(idDomicilio);
    }

    private void editarDomicilio(Domicilio dom) {
       controlPersis.editarDomicilio(dom);
    }

    private void actualizarPrecio(Articulo art) {
        double precio = art.getCosto()+(art.getCosto()*art.getGrupo().getBeneficio());
        art.setPrecio(precio);
        controlPersis.editarArticulo(art);
    }

    public void editarEstadoCliente(int id) {
        Cliente cli = traerCliente(id);
        if(cli.isEstado()){
            cli.setEstado(false);
        }else{
            cli.setEstado(true);
        }
        controlPersis.editarEstadoCliente(cli);
    }

    public void editarEstadoArticulo(int id) {
        Articulo art = traerArticulo(id);
         if(art.isEstado()){
            art.setEstado(false);
        }else{
            art.setEstado(true);
        }
         controlPersis.editarEstadoArticulo(art);
    }

    public void editarEstadoGrupo(int id) {
        Grupo gru = traerGrupo(id);
        if(gru.isEstado()){
            gru.setEstado(false);
        }else{
            gru.setEstado(true);
        }
         controlPersis.editarEstadoGrupo(gru);
    }

    public List<Localidad> traerLocalidades() {
    return controlPersis.traerLocalidades();
    }

    public Localidad traerLocalidad(int id) {
    return controlPersis.traerLocalidad(id);
    }

    public void editarLocalidad(Localidad loca, String text, int text0, String text1) {
    loca.setLocalidad(text);
    loca.setCodigoPostal(text0);
    loca.setProvincia(text1);
    
    controlPersis.editarLocalidad(loca);
    }

    public Usuario validarUsuario(String usuario, String contrasenia){
        
       // String mensaje="";
       Usuario usr = null;
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for (Usuario usu : listaUsuarios) {  
            if (usu.getId().equals(usuario)) {
                if(usu.getContrasenia().equals(contrasenia)) {
                   usr = usu;
                    return usr;
                }
                else {
                   usr = null;
                    return usr;
                }
            }
            else {
               usr = null;
            }
        }
        return usr;
        
    }

    public void guardarVenta(Usuario usr, Cliente idCliente, double tot) {
        LinkedList<Devolucion> listaDev = new LinkedList<Devolucion>();
        LinkedList<ItemVenta> listaItemV = new LinkedList<ItemVenta>();
        Venta venta = new Venta();
        
        venta.setClient(idCliente);
        venta.setUsu(usr);
        venta.setTotal(tot);
        venta.setListaDevolucion(listaDev);
        venta.setListaItemVenta(listaItemV);
        venta.setFechaVenta(fechaActual);
        venta.setHoraVenta(fechaActual);
        
        controlPersis.guardarVenta(venta);
    }

    public int traeridVenta() {
        return controlPersis.traeridVenta();
    }

    public Venta traerVenta(int idVent) {
        return controlPersis.traerVenta(idVent);
    }

    public void guardarItemVenta(int cant, double subTot, Venta iDVenta, Articulo art) {
        ItemVenta item = new ItemVenta();
        item.setArticu(art);
        item.setCantidad(cant);
        item.setPrecioVta(subTot);
        item.setVenta(iDVenta);
        
        controlPersis.guardarItemVenta(item);
        
        LinkedList<ItemVenta> listaItemV = new LinkedList<ItemVenta>();
        listaItemV.add(item);
        iDVenta.setListaItemVenta(listaItemV);
        controlPersis.editVenta(iDVenta);
    }

    public List<ItemVenta> traerItemVenta(Cliente idCliente) {
        return controlPersis.traerItemVenta(idCliente);
    }

    public void guardarDevol(Usuario usr, Cliente idCliente, double tot) {
        LinkedList<ItemDevolucion> listaItemD = new LinkedList<>();
        Devolucion devo = new Devolucion();
        
        devo.setUsuario(usr);
        devo.setCliente(idCliente);
        devo.setTotalDevolucion(tot);
        devo.setItemDev(listaItemD);
        devo.setFechaDevolucion(fechaActual);
        
        controlPersis.guardarDevol(devo);
    }

    public int traeridDevol() {
        return controlPersis.traeridDevol();
    }

    public Devolucion traerDevolucion(int idDevo) {
        return controlPersis.traerDevolucion(idDevo);
    }

    public void guardarItemDevolucion(int cant, double subTot, Devolucion devol, Articulo art, boolean esCambio) {
        ItemDevolucion itemD = new ItemDevolucion();
        itemD.setArti(art);
        itemD.setCantidadDevuelta(cant);
        itemD.setPrecioDevol(subTot);
        itemD.setDevo(devol);
        itemD.setCambio(esCambio);
        
        controlPersis.guardarItemDev(itemD);
        /*
        LinkedList<ItemDevolucion> listaItemD = new LinkedList<>();
        listaItemD.add(itemD);
        devol.setItemDev(listaItemD);
       //controlPersis.editDevolucion(devol);*/
    }

    public void guardarPago(String formaPago, double pago, Usuario usr, Cliente idCliente) {
        Pago pag = new Pago();
      
        pag.setClien(idCliente);
        pag.setFechaPago(fechaActual);
        pag.setFormaPago(formaPago);
        pag.setHoraPago(fechaActual);
        pag.setMonto(pago);
        pag.setUsu(usr);
        
        controlPersis.guardarPago(pag);
    }

    public List<Venta> traerVentas(Cliente idCliente) {
        return controlPersis.traerVentas(idCliente);
    }

    public List<Pago> traerPagos(Cliente idCliente) {
        return controlPersis.traerPagos(idCliente);
    }

    public List<ItemDevolucion> traerItemDevoluciones(Cliente idCliente) {
        return controlPersis.traerItemDevolucion(idCliente);
    }
}
