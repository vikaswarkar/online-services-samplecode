/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vikaswarkar
 */
@Entity
@Table(name = "SERVICE_CATEGORIES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceCategory.findAll", query = "SELECT s FROM ServiceCategory s"),
    @NamedQuery(name = "ServiceCategory.findById", query = "SELECT s FROM ServiceCategory s WHERE s.serviceCategoryId = :id"),
    @NamedQuery(name = "ServiceCategory.findByName", query = "SELECT s FROM ServiceCategory s WHERE s.name = :name"),
    @NamedQuery(name = "ServiceCategory.findByDescription", query = "SELECT s FROM ServiceCategory s WHERE s.description = :description")})
public class ServiceCategory extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "SERVICE_CATEGORY_ID")
    private Integer serviceCategoryId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 50, message = "{servicecategory.name}")
    @Column(name = "NAME")
    private String name;
    
    @Size(min = 3, max = 50, message = "{servicecategory.description}")
    @Column(name = "DESCRIPTION")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceCategory")
    private Collection<Service> services;

    public Integer getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Service> getServices() {
        return services;
    }

    public void setServices(Collection<Service> services) {
        this.services = services;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceCategoryId != null ? serviceCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the serviceCategoryId fields are not set
        if (!(object instanceof ServiceCategory)) {
            return false;
        }
        ServiceCategory other = (ServiceCategory) object;
        if ((this.serviceCategoryId == null && other.serviceCategoryId != null) || (this.serviceCategoryId != null && !this.serviceCategoryId.equals(other.serviceCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.services.entities.ServiceCategories[ id=" + serviceCategoryId + " ]";
    }

    public ServiceCategory() {
    }

    @Override
    public AbstractEntity getNewInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
