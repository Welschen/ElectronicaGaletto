
package com.mycompany.electronicagaletto.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Cliente;
import com.mycompany.electronicagaletto.logica.Usuario;
import com.mycompany.electronicagaletto.logica.Devolucion;
import com.mycompany.electronicagaletto.logica.Venta;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public VentaJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        if (venta.getListaDevolucion() == null) {
            venta.setListaDevolucion(new LinkedList<Devolucion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente client = venta.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getIdCliente());
                venta.setClient(client);
            }
            Usuario usu = venta.getUsu();
            if (usu != null) {
                usu = em.getReference(usu.getClass(), usu.getIdUsuario());
                venta.setUsu(usu);
            }
            LinkedList<Devolucion> attachedListaDevolucion = new LinkedList<Devolucion>();
            for (Devolucion listaDevolucionDevolucionToAttach : venta.getListaDevolucion()) {
                listaDevolucionDevolucionToAttach = em.getReference(listaDevolucionDevolucionToAttach.getClass(), listaDevolucionDevolucionToAttach.getIdDevolucion());
                attachedListaDevolucion.add(listaDevolucionDevolucionToAttach);
            }
            venta.setListaDevolucion(attachedListaDevolucion);
            em.persist(venta);
            if (client != null) {
                client.getListaVentas().add(venta);
                client = em.merge(client);
            }
            if (usu != null) {
                usu.getListaVenta().add(venta);
                usu = em.merge(usu);
            }
            for (Devolucion listaDevolucionDevolucion : venta.getListaDevolucion()) {
                Venta oldVentaOfListaDevolucionDevolucion = listaDevolucionDevolucion.getVenta();
                listaDevolucionDevolucion.setVenta(venta);
                listaDevolucionDevolucion = em.merge(listaDevolucionDevolucion);
                if (oldVentaOfListaDevolucionDevolucion != null) {
                    oldVentaOfListaDevolucionDevolucion.getListaDevolucion().remove(listaDevolucionDevolucion);
                    oldVentaOfListaDevolucionDevolucion = em.merge(oldVentaOfListaDevolucionDevolucion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getIdVenta());
            Cliente clientOld = persistentVenta.getClient();
            Cliente clientNew = venta.getClient();
            Usuario usuOld = persistentVenta.getUsu();
            Usuario usuNew = venta.getUsu();
            LinkedList<Devolucion> listaDevolucionOld = persistentVenta.getListaDevolucion();
            LinkedList<Devolucion> listaDevolucionNew = venta.getListaDevolucion();
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getIdCliente());
                venta.setClient(clientNew);
            }
            if (usuNew != null) {
                usuNew = em.getReference(usuNew.getClass(), usuNew.getIdUsuario());
                venta.setUsu(usuNew);
            }
            LinkedList<Devolucion> attachedListaDevolucionNew = new LinkedList<Devolucion>();
            for (Devolucion listaDevolucionNewDevolucionToAttach : listaDevolucionNew) {
                listaDevolucionNewDevolucionToAttach = em.getReference(listaDevolucionNewDevolucionToAttach.getClass(), listaDevolucionNewDevolucionToAttach.getIdDevolucion());
                attachedListaDevolucionNew.add(listaDevolucionNewDevolucionToAttach);
            }
            listaDevolucionNew = attachedListaDevolucionNew;
            venta.setListaDevolucion(listaDevolucionNew);
            venta = em.merge(venta);
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getListaVentas().remove(venta);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getListaVentas().add(venta);
                clientNew = em.merge(clientNew);
            }
            if (usuOld != null && !usuOld.equals(usuNew)) {
                usuOld.getListaVenta().remove(venta);
                usuOld = em.merge(usuOld);
            }
            if (usuNew != null && !usuNew.equals(usuOld)) {
                usuNew.getListaVenta().add(venta);
                usuNew = em.merge(usuNew);
            }
            for (Devolucion listaDevolucionOldDevolucion : listaDevolucionOld) {
                if (!listaDevolucionNew.contains(listaDevolucionOldDevolucion)) {
                    listaDevolucionOldDevolucion.setVenta(null);
                    listaDevolucionOldDevolucion = em.merge(listaDevolucionOldDevolucion);
                }
            }
            for (Devolucion listaDevolucionNewDevolucion : listaDevolucionNew) {
                if (!listaDevolucionOld.contains(listaDevolucionNewDevolucion)) {
                    Venta oldVentaOfListaDevolucionNewDevolucion = listaDevolucionNewDevolucion.getVenta();
                    listaDevolucionNewDevolucion.setVenta(venta);
                    listaDevolucionNewDevolucion = em.merge(listaDevolucionNewDevolucion);
                    if (oldVentaOfListaDevolucionNewDevolucion != null && !oldVentaOfListaDevolucionNewDevolucion.equals(venta)) {
                        oldVentaOfListaDevolucionNewDevolucion.getListaDevolucion().remove(listaDevolucionNewDevolucion);
                        oldVentaOfListaDevolucionNewDevolucion = em.merge(oldVentaOfListaDevolucionNewDevolucion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = venta.getIdVenta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getIdVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Cliente client = venta.getClient();
            if (client != null) {
                client.getListaVentas().remove(venta);
                client = em.merge(client);
            }
            Usuario usu = venta.getUsu();
            if (usu != null) {
                usu.getListaVenta().remove(venta);
                usu = em.merge(usu);
            }
            LinkedList<Devolucion> listaDevolucion = venta.getListaDevolucion();
            for (Devolucion listaDevolucionDevolucion : listaDevolucion) {
                listaDevolucionDevolucion.setVenta(null);
                listaDevolucionDevolucion = em.merge(listaDevolucionDevolucion);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
