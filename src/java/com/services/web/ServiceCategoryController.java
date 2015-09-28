
package com.services.web;

import com.services.entities.ServiceCategory;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "serviceCategoryController")
@SessionScoped
public class ServiceCategoryController extends BaseController implements Serializable {

    private static final long serialVersionUID = 2310259107429450847L;
    @EJB
    private com.services.ejbs.ServiceCategoryEJB serviceCategoryEJB;
    private List<ServiceCategory> serviceCategories;
    
    public ServiceCategoryController() {
        super(new ServiceCategory());
    }

    public List<ServiceCategory> getServiceCategories() {
        try {
            this.serviceCategories = serviceCategoryEJB.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return serviceCategories;
    }
    
    @Override
    public String prepareToCreate(){
        currentRow = new ServiceCategory();
        return getNextPage();
    }

    public String prepareToDelete(ServiceCategory serviceCategory){
        currentRow = serviceCategory;
        setShowConfirmationMessage(true);
        return "ServiceCategoryList";
    }
        
    public String create() {        
        serviceCategoryEJB.create((ServiceCategory)currentRow);
        return "ServiceCategoryList";
    }

    @Override
    void updateDB() {
        serviceCategoryEJB.edit((ServiceCategory)currentRow);
    }

    
}
