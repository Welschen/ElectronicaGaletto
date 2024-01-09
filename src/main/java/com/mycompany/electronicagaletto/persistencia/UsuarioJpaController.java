
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.Venta;
import com.mycompany.electronicagaletto.logica.Auditoria;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getListaDevolucion() == null) {
            usuario.setListaDevolucion(new LinkedList<Devolucion>());
        }
        if (usuario.getListaVenta() == null) {
            usuario.setListaVenta(new LinkedList<Venta>());
        }
        if (usuario.getListaAuditoria() == null) {
            usuario.setListaAuditoria(new LinkedList<Auditoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Devolucion> attachedListaDevolucion = new LinkedList<Devolucion>();
            for (Devolucion listaDevolucionDevolucionToAttach : usuario.getListaDevolucion()) {
                listaDevolucionDevolucionToAttach = em.getReference(listaDevolucionDevolucionToAttach.getClass(), listaDevolucionDevolucionToAttach.getIdDevolucion());
                attachedListaDevolucion.add(listaDevolucionDevolucionToAttach);
            }
            usuario.setListaDevolucion(attachedListaDevolucion);
            LinkedList<Venta> attachedListaVenta = new LinkedList<Venta>();
            for (Venta listaVentaVentaToAttach : usuario.getListaVenta()) {
                listaVentaVentaToAttach = em.getReference(listaVentaVentaToAttach.getClass(), listaVentaVentaToAttach.getIdVenta());
                attachedListaVenta.add(listaVentaVentaToAttach);
            }
            usuario.setListaVenta(attachedListaVenta);
            LinkedList<Auditoria> attachedListaAuditoria = new LinkedList<Auditoria>();
            for (Auditoria listaAuditoriaAuditoriaToAttach : usuario.getListaAuditoria()) {
                listaAuditoriaAuditoriaToAttach = em.getReference(listaAuditoriaAuditoriaToAttach.getClass(), listaAuditoriaAuditoriaToAttach.getIdAuditoria());
                attachedListaAuditoria.add(listaAuditoriaAuditoriaToAttach);
            }
            usuario.setListaAuditoria(attachedListaAuditoria);
            em.persist(usuario);
            for (Devolucion listaDevolucionDevolucion : usuario.getListaDevolucion()) {
                Usuario oldUsuarioOfListaDevolucionDevolucion = listaDevolucionDevolucion.getUsuario();
                listaDevolucionDevolucion.setUsuario(usuario);
                listaDevolucionDevolucion = em.merge(listaDevolucionDevolucion);
                if (oldUsuarioOfListaDevolucionDevolucion != null) {
                    oldUsuarioOfListaDevolucionDevolucion.getListaDevolucion().remove(listaDevolucionDevolucion);
                    oldUsuarioOfListaDevolucionDevolucion = em.merge(oldUsuarioOfListaDevolucionDevolucion);
                }
            }
            for (Venta listaVentaVenta : usuario.getListaVenta()) {
                Usuario oldUsuOfListaVentaVenta = listaVentaVenta.getUsu();
                listaVentaVenta.setUsu(usuario);
                listaVentaVenta = em.merge(listaVentaVenta);
                if (oldUsuOfListaVentaVenta != null) {
                    oldUsuOfListaVentaVenta.getListaVenta().remove(listaVentaVenta);
                    oldUsuOfListaVentaVenta = em.merge(oldUsuOfListaVentaVenta);
                }
            }
            for (Auditoria listaAuditoriaAuditoria : usuario.getListaAuditoria()) {
                Usuario oldUsuarioOfListaAuditoriaAuditoria = listaAuditoriaAuditoria.getUsuario();
                listaAuditoriaAuditoria.setUsuario(usuario);
                listaAuditoriaAuditoria = em.merge(listaAuditoriaAuditoria);
                if (oldUsuarioOfListaAuditoriaAuditoria != null) {
                    oldUsuarioOfListaAuditoriaAuditoria.getListaAuditoria().remove(listaAuditoriaAuditoria);
                    oldUsuarioOfListaAuditoriaAuditoria = em.merge(oldUsuarioOfListaAuditoriaAuditoria);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            LinkedList<Devolucion> listaDevolucionOld = persistentUsuario.getListaDevolucion();
            LinkedList<Devolucion> listaDevolucionNew = usuario.getListaDevolucion();
            LinkedList<Venta> listaVentaOld = persistentUsuario.getListaVenta();
            LinkedList<Venta> listaVentaNew = usuario.getListaVenta();
            LinkedList<Auditoria> listaAuditoriaOld = persistentUsuario.getListaAuditoria();
            LinkedList<Auditoria> listaAuditoriaNew = usuario.getListaAuditoria();
            LinkedList<Devolucion> attachedListaDevolucionNew = new LinkedList<Devolucion>();
            for (Devolucion listaDevolucionNewDevolucionToAttach : listaDevolucionNew) {
                listaDevolucionNewDevolucionToAttach = em.getReference(listaDevolucionNewDevolucionToAttach.getClass(), listaDevolucionNewDevolucionToAttach.getIdDevolucion());
                attachedListaDevolucionNew.add(listaDevolucionNewDevolucionToAttach);
            }
            listaDevolucionNew = attachedListaDevolucionNew;
            usuario.setListaDevolucion(listaDevolucionNew);
            LinkedList<Venta> attachedListaVentaNew = new LinkedList<Venta>();
            for (Venta listaVentaNewVentaToAttach : listaVentaNew) {
                listaVentaNewVentaToAttach = em.getReference(listaVentaNewVentaToAttach.getClass(), listaVentaNewVentaToAttach.getIdVenta());
                attachedListaVentaNew.add(listaVentaNewVentaToAttach);
            }
            listaVentaNew = attachedListaVentaNew;
            usuario.setListaVenta(listaVentaNew);
            LinkedList<Auditoria> attachedListaAuditoriaNew = new LinkedList<Auditoria>();
            for (Auditoria listaAuditoriaNewAuditoriaToAttach : listaAuditoriaNew) {
                listaAuditoriaNewAuditoriaToAttach = em.getReference(listaAuditoriaNewAuditoriaToAttach.getClass(), listaAuditoriaNewAuditoriaToAttach.getIdAuditoria());
                attachedListaAuditoriaNew.add(listaAuditoriaNewAuditoriaToAttach);
            }
            listaAuditoriaNew = attachedListaAuditoriaNew;
            usuario.setListaAuditoria(listaAuditoriaNew);
            usuario = em.merge(usuario);
            for (Devolucion listaDevolucionOldDevolucion : listaDevolucionOld) {
                if (!listaDevolucionNew.contains(listaDevolucionOldDevolucion)) {
                    listaDevolucionOldDevolucion.setUsuario(null);
                    listaDevolucionOldDevolucion = em.merge(listaDevolucionOldDevolucion);
                }
            }
            for (Devolucion listaDevolucionNewDevolucion : listaDevolucionNew) {
                if (!listaDevolucionOld.contains(listaDevolucionNewDevolucion)) {
                    Usuario oldUsuarioOfListaDevolucionNewDevolucion = listaDevolucionNewDevolucion.getUsuario();
                    listaDevolucionNewDevolucion.setUsuario(usuario);
                    listaDevolucionNewDevolucion = em.merge(listaDevolucionNewDevolucion);
                    if (oldUsuarioOfListaDevolucionNewDevolucion != null && !oldUsuarioOfListaDevolucionNewDevolucion.equals(usuario)) {
                        oldUsuarioOfListaDevolucionNewDevolucion.getListaDevolucion().remove(listaDevolucionNewDevolucion);
                        oldUsuarioOfListaDevolucionNewDevolucion = em.merge(oldUsuarioOfListaDevolucionNewDevolucion);
                    }
                }
            }
            for (Venta listaVentaOldVenta : listaVentaOld) {
                if (!listaVentaNew.contains(listaVentaOldVenta)) {
                    listaVentaOldVenta.setUsu(null);
                    listaVentaOldVenta = em.merge(listaVentaOldVenta);
                }
            }
            for (Venta listaVentaNewVenta : listaVentaNew) {
                if (!listaVentaOld.contains(listaVentaNewVenta)) {
                    Usuario oldUsuOfListaVentaNewVenta = listaVentaNewVenta.getUsu();
                    listaVentaNewVenta.setUsu(usuario);
                    listaVentaNewVenta = em.merge(listaVentaNewVenta);
                    if (oldUsuOfListaVentaNewVenta != null && !oldUsuOfListaVentaNewVenta.equals(usuario)) {
                        oldUsuOfListaVentaNewVenta.getListaVenta().remove(listaVentaNewVenta);
                        oldUsuOfListaVentaNewVenta = em.merge(oldUsuOfListaVentaNewVenta);
                    }
                }
            }
            for (Auditoria listaAuditoriaOldAuditoria : listaAuditoriaOld) {
                if (!listaAuditoriaNew.contains(listaAuditoriaOldAuditoria)) {
                    listaAuditoriaOldAuditoria.setUsuario(null);
                    listaAuditoriaOldAuditoria = em.merge(listaAuditoriaOldAuditoria);
                }
            }
            for (Auditoria listaAuditoriaNewAuditoria : listaAuditoriaNew) {
                if (!listaAuditoriaOld.contains(listaAuditoriaNewAuditoria)) {
                    Usuario oldUsuarioOfListaAuditoriaNewAuditoria = listaAuditoriaNewAuditoria.getUsuario();
                    listaAuditoriaNewAuditoria.setUsuario(usuario);
                    listaAuditoriaNewAuditoria = em.merge(listaAuditoriaNewAuditoria);
                    if (oldUsuarioOfListaAuditoriaNewAuditoria != null && !oldUsuarioOfListaAuditoriaNewAuditoria.equals(usuario)) {
                        oldUsuarioOfListaAuditoriaNewAuditoria.getListaAuditoria().remove(listaAuditoriaNewAuditoria);
                        oldUsuarioOfListaAuditoriaNewAuditoria = em.merge(oldUsuarioOfListaAuditoriaNewAuditoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Devolucion> listaDevolucion = usuario.getListaDevolucion();
            for (Devolucion listaDevolucionDevolucion : listaDevolucion) {
                listaDevolucionDevolucion.setUsuario(null);
                listaDevolucionDevolucion = em.merge(listaDevolucionDevolucion);
            }
            LinkedList<Venta> listaVenta = usuario.getListaVenta();
            for (Venta listaVentaVenta : listaVenta) {
                listaVentaVenta.setUsu(null);
                listaVentaVenta = em.merge(listaVentaVenta);
            }
            LinkedList<Auditoria> listaAuditoria = usuario.getListaAuditoria();
            for (Auditoria listaAuditoriaAuditoria : listaAuditoria) {
                listaAuditoriaAuditoria.setUsuario(null);
                listaAuditoriaAuditoria = em.merge(listaAuditoriaAuditoria);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
