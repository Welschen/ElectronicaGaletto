
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.ItemPresupuesto;
import com.mycompany.electronicagaletto.logica.Presupuesto;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ItemPresupuestoJpaController implements Serializable {

    public ItemPresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ItemPresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItemPresupuesto itemPresupuesto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo artic = itemPresupuesto.getArtic();
            if (artic != null) {
                artic = em.getReference(artic.getClass(), artic.getIdArticulo());
                itemPresupuesto.setArtic(artic);
            }
            Presupuesto presupuest = itemPresupuesto.getPresupuest();
            if (presupuest != null) {
                presupuest = em.getReference(presupuest.getClass(), presupuest.getIdPresupuesto());
                itemPresupuesto.setPresupuest(presupuest);
            }
            em.persist(itemPresupuesto);
            if (artic != null) {
                artic.getItemPresu().add(itemPresupuesto);
                artic = em.merge(artic);
            }
            if (presupuest != null) {
                presupuest.getListaPresupuesto().add(itemPresupuesto);
                presupuest = em.merge(presupuest);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItemPresupuesto itemPresupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemPresupuesto persistentItemPresupuesto = em.find(ItemPresupuesto.class, itemPresupuesto.getIdItemPresupuesto());
            Articulo articOld = persistentItemPresupuesto.getArtic();
            Articulo articNew = itemPresupuesto.getArtic();
            Presupuesto presupuestOld = persistentItemPresupuesto.getPresupuest();
            Presupuesto presupuestNew = itemPresupuesto.getPresupuest();
            if (articNew != null) {
                articNew = em.getReference(articNew.getClass(), articNew.getIdArticulo());
                itemPresupuesto.setArtic(articNew);
            }
            if (presupuestNew != null) {
                presupuestNew = em.getReference(presupuestNew.getClass(), presupuestNew.getIdPresupuesto());
                itemPresupuesto.setPresupuest(presupuestNew);
            }
            itemPresupuesto = em.merge(itemPresupuesto);
            if (articOld != null && !articOld.equals(articNew)) {
                articOld.getItemPresu().remove(itemPresupuesto);
                articOld = em.merge(articOld);
            }
            if (articNew != null && !articNew.equals(articOld)) {
                articNew.getItemPresu().add(itemPresupuesto);
                articNew = em.merge(articNew);
            }
            if (presupuestOld != null && !presupuestOld.equals(presupuestNew)) {
                presupuestOld.getListaPresupuesto().remove(itemPresupuesto);
                presupuestOld = em.merge(presupuestOld);
            }
            if (presupuestNew != null && !presupuestNew.equals(presupuestOld)) {
                presupuestNew.getListaPresupuesto().add(itemPresupuesto);
                presupuestNew = em.merge(presupuestNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = itemPresupuesto.getIdItemPresupuesto();
                if (findItemPresupuesto(id) == null) {
                    throw new NonexistentEntityException("The itemPresupuesto with id " + id + " no longer exists.");
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
            ItemPresupuesto itemPresupuesto;
            try {
                itemPresupuesto = em.getReference(ItemPresupuesto.class, id);
                itemPresupuesto.getIdItemPresupuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemPresupuesto with id " + id + " no longer exists.", enfe);
            }
            Articulo artic = itemPresupuesto.getArtic();
            if (artic != null) {
                artic.getItemPresu().remove(itemPresupuesto);
                artic = em.merge(artic);
            }
            Presupuesto presupuest = itemPresupuesto.getPresupuest();
            if (presupuest != null) {
                presupuest.getListaPresupuesto().remove(itemPresupuesto);
                presupuest = em.merge(presupuest);
            }
            em.remove(itemPresupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemPresupuesto> findItemPresupuestoEntities() {
        return findItemPresupuestoEntities(true, -1, -1);
    }

    public List<ItemPresupuesto> findItemPresupuestoEntities(int maxResults, int firstResult) {
        return findItemPresupuestoEntities(false, maxResults, firstResult);
    }

    private List<ItemPresupuesto> findItemPresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemPresupuesto.class));
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

    public ItemPresupuesto findItemPresupuesto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemPresupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemPresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemPresupuesto> rt = cq.from(ItemPresupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
