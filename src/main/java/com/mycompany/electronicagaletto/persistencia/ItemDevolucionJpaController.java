
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.ItemDevolucion;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ItemDevolucionJpaController implements Serializable {

    public ItemDevolucionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ItemDevolucionJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItemDevolucion itemDevolucion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo arti = itemDevolucion.getArti();
            if (arti != null) {
                arti = em.getReference(arti.getClass(), arti.getIdArticulo());
                itemDevolucion.setArti(arti);
            }
            Devolucion devo = itemDevolucion.getDevo();
            if (devo != null) {
                devo = em.getReference(devo.getClass(), devo.getIdDevolucion());
                itemDevolucion.setDevo(devo);
            }
            em.persist(itemDevolucion);
            if (arti != null) {
                arti.getItemDevol().add(itemDevolucion);
                arti = em.merge(arti);
            }
            if (devo != null) {
                devo.getItemDev().add(itemDevolucion);
                devo = em.merge(devo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItemDevolucion itemDevolucion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemDevolucion persistentItemDevolucion = em.find(ItemDevolucion.class, itemDevolucion.getIdItemDevolucion());
            Articulo artiOld = persistentItemDevolucion.getArti();
            Articulo artiNew = itemDevolucion.getArti();
            Devolucion devoOld = persistentItemDevolucion.getDevo();
            Devolucion devoNew = itemDevolucion.getDevo();
            if (artiNew != null) {
                artiNew = em.getReference(artiNew.getClass(), artiNew.getIdArticulo());
                itemDevolucion.setArti(artiNew);
            }
            if (devoNew != null) {
                devoNew = em.getReference(devoNew.getClass(), devoNew.getIdDevolucion());
                itemDevolucion.setDevo(devoNew);
            }
            itemDevolucion = em.merge(itemDevolucion);
            if (artiOld != null && !artiOld.equals(artiNew)) {
                artiOld.getItemDevol().remove(itemDevolucion);
                artiOld = em.merge(artiOld);
            }
            if (artiNew != null && !artiNew.equals(artiOld)) {
                artiNew.getItemDevol().add(itemDevolucion);
                artiNew = em.merge(artiNew);
            }
            if (devoOld != null && !devoOld.equals(devoNew)) {
                devoOld.getItemDev().remove(itemDevolucion);
                devoOld = em.merge(devoOld);
            }
            if (devoNew != null && !devoNew.equals(devoOld)) {
                devoNew.getItemDev().add(itemDevolucion);
                devoNew = em.merge(devoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = itemDevolucion.getIdItemDevolucion();
                if (findItemDevolucion(id) == null) {
                    throw new NonexistentEntityException("The itemDevolucion with id " + id + " no longer exists.");
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
            ItemDevolucion itemDevolucion;
            try {
                itemDevolucion = em.getReference(ItemDevolucion.class, id);
                itemDevolucion.getIdItemDevolucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemDevolucion with id " + id + " no longer exists.", enfe);
            }
            Articulo arti = itemDevolucion.getArti();
            if (arti != null) {
                arti.getItemDevol().remove(itemDevolucion);
                arti = em.merge(arti);
            }
            Devolucion devo = itemDevolucion.getDevo();
            if (devo != null) {
                devo.getItemDev().remove(itemDevolucion);
                devo = em.merge(devo);
            }
            em.remove(itemDevolucion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemDevolucion> findItemDevolucionEntities() {
        return findItemDevolucionEntities(true, -1, -1);
    }

    public List<ItemDevolucion> findItemDevolucionEntities(int maxResults, int firstResult) {
        return findItemDevolucionEntities(false, maxResults, firstResult);
    }

    private List<ItemDevolucion> findItemDevolucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemDevolucion.class));
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

    public ItemDevolucion findItemDevolucion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemDevolucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemDevolucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemDevolucion> rt = cq.from(ItemDevolucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
