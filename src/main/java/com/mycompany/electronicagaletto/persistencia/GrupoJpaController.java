
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Articulo;
import com.mycompany.electronicagaletto.logica.Grupo;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GrupoJpaController implements Serializable {

    public GrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public GrupoJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) {
        if (grupo.getArticulo() == null) {
            grupo.setArticulo(new LinkedList<Articulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Articulo> attachedArticulo = new LinkedList<Articulo>();
            for (Articulo articuloArticuloToAttach : grupo.getArticulo()) {
                articuloArticuloToAttach = em.getReference(articuloArticuloToAttach.getClass(), articuloArticuloToAttach.getIdArituclo());
                attachedArticulo.add(articuloArticuloToAttach);
            }
            grupo.setArticulo(attachedArticulo);
            em.persist(grupo);
            for (Articulo articuloArticulo : grupo.getArticulo()) {
                Grupo oldGrupoOfArticuloArticulo = articuloArticulo.getGrupo();
                articuloArticulo.setGrupo(grupo);
                articuloArticulo = em.merge(articuloArticulo);
                if (oldGrupoOfArticuloArticulo != null) {
                    oldGrupoOfArticuloArticulo.getArticulo().remove(articuloArticulo);
                    oldGrupoOfArticuloArticulo = em.merge(oldGrupoOfArticuloArticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getIdGrupo());
            LinkedList<Articulo> articuloOld = persistentGrupo.getArticulo();
            LinkedList<Articulo> articuloNew = grupo.getArticulo();
            LinkedList<Articulo> attachedArticuloNew = new LinkedList<Articulo>();
            for (Articulo articuloNewArticuloToAttach : articuloNew) {
                articuloNewArticuloToAttach = em.getReference(articuloNewArticuloToAttach.getClass(), articuloNewArticuloToAttach.getIdArituclo());
                attachedArticuloNew.add(articuloNewArticuloToAttach);
            }
            articuloNew = attachedArticuloNew;
            grupo.setArticulo(articuloNew);
            grupo = em.merge(grupo);
            for (Articulo articuloOldArticulo : articuloOld) {
                if (!articuloNew.contains(articuloOldArticulo)) {
                    articuloOldArticulo.setGrupo(null);
                    articuloOldArticulo = em.merge(articuloOldArticulo);
                }
            }
            for (Articulo articuloNewArticulo : articuloNew) {
                if (!articuloOld.contains(articuloNewArticulo)) {
                    Grupo oldGrupoOfArticuloNewArticulo = articuloNewArticulo.getGrupo();
                    articuloNewArticulo.setGrupo(grupo);
                    articuloNewArticulo = em.merge(articuloNewArticulo);
                    if (oldGrupoOfArticuloNewArticulo != null && !oldGrupoOfArticuloNewArticulo.equals(grupo)) {
                        oldGrupoOfArticuloNewArticulo.getArticulo().remove(articuloNewArticulo);
                        oldGrupoOfArticuloNewArticulo = em.merge(oldGrupoOfArticuloNewArticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = grupo.getIdGrupo();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getIdGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Articulo> articulo = grupo.getArticulo();
            for (Articulo articuloArticulo : articulo) {
                articuloArticulo.setGrupo(null);
                articuloArticulo = em.merge(articuloArticulo);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
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

    public Grupo findGrupo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
