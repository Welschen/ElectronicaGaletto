/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicagaletto.persistencia;

import com.mycompany.electronicagaletto.logica.Auditoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AuditoriaJpaController implements Serializable {

    public AuditoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
   
    public AuditoriaJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Auditoria auditoria) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = auditoria.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdUsuario());
                auditoria.setUsuario(usuario);
            }
            em.persist(auditoria);
            if (usuario != null) {
                usuario.getListaAuditoria().add(auditoria);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Auditoria auditoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Auditoria persistentAuditoria = em.find(Auditoria.class, auditoria.getIdAuditoria());
            Usuario usuarioOld = persistentAuditoria.getUsuario();
            Usuario usuarioNew = auditoria.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdUsuario());
                auditoria.setUsuario(usuarioNew);
            }
            auditoria = em.merge(auditoria);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getListaAuditoria().remove(auditoria);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getListaAuditoria().add(auditoria);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = auditoria.getIdAuditoria();
                if (findAuditoria(id) == null) {
                    throw new NonexistentEntityException("The auditoria with id " + id + " no longer exists.");
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
            Auditoria auditoria;
            try {
                auditoria = em.getReference(Auditoria.class, id);
                auditoria.getIdAuditoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auditoria with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = auditoria.getUsuario();
            if (usuario != null) {
                usuario.getListaAuditoria().remove(auditoria);
                usuario = em.merge(usuario);
            }
            em.remove(auditoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Auditoria> findAuditoriaEntities() {
        return findAuditoriaEntities(true, -1, -1);
    }

    public List<Auditoria> findAuditoriaEntities(int maxResults, int firstResult) {
        return findAuditoriaEntities(false, maxResults, firstResult);
    }

    private List<Auditoria> findAuditoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Auditoria.class));
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

    public Auditoria findAuditoria(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Auditoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuditoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Auditoria> rt = cq.from(Auditoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
