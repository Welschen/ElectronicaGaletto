
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.ItemPresupuesto;
import com.mycompany.electronicagaletto.logica.Presupuesto;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PresupuestoJpaController implements Serializable {

    public PresupuestoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Presupuesto presupuesto) {
        if (presupuesto.getListaPresupuesto() == null) {
            presupuesto.setListaPresupuesto(new LinkedList<ItemPresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<ItemPresupuesto> attachedListaPresupuesto = new LinkedList<ItemPresupuesto>();
            for (ItemPresupuesto listaPresupuestoItemPresupuestoToAttach : presupuesto.getListaPresupuesto()) {
                listaPresupuestoItemPresupuestoToAttach = em.getReference(listaPresupuestoItemPresupuestoToAttach.getClass(), listaPresupuestoItemPresupuestoToAttach.getIdItemPresupuesto());
                attachedListaPresupuesto.add(listaPresupuestoItemPresupuestoToAttach);
            }
            presupuesto.setListaPresupuesto(attachedListaPresupuesto);
            em.persist(presupuesto);
            for (ItemPresupuesto listaPresupuestoItemPresupuesto : presupuesto.getListaPresupuesto()) {
                Presupuesto oldPresupuestOfListaPresupuestoItemPresupuesto = listaPresupuestoItemPresupuesto.getPresupuest();
                listaPresupuestoItemPresupuesto.setPresupuest(presupuesto);
                listaPresupuestoItemPresupuesto = em.merge(listaPresupuestoItemPresupuesto);
                if (oldPresupuestOfListaPresupuestoItemPresupuesto != null) {
                    oldPresupuestOfListaPresupuestoItemPresupuesto.getListaPresupuesto().remove(listaPresupuestoItemPresupuesto);
                    oldPresupuestOfListaPresupuestoItemPresupuesto = em.merge(oldPresupuestOfListaPresupuestoItemPresupuesto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Presupuesto presupuesto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Presupuesto persistentPresupuesto = em.find(Presupuesto.class, presupuesto.getIdPresupuesto());
            LinkedList<ItemPresupuesto> listaPresupuestoOld = persistentPresupuesto.getListaPresupuesto();
            LinkedList<ItemPresupuesto> listaPresupuestoNew = presupuesto.getListaPresupuesto();
            LinkedList<ItemPresupuesto> attachedListaPresupuestoNew = new LinkedList<ItemPresupuesto>();
            for (ItemPresupuesto listaPresupuestoNewItemPresupuestoToAttach : listaPresupuestoNew) {
                listaPresupuestoNewItemPresupuestoToAttach = em.getReference(listaPresupuestoNewItemPresupuestoToAttach.getClass(), listaPresupuestoNewItemPresupuestoToAttach.getIdItemPresupuesto());
                attachedListaPresupuestoNew.add(listaPresupuestoNewItemPresupuestoToAttach);
            }
            listaPresupuestoNew = attachedListaPresupuestoNew;
            presupuesto.setListaPresupuesto(listaPresupuestoNew);
            presupuesto = em.merge(presupuesto);
            for (ItemPresupuesto listaPresupuestoOldItemPresupuesto : listaPresupuestoOld) {
                if (!listaPresupuestoNew.contains(listaPresupuestoOldItemPresupuesto)) {
                    listaPresupuestoOldItemPresupuesto.setPresupuest(null);
                    listaPresupuestoOldItemPresupuesto = em.merge(listaPresupuestoOldItemPresupuesto);
                }
            }
            for (ItemPresupuesto listaPresupuestoNewItemPresupuesto : listaPresupuestoNew) {
                if (!listaPresupuestoOld.contains(listaPresupuestoNewItemPresupuesto)) {
                    Presupuesto oldPresupuestOfListaPresupuestoNewItemPresupuesto = listaPresupuestoNewItemPresupuesto.getPresupuest();
                    listaPresupuestoNewItemPresupuesto.setPresupuest(presupuesto);
                    listaPresupuestoNewItemPresupuesto = em.merge(listaPresupuestoNewItemPresupuesto);
                    if (oldPresupuestOfListaPresupuestoNewItemPresupuesto != null && !oldPresupuestOfListaPresupuestoNewItemPresupuesto.equals(presupuesto)) {
                        oldPresupuestOfListaPresupuestoNewItemPresupuesto.getListaPresupuesto().remove(listaPresupuestoNewItemPresupuesto);
                        oldPresupuestOfListaPresupuestoNewItemPresupuesto = em.merge(oldPresupuestOfListaPresupuestoNewItemPresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = presupuesto.getIdPresupuesto();
                if (findPresupuesto(id) == null) {
                    throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.");
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
            Presupuesto presupuesto;
            try {
                presupuesto = em.getReference(Presupuesto.class, id);
                presupuesto.getIdPresupuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.", enfe);
            }
            LinkedList<ItemPresupuesto> listaPresupuesto = presupuesto.getListaPresupuesto();
            for (ItemPresupuesto listaPresupuestoItemPresupuesto : listaPresupuesto) {
                listaPresupuestoItemPresupuesto.setPresupuest(null);
                listaPresupuestoItemPresupuesto = em.merge(listaPresupuestoItemPresupuesto);
            }
            em.remove(presupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Presupuesto> findPresupuestoEntities() {
        return findPresupuestoEntities(true, -1, -1);
    }

    public List<Presupuesto> findPresupuestoEntities(int maxResults, int firstResult) {
        return findPresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Presupuesto> findPresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Presupuesto.class));
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

    public Presupuesto findPresupuesto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Presupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Presupuesto> rt = cq.from(Presupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
