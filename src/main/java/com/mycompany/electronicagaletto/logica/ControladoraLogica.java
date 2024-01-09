package com.mycompany.electronicagaletto.logica;

import com.mycompany.electronicagaletto.persistencia.ControladoraPersistencia;
import java.util.LinkedList;

public class ControladoraLogica {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //Articulo

    public void guardar(String text, double costo, String barras, int stock, Grupo gru) {
        Articulo arti = new Articulo();
        arti.setNombreArticulo(text);
        arti.setCosto(costo);
        arti.setCodBarra(barras);
        arti.setStock(stock);
        arti.setGrupo(gru);
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
       controlPersis.guardar(grup);
    }
    //Localidad
    public void guardarLoc(String loca, String prov, int cod) {
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
        
        //guardamos
        controlPersis.guardar(dom, cli);
    }
}
