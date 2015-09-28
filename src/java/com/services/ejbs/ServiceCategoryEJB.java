

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.ejbs;

import com.services.entities.ServiceCategory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author vikaswarkar
 */
@Stateless
public class ServiceCategoryEJB extends AbstractEJB<ServiceCategory> {
    @PersistenceContext(unitName = "servicesPU")
    private EntityManager em;

    public ServiceCategoryEJB() {
        super(ServiceCategory.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
