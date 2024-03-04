
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.ItemVenta;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ItemVentaJpaController implements Serializable {

    public ItemVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ItemVentaJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItemVenta itemVenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo articu = itemVenta.getArticu();
            if (articu != null) {
                articu = em.getReference(articu.getClass(), articu.getIdArticulo());
                itemVenta.setArticu(articu);
            }
            em.persist(itemVenta);
            if (articu != null) {
                articu.getItemVta().add(itemVenta);
                articu = em.merge(articu);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItemVenta itemVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemVenta persistentItemVenta = em.find(ItemVenta.class, itemVenta.getIdItemVenta());
            Articulo articuOld = persistentItemVenta.getArticu();
            Articulo articuNew = itemVenta.getArticu();
            if (articuNew != null) {
                articuNew = em.getReference(articuNew.getClass(), articuNew.getIdArticulo());
                itemVenta.setArticu(articuNew);
            }
            itemVenta = em.merge(itemVenta);
            if (articuOld != null && !articuOld.equals(articuNew)) {
                articuOld.getItemVta().remove(itemVenta);
                articuOld = em.merge(articuOld);
            }
            if (articuNew != null && !articuNew.equals(articuOld)) {
                articuNew.getItemVta().add(itemVenta);
                articuNew = em.merge(articuNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = itemVenta.getIdItemVenta();
                if (findItemVenta(id) == null) {
                    throw new NonexistentEntityException("The itemVenta with id " + id + " no longer exists.");
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
            ItemVenta itemVenta;
            try {
                itemVenta = em.getReference(ItemVenta.class, id);
                itemVenta.getIdItemVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemVenta with id " + id + " no longer exists.", enfe);
            }
            Articulo articu = itemVenta.getArticu();
            if (articu != null) {
                articu.getItemVta().remove(itemVenta);
                articu = em.merge(articu);
            }
            em.remove(itemVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemVenta> findItemVentaEntities() {
        return findItemVentaEntities(true, -1, -1);
    }

    public List<ItemVenta> findItemVentaEntities(int maxResults, int firstResult) {
        return findItemVentaEntities(false, maxResults, firstResult);
    }

    private List<ItemVenta> findItemVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemVenta.class));
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

    public ItemVenta findItemVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemVenta> rt = cq.from(ItemVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
