/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicagaletto.persistencia;

import com.mycompany.electronicagaletto.logica.Articulo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Grupo;
import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.logica.ItemVenta;
import com.mycompany.electronicagaletto.logica.ItemPresupuesto;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

     public ArticuloJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }
     
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) {
        if (articulo.getItemDevol() == null) {
            articulo.setItemDevol(new LinkedList<ItemDevolucion>());
        }
        if (articulo.getItemVta() == null) {
            articulo.setItemVta(new LinkedList<ItemVenta>());
        }
        if (articulo.getItemPresu() == null) {
            articulo.setItemPresu(new LinkedList<ItemPresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupo = articulo.getGrupo();
            if (grupo != null) {
                grupo = em.getReference(grupo.getClass(), grupo.getIdGrupo());
                articulo.setGrupo(grupo);
            }
            LinkedList<ItemDevolucion> attachedItemDevol = new LinkedList<ItemDevolucion>();
            for (ItemDevolucion itemDevolItemDevolucionToAttach : articulo.getItemDevol()) {
                itemDevolItemDevolucionToAttach = em.getReference(itemDevolItemDevolucionToAttach.getClass(), itemDevolItemDevolucionToAttach.getIdItemDevolucion());
                attachedItemDevol.add(itemDevolItemDevolucionToAttach);
            }
            articulo.setItemDevol(attachedItemDevol);
            LinkedList<ItemVenta> attachedItemVta = new LinkedList<ItemVenta>();
            for (ItemVenta itemVtaItemVentaToAttach : articulo.getItemVta()) {
                itemVtaItemVentaToAttach = em.getReference(itemVtaItemVentaToAttach.getClass(), itemVtaItemVentaToAttach.getIdItemVenta());
                attachedItemVta.add(itemVtaItemVentaToAttach);
            }
            articulo.setItemVta(attachedItemVta);
           LinkedList<ItemPresupuesto> attachedItemPresu = new LinkedList<ItemPresupuesto>();
            for (ItemPresupuesto itemPresuItemPresupuestoToAttach : articulo.getItemPresu()) {
                itemPresuItemPresupuestoToAttach = em.getReference(itemPresuItemPresupuestoToAttach.getClass(), itemPresuItemPresupuestoToAttach.getIdItemPresupuesto());
                attachedItemPresu.add(itemPresuItemPresupuestoToAttach);
            }
            articulo.setItemPresu(attachedItemPresu);
            em.persist(articulo);
            if (grupo != null) {
                grupo.getArticulo().add(articulo);
                grupo = em.merge(grupo);
            }
            for (ItemDevolucion itemDevolItemDevolucion : articulo.getItemDevol()) {
                Articulo oldArtiOfItemDevolItemDevolucion = itemDevolItemDevolucion.getArti();
                itemDevolItemDevolucion.setArti(articulo);
                itemDevolItemDevolucion = em.merge(itemDevolItemDevolucion);
                if (oldArtiOfItemDevolItemDevolucion != null) {
                    oldArtiOfItemDevolItemDevolucion.getItemDevol().remove(itemDevolItemDevolucion);
                    oldArtiOfItemDevolItemDevolucion = em.merge(oldArtiOfItemDevolItemDevolucion);
                }
            }
            for (ItemVenta itemVtaItemVenta : articulo.getItemVta()) {
                Articulo oldArticuOfItemVtaItemVenta = itemVtaItemVenta.getArticu();
                itemVtaItemVenta.setArticu(articulo);
                itemVtaItemVenta = em.merge(itemVtaItemVenta);
                if (oldArticuOfItemVtaItemVenta != null) {
                    oldArticuOfItemVtaItemVenta.getItemVta().remove(itemVtaItemVenta);
                    oldArticuOfItemVtaItemVenta = em.merge(oldArticuOfItemVtaItemVenta);
                }
            }
            for (ItemPresupuesto itemPresuItemPresupuesto : articulo.getItemPresu()) {
                Articulo oldArticOfItemPresuItemPresupuesto = itemPresuItemPresupuesto.getArtic();
                itemPresuItemPresupuesto.setArtic(articulo);
                itemPresuItemPresupuesto = em.merge(itemPresuItemPresupuesto);
                if (oldArticOfItemPresuItemPresupuesto != null) {
                    oldArticOfItemPresuItemPresupuesto.getItemPresu().remove(itemPresuItemPresupuesto);
                    oldArticOfItemPresuItemPresupuesto = em.merge(oldArticOfItemPresuItemPresupuesto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getIdArticulo());
            Grupo grupoOld = persistentArticulo.getGrupo();
            Grupo grupoNew = articulo.getGrupo();
            LinkedList<ItemDevolucion> itemDevolOld = persistentArticulo.getItemDevol();
            LinkedList<ItemDevolucion> itemDevolNew = articulo.getItemDevol();
            LinkedList<ItemVenta> itemVtaOld = persistentArticulo.getItemVta();
            LinkedList<ItemVenta> itemVtaNew = articulo.getItemVta();
            LinkedList<ItemPresupuesto> itemPresuOld = persistentArticulo.getItemPresu();
            LinkedList<ItemPresupuesto> itemPresuNew = articulo.getItemPresu();
            if (grupoNew != null) {
                grupoNew = em.getReference(grupoNew.getClass(), grupoNew.getIdGrupo());
                articulo.setGrupo(grupoNew);
            }
            LinkedList<ItemDevolucion> attachedItemDevolNew = new LinkedList<ItemDevolucion>();
            for (ItemDevolucion itemDevolNewItemDevolucionToAttach : itemDevolNew) {
                itemDevolNewItemDevolucionToAttach = em.getReference(itemDevolNewItemDevolucionToAttach.getClass(), itemDevolNewItemDevolucionToAttach.getIdItemDevolucion());
                attachedItemDevolNew.add(itemDevolNewItemDevolucionToAttach);
            }
            itemDevolNew = attachedItemDevolNew;
            articulo.setItemDevol(itemDevolNew);
            LinkedList<ItemVenta> attachedItemVtaNew = new LinkedList<ItemVenta>();
            for (ItemVenta itemVtaNewItemVentaToAttach : itemVtaNew) {
                itemVtaNewItemVentaToAttach = em.getReference(itemVtaNewItemVentaToAttach.getClass(), itemVtaNewItemVentaToAttach.getIdItemVenta());
                attachedItemVtaNew.add(itemVtaNewItemVentaToAttach);
            }
            itemVtaNew = attachedItemVtaNew;
            articulo.setItemVta(itemVtaNew);
            LinkedList<ItemPresupuesto> attachedItemPresuNew = new LinkedList<ItemPresupuesto>();
            for (ItemPresupuesto itemPresuNewItemPresupuestoToAttach : itemPresuNew) {
                itemPresuNewItemPresupuestoToAttach = em.getReference(itemPresuNewItemPresupuestoToAttach.getClass(), itemPresuNewItemPresupuestoToAttach.getIdItemPresupuesto());
                attachedItemPresuNew.add(itemPresuNewItemPresupuestoToAttach);
            }
            itemPresuNew = attachedItemPresuNew;
            articulo.setItemPresu(itemPresuNew);
            articulo = em.merge(articulo);
            if (grupoOld != null && !grupoOld.equals(grupoNew)) {
                grupoOld.getArticulo().remove(articulo);
                grupoOld = em.merge(grupoOld);
            }
            if (grupoNew != null && !grupoNew.equals(grupoOld)) {
                grupoNew.getArticulo().add(articulo);
                grupoNew = em.merge(grupoNew);
            }
            for (ItemDevolucion itemDevolOldItemDevolucion : itemDevolOld) {
                if (!itemDevolNew.contains(itemDevolOldItemDevolucion)) {
                    itemDevolOldItemDevolucion.setArti(null);
                    itemDevolOldItemDevolucion = em.merge(itemDevolOldItemDevolucion);
                }
            }
            for (ItemDevolucion itemDevolNewItemDevolucion : itemDevolNew) {
                if (!itemDevolOld.contains(itemDevolNewItemDevolucion)) {
                    Articulo oldArtiOfItemDevolNewItemDevolucion = itemDevolNewItemDevolucion.getArti();
                    itemDevolNewItemDevolucion.setArti(articulo);
                    itemDevolNewItemDevolucion = em.merge(itemDevolNewItemDevolucion);
                    if (oldArtiOfItemDevolNewItemDevolucion != null && !oldArtiOfItemDevolNewItemDevolucion.equals(articulo)) {
                        oldArtiOfItemDevolNewItemDevolucion.getItemDevol().remove(itemDevolNewItemDevolucion);
                        oldArtiOfItemDevolNewItemDevolucion = em.merge(oldArtiOfItemDevolNewItemDevolucion);
                    }
                }
            }
            for (ItemVenta itemVtaOldItemVenta : itemVtaOld) {
                if (!itemVtaNew.contains(itemVtaOldItemVenta)) {
                    itemVtaOldItemVenta.setArticu(null);
                    itemVtaOldItemVenta = em.merge(itemVtaOldItemVenta);
                }
            }
            for (ItemVenta itemVtaNewItemVenta : itemVtaNew) {
                if (!itemVtaOld.contains(itemVtaNewItemVenta)) {
                    Articulo oldArticuOfItemVtaNewItemVenta = itemVtaNewItemVenta.getArticu();
                    itemVtaNewItemVenta.setArticu(articulo);
                    itemVtaNewItemVenta = em.merge(itemVtaNewItemVenta);
                    if (oldArticuOfItemVtaNewItemVenta != null && !oldArticuOfItemVtaNewItemVenta.equals(articulo)) {
                        oldArticuOfItemVtaNewItemVenta.getItemVta().remove(itemVtaNewItemVenta);
                        oldArticuOfItemVtaNewItemVenta = em.merge(oldArticuOfItemVtaNewItemVenta);
                    }
                }
            }
            for (ItemPresupuesto itemPresuOldItemPresupuesto : itemPresuOld) {
                if (!itemPresuNew.contains(itemPresuOldItemPresupuesto)) {
                    itemPresuOldItemPresupuesto.setArtic(null);
                    itemPresuOldItemPresupuesto = em.merge(itemPresuOldItemPresupuesto);
                }
            }
            for (ItemPresupuesto itemPresuNewItemPresupuesto : itemPresuNew) {
                if (!itemPresuOld.contains(itemPresuNewItemPresupuesto)) {
                    Articulo oldArticOfItemPresuNewItemPresupuesto = itemPresuNewItemPresupuesto.getArtic();
                    itemPresuNewItemPresupuesto.setArtic(articulo);
                    itemPresuNewItemPresupuesto = em.merge(itemPresuNewItemPresupuesto);
                    if (oldArticOfItemPresuNewItemPresupuesto != null && !oldArticOfItemPresuNewItemPresupuesto.equals(articulo)) {
                        oldArticOfItemPresuNewItemPresupuesto.getItemPresu().remove(itemPresuNewItemPresupuesto);
                        oldArticOfItemPresuNewItemPresupuesto = em.merge(oldArticOfItemPresuNewItemPresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = articulo.getIdArticulo();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getIdArticulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            Grupo grupo = articulo.getGrupo();
            if (grupo != null) {
                grupo.getArticulo().remove(articulo);
                grupo = em.merge(grupo);
            }
            LinkedList<ItemDevolucion> itemDevol = articulo.getItemDevol();
            for (ItemDevolucion itemDevolItemDevolucion : itemDevol) {
                itemDevolItemDevolucion.setArti(null);
                itemDevolItemDevolucion = em.merge(itemDevolItemDevolucion);
            }
            LinkedList<ItemVenta> itemVta = articulo.getItemVta();
            for (ItemVenta itemVtaItemVenta : itemVta) {
                itemVtaItemVenta.setArticu(null);
                itemVtaItemVenta = em.merge(itemVtaItemVenta);
            }
            LinkedList<ItemPresupuesto> itemPresu = articulo.getItemPresu();
            for (ItemPresupuesto itemPresuItemPresupuesto : itemPresu) {
                itemPresuItemPresupuesto.setArtic(null);
                itemPresuItemPresupuesto = em.merge(itemPresuItemPresupuesto);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Articulo findArticulo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
