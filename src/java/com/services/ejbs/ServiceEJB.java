
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.ejbs;

import com.services.entities.Service;
import com.services.entities.ServiceCategory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


/**
 *
 * @author vikaswarkar
 */
@Stateless
@Path("/services")
public class ServiceEJB extends AbstractEJB<Service> {
    @PersistenceContext(unitName = "servicesPU")
    private EntityManager em;

    public ServiceEJB() {
        super(Service.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("/bycategory")
    @Produces({"application/xml", "application/json"})
    public List<Service> findServicesByCategoryId(@QueryParam("serviceCategoryId")Integer serviceCategoryId) {
        ServiceCategory parent = new ServiceCategory();
        parent.setServiceCategoryId(serviceCategoryId);
        return findByParentID(parent, "serviceCategory");
    }    
}
