
package com.mycompany.electronicagaletto.persistencia;

import com.mycompany.electronicagaletto.logica.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.electronicagaletto.logica.Pago;

import com.mycompany.electronicagaletto.logica.Venta;
import com.mycompany.electronicagaletto.persistencia.exceptions.NonexistentEntityException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ClienteJpaController() {
        emf = Persistence.createEntityManagerFactory("ElectroGalettoJPAPU");
    }
     
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getListaPagos() == null) {
            cliente.setListaPagos(new LinkedList<Pago>());
        }
        if (cliente.getListaVentas() == null) {
            cliente.setListaVentas(new LinkedList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LinkedList<Pago> attachedListaPagos = new LinkedList<Pago>();
            for (Pago listaPagosPagoToAttach : cliente.getListaPagos()) {
                listaPagosPagoToAttach = em.getReference(listaPagosPagoToAttach.getClass(), listaPagosPagoToAttach.getIdPago());
                attachedListaPagos.add(listaPagosPagoToAttach);
            }
            cliente.setListaPagos(attachedListaPagos);
            LinkedList<Venta> attachedListaVentas = new LinkedList<Venta>();
            for (Venta listaVentasVentaToAttach : cliente.getListaVentas()) {
                listaVentasVentaToAttach = em.getReference(listaVentasVentaToAttach.getClass(), listaVentasVentaToAttach.getIdVenta());
                attachedListaVentas.add(listaVentasVentaToAttach);
            }
            cliente.setListaVentas(attachedListaVentas);
            em.persist(cliente);
            for (Pago listaPagosPago : cliente.getListaPagos()) {
                Cliente oldClienOfListaPagosPago = listaPagosPago.getClien();
                listaPagosPago.setClien(cliente);
                listaPagosPago = em.merge(listaPagosPago);
                if (oldClienOfListaPagosPago != null) {
                    oldClienOfListaPagosPago.getListaPagos().remove(listaPagosPago);
                    oldClienOfListaPagosPago = em.merge(oldClienOfListaPagosPago);
                }
            }
            for (Venta listaVentasVenta : cliente.getListaVentas()) {
                Cliente oldClientOfListaVentasVenta = listaVentasVenta.getClient();
                listaVentasVenta.setClient(cliente);
                listaVentasVenta = em.merge(listaVentasVenta);
                if (oldClientOfListaVentasVenta != null) {
                    oldClientOfListaVentasVenta.getListaVentas().remove(listaVentasVenta);
                    oldClientOfListaVentasVenta = em.merge(oldClientOfListaVentasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            LinkedList<Pago> listaPagosOld = persistentCliente.getListaPagos();
            LinkedList<Pago> listaPagosNew = cliente.getListaPagos();
            LinkedList<Venta> listaVentasOld = persistentCliente.getListaVentas();
            LinkedList<Venta> listaVentasNew = cliente.getListaVentas();
            LinkedList<Pago> attachedListaPagosNew = new LinkedList<Pago>();
            for (Pago listaPagosNewPagoToAttach : listaPagosNew) {
                listaPagosNewPagoToAttach = em.getReference(listaPagosNewPagoToAttach.getClass(), listaPagosNewPagoToAttach.getIdPago());
                attachedListaPagosNew.add(listaPagosNewPagoToAttach);
            }
            listaPagosNew = attachedListaPagosNew;
            cliente.setListaPagos(listaPagosNew);
            LinkedList<Venta> attachedListaVentasNew = new LinkedList<Venta>();
            for (Venta listaVentasNewVentaToAttach : listaVentasNew) {
                listaVentasNewVentaToAttach = em.getReference(listaVentasNewVentaToAttach.getClass(), listaVentasNewVentaToAttach.getIdVenta());
                attachedListaVentasNew.add(listaVentasNewVentaToAttach);
            }
            listaVentasNew = attachedListaVentasNew;
            cliente.setListaVentas(listaVentasNew);
            cliente = em.merge(cliente);
            for (Pago listaPagosOldPago : listaPagosOld) {
                if (!listaPagosNew.contains(listaPagosOldPago)) {
                    listaPagosOldPago.setClien(null);
                    listaPagosOldPago = em.merge(listaPagosOldPago);
                }
            }
            for (Pago listaPagosNewPago : listaPagosNew) {
                if (!listaPagosOld.contains(listaPagosNewPago)) {
                    Cliente oldClienOfListaPagosNewPago = listaPagosNewPago.getClien();
                    listaPagosNewPago.setClien(cliente);
                    listaPagosNewPago = em.merge(listaPagosNewPago);
                    if (oldClienOfListaPagosNewPago != null && !oldClienOfListaPagosNewPago.equals(cliente)) {
                        oldClienOfListaPagosNewPago.getListaPagos().remove(listaPagosNewPago);
                        oldClienOfListaPagosNewPago = em.merge(oldClienOfListaPagosNewPago);
                    }
                }
            }
            for (Venta listaVentasOldVenta : listaVentasOld) {
                if (!listaVentasNew.contains(listaVentasOldVenta)) {
                    listaVentasOldVenta.setClient(null);
                    listaVentasOldVenta = em.merge(listaVentasOldVenta);
                }
            }
            for (Venta listaVentasNewVenta : listaVentasNew) {
                if (!listaVentasOld.contains(listaVentasNewVenta)) {
                    Cliente oldClientOfListaVentasNewVenta = listaVentasNewVenta.getClient();
                    listaVentasNewVenta.setClient(cliente);
                    listaVentasNewVenta = em.merge(listaVentasNewVenta);
                    if (oldClientOfListaVentasNewVenta != null && !oldClientOfListaVentasNewVenta.equals(cliente)) {
                        oldClientOfListaVentasNewVenta.getListaVentas().remove(listaVentasNewVenta);
                        oldClientOfListaVentasNewVenta = em.merge(oldClientOfListaVentasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            LinkedList<Pago> listaPagos = cliente.getListaPagos();
            for (Pago listaPagosPago : listaPagos) {
                listaPagosPago.setClien(null);
                listaPagosPago = em.merge(listaPagosPago);
            }
            LinkedList<Venta> listaVentas = cliente.getListaVentas();
            for (Venta listaVentasVenta : listaVentas) {
                listaVentasVenta.setClient(null);
                listaVentasVenta = em.merge(listaVentasVenta);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
