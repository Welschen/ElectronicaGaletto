/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicagaletto.persistencia;

import com.mycompany.electronicagaletto.logica.Devolucion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.logica.Venta;
import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DevolucionJpaController implements Serializable {

    public DevolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public DevolucionJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Devolucion devolucion) {
        if (devolucion.getItemDev() == null) {
            devolucion.setItemDev(new LinkedList<ItemDevolucion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = devolucion.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdUsuario());
                devolucion.setUsuario(usuario);
            }
            Venta venta = devolucion.getVenta();
            if (venta != null) {
                venta = em.getReference(venta.getClass(), venta.getIdVenta());
                devolucion.setVenta(venta);
            }
            LinkedList<ItemDevolucion> attachedItemDev = new LinkedList<ItemDevolucion>();
            for (ItemDevolucion itemDevItemDevolucionToAttach : devolucion.getItemDev()) {
                itemDevItemDevolucionToAttach = em.getReference(itemDevItemDevolucionToAttach.getClass(), itemDevItemDevolucionToAttach.getIdItemDevolucion());
                attachedItemDev.add(itemDevItemDevolucionToAttach);
            }
            devolucion.setItemDev(attachedItemDev);
            em.persist(devolucion);
            if (usuario != null) {
                usuario.getListaDevolucion().add(devolucion);
                usuario = em.merge(usuario);
            }
            if (venta != null) {
                venta.getListaDevolucion().add(devolucion);
                venta = em.merge(venta);
            }
            for (ItemDevolucion itemDevItemDevolucion : devolucion.getItemDev()) {
                Devolucion oldDevoOfItemDevItemDevolucion = itemDevItemDevolucion.getDevo();
                itemDevItemDevolucion.setDevo(devolucion);
                itemDevItemDevolucion = em.merge(itemDevItemDevolucion);
                if (oldDevoOfItemDevItemDevolucion != null) {
                    oldDevoOfItemDevItemDevolucion.getItemDev().remove(itemDevItemDevolucion);
                    oldDevoOfItemDevItemDevolucion = em.merge(oldDevoOfItemDevItemDevolucion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Devolucion devolucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Devolucion persistentDevolucion = em.find(Devolucion.class, devolucion.getIdDevolucion());
            Usuario usuarioOld = persistentDevolucion.getUsuario();
            Usuario usuarioNew = devolucion.getUsuario();
            Venta ventaOld = persistentDevolucion.getVenta();
            Venta ventaNew = devolucion.getVenta();
            LinkedList<ItemDevolucion> itemDevOld = persistentDevolucion.getItemDev();
            LinkedList<ItemDevolucion> itemDevNew = devolucion.getItemDev();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdUsuario());
                devolucion.setUsuario(usuarioNew);
            }
            if (ventaNew != null) {
                ventaNew = em.getReference(ventaNew.getClass(), ventaNew.getIdVenta());
                devolucion.setVenta(ventaNew);
            }
            LinkedList<ItemDevolucion> attachedItemDevNew = new LinkedList<ItemDevolucion>();
            for (ItemDevolucion itemDevNewItemDevolucionToAttach : itemDevNew) {
                itemDevNewItemDevolucionToAttach = em.getReference(itemDevNewItemDevolucionToAttach.getClass(), itemDevNewItemDevolucionToAttach.getIdItemDevolucion());
                attachedItemDevNew.add(itemDevNewItemDevolucionToAttach);
            }
            itemDevNew = attachedItemDevNew;
            devolucion.setItemDev(itemDevNew);
            devolucion = em.merge(devolucion);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getListaDevolucion().remove(devolucion);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getListaDevolucion().add(devolucion);
                usuarioNew = em.merge(usuarioNew);
            }
            if (ventaOld != null && !ventaOld.equals(ventaNew)) {
                ventaOld.getListaDevolucion().remove(devolucion);
                ventaOld = em.merge(ventaOld);
            }
            if (ventaNew != null && !ventaNew.equals(ventaOld)) {
                ventaNew.getListaDevolucion().add(devolucion);
                ventaNew = em.merge(ventaNew);
            }
            for (ItemDevolucion itemDevOldItemDevolucion : itemDevOld) {
                if (!itemDevNew.contains(itemDevOldItemDevolucion)) {
                    itemDevOldItemDevolucion.setDevo(null);
                    itemDevOldItemDevolucion = em.merge(itemDevOldItemDevolucion);
                }
            }
            for (ItemDevolucion itemDevNewItemDevolucion : itemDevNew) {
                if (!itemDevOld.contains(itemDevNewItemDevolucion)) {
                    Devolucion oldDevoOfItemDevNewItemDevolucion = itemDevNewItemDevolucion.getDevo();
                    itemDevNewItemDevolucion.setDevo(devolucion);
                    itemDevNewItemDevolucion = em.merge(itemDevNewItemDevolucion);
                    if (oldDevoOfItemDevNewItemDevolucion != null && !oldDevoOfItemDevNewItemDevolucion.equals(devolucion)) {
                        oldDevoOfItemDevNewItemDevolucion.getItemDev().remove(itemDevNewItemDevolucion);
                        oldDevoOfItemDevNewItemDevolucion = em.merge(oldDevoOfItemDevNewItemDevolucion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = devolucion.getIdDevolucion();
                if (findDevolucion(id) == null) {
                    throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.");
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
            Devolucion devolucion;
            try {
                devolucion = em.getReference(Devolucion.class, id);
                devolucion.getIdDevolucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = devolucion.getUsuario();
            if (usuario != null) {
                usuario.getListaDevolucion().remove(devolucion);
                usuario = em.merge(usuario);
            }
            Venta venta = devolucion.getVenta();
            if (venta != null) {
                venta.getListaDevolucion().remove(devolucion);
                venta = em.merge(venta);
            }
            LinkedList<ItemDevolucion> itemDev = devolucion.getItemDev();
            for (ItemDevolucion itemDevItemDevolucion : itemDev) {
                itemDevItemDevolucion.setDevo(null);
                itemDevItemDevolucion = em.merge(itemDevItemDevolucion);
            }
            em.remove(devolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Devolucion> findDevolucionEntities() {
        return findDevolucionEntities(true, -1, -1);
    }

    public List<Devolucion> findDevolucionEntities(int maxResults, int firstResult) {
        return findDevolucionEntities(false, maxResults, firstResult);
    }

    private List<Devolucion> findDevolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Devolucion.class));
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

    public Devolucion findDevolucion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Devolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDevolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Devolucion> rt = cq.from(Devolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
