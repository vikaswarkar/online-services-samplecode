/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.web;

import com.services.entities.AbstractEntity;
import com.services.entities.Service;

/**
 *
 * @author vikaswarkar
 */
public abstract class BaseController {
    protected AbstractEntity currentRow;
    protected String nextPage;
    protected boolean showConfirmationMessage = false;
    
    protected BaseController(AbstractEntity currentRow){
        this.currentRow = currentRow;
    }
    
    abstract String prepareToCreate();
    abstract void updateDB();
    
    public String update() {
        updateDB();
        return getNextPage();
    }
               
    public AbstractEntity getCurrentRow() {
        return currentRow;
    }
    
    public String prepareToEdit(AbstractEntity currentRow) {
        this.currentRow = currentRow;
        return getNextPage();
    }

    public String prepareToView(AbstractEntity currentRow) {
        this.currentRow = currentRow;
        return getNextPage();
    }

    public String prepareToDelete(AbstractEntity currentRow) {
        this.currentRow = currentRow;
        setShowConfirmationMessage(true);
        return getNextPage();
    }
    
    public void setCurrentRow(AbstractEntity currentRow) {
        this.currentRow = currentRow;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public boolean showConfirmationMessage(AbstractEntity currentRow) {
        boolean retValue = false;
        if (currentRow!= null && this.currentRow!= null) {
//            retValue = (showConfirmationMessage && current.getServiceId() == this.((Service)current).getServiceId());  
        }
        return retValue;
    }

    public void setShowConfirmationMessage(boolean showConfirmationMessage) {
        this.showConfirmationMessage = showConfirmationMessage;
    }    
    
}
