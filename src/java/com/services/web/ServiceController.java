
package com.services.web;

import com.services.entities.Service;
import com.services.entities.ServiceCategory;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean()
@SessionScoped
public class ServiceController extends BaseController implements Serializable {

    private static final long serialVersionUID = 2310259107429450847L;
    @EJB
    protected com.services.ejbs.ServiceEJB serviceEJB;
    private ServiceCategory serviceCategory;
    private List<Service> serviceList;
    
    public ServiceController() {
        super(new Service());
    }

    @Override
    public String prepareToCreate() {
        this.currentRow = new Service();
        return getNextPage();
    }
    
    public String create() {
        ((Service)currentRow).setServiceCategory(serviceCategory);
        serviceEJB.create((Service)currentRow);
        return getNextPage();
    }
    
    @Override
    public void updateDB() {
        serviceEJB.edit((Service)currentRow);
    }
    
    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public List<Service> getServiceList() {
        try {
            this.serviceList = serviceEJB.findByParentID(serviceCategory, "serviceCategory");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceList;
    }


            
}
