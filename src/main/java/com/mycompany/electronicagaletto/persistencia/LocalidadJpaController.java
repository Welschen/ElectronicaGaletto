
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Domicilio;
import com.mycompany.electronicagaletto.logica.Localidad;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class LocalidadJpaController implements Serializable {

    public LocalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public LocalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Localidad localidad) {
        if (localidad.getListaDomicilios() == null) {
            localidad.setListaDomicilios(new LinkedList<Domicilio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Domicilio> attachedListaDomicilios = new LinkedList<Domicilio>();
            for (Domicilio listaDomiciliosDomicilioToAttach : localidad.getListaDomicilios()) {
                listaDomiciliosDomicilioToAttach = em.getReference(listaDomiciliosDomicilioToAttach.getClass(), listaDomiciliosDomicilioToAttach.getIdDomicilio());
                attachedListaDomicilios.add(listaDomiciliosDomicilioToAttach);
            }
            localidad.setListaDomicilios(attachedListaDomicilios);
            em.persist(localidad);
            for (Domicilio listaDomiciliosDomicilio : localidad.getListaDomicilios()) {
                Localidad oldLocalOfListaDomiciliosDomicilio = listaDomiciliosDomicilio.getLocal();
                listaDomiciliosDomicilio.setLocal(localidad);
                listaDomiciliosDomicilio = em.merge(listaDomiciliosDomicilio);
                if (oldLocalOfListaDomiciliosDomicilio != null) {
                    oldLocalOfListaDomiciliosDomicilio.getListaDomicilios().remove(listaDomiciliosDomicilio);
                    oldLocalOfListaDomiciliosDomicilio = em.merge(oldLocalOfListaDomiciliosDomicilio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localidad localidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Localidad persistentLocalidad = em.find(Localidad.class, localidad.getIdLocalidad());
            LinkedList<Domicilio> listaDomiciliosOld = persistentLocalidad.getListaDomicilios();
            LinkedList<Domicilio> listaDomiciliosNew = localidad.getListaDomicilios();
            LinkedList<Domicilio> attachedListaDomiciliosNew = new LinkedList<Domicilio>();
            for (Domicilio listaDomiciliosNewDomicilioToAttach : listaDomiciliosNew) {
                listaDomiciliosNewDomicilioToAttach = em.getReference(listaDomiciliosNewDomicilioToAttach.getClass(), listaDomiciliosNewDomicilioToAttach.getIdDomicilio());
                attachedListaDomiciliosNew.add(listaDomiciliosNewDomicilioToAttach);
            }
            listaDomiciliosNew = attachedListaDomiciliosNew;
            localidad.setListaDomicilios(listaDomiciliosNew);
            localidad = em.merge(localidad);
            for (Domicilio listaDomiciliosOldDomicilio : listaDomiciliosOld) {
                if (!listaDomiciliosNew.contains(listaDomiciliosOldDomicilio)) {
                    listaDomiciliosOldDomicilio.setLocal(null);
                    listaDomiciliosOldDomicilio = em.merge(listaDomiciliosOldDomicilio);
                }
            }
            for (Domicilio listaDomiciliosNewDomicilio : listaDomiciliosNew) {
                if (!listaDomiciliosOld.contains(listaDomiciliosNewDomicilio)) {
                    Localidad oldLocalOfListaDomiciliosNewDomicilio = listaDomiciliosNewDomicilio.getLocal();
                    listaDomiciliosNewDomicilio.setLocal(localidad);
                    listaDomiciliosNewDomicilio = em.merge(listaDomiciliosNewDomicilio);
                    if (oldLocalOfListaDomiciliosNewDomicilio != null && !oldLocalOfListaDomiciliosNewDomicilio.equals(localidad)) {
                        oldLocalOfListaDomiciliosNewDomicilio.getListaDomicilios().remove(listaDomiciliosNewDomicilio);
                        oldLocalOfListaDomiciliosNewDomicilio = em.merge(oldLocalOfListaDomiciliosNewDomicilio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = localidad.getIdLocalidad();
                if (findLocalidad(id) == null) {
                    throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.");
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
            Localidad localidad;
            try {
                localidad = em.getReference(Localidad.class, id);
                localidad.getIdLocalidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localidad with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Domicilio> listaDomicilios = localidad.getListaDomicilios();
            for (Domicilio listaDomiciliosDomicilio : listaDomicilios) {
                listaDomiciliosDomicilio.setLocal(null);
                listaDomiciliosDomicilio = em.merge(listaDomiciliosDomicilio);
            }
            em.remove(localidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localidad> findLocalidadEntities() {
        return findLocalidadEntities(true, -1, -1);
    }

    public List<Localidad> findLocalidadEntities(int maxResults, int firstResult) {
        return findLocalidadEntities(false, maxResults, firstResult);
    }

    private List<Localidad> findLocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localidad.class));
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

    public Localidad findLocalidad(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localidad> rt = cq.from(Localidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
