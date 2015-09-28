
package com.services.ejbs;

import com.services.entities.ServiceCategory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


public abstract class AbstractEJB<T> {
    private Class<T> entityClass;

    public AbstractEJB() {
    }

    public AbstractEJB(final Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @GET
    @Produces({
        "application/xml",
        "application/json"
    })    
    @Path("/allServices")
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));

        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findByParentID(ServiceCategory parent, String mappedBy) {

        CriteriaQuery<T> query = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> child = query.from(entityClass);
        query.where(getEntityManager().getCriteriaBuilder().equal(child.get(mappedBy),parent));

        List<T> result = getEntityManager().createQuery(query).getResultList();
        return result;
    }
    
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    public List<T> findRange(
        int[] range,
        CriteriaQuery query) {
        javax.persistence.Query q = getEntityManager().createQuery(query);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);

        return q.getResultList();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));

        javax.persistence.Query q = getEntityManager().createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();
    }
}
