/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services.web;

import com.services.entities.Service;

/**
 *
 * @author vikaswarkar
 */
public abstract class AbstractController<T> {
    protected T currentRow;
    protected String nextPage;
    protected boolean showConfirmationMessage = false;
    
//    protected <T extends AbstractEntity> AbstractController(T currentRow){
//        this.currentRow = currentRow;
//    }
    
    
    public T getCurrentRow() {
        return currentRow;
    }
    
    public String prepareToEdit(T currentRow) {
        this.currentRow = currentRow;
        return getNextPage();
    }

    public String prepareToView(T currentRow) {
        this.currentRow = currentRow;
        return getNextPage();
    }

    public String prepareToDelete(T currentRow) {
        this.currentRow = currentRow;
        setShowConfirmationMessage(true);
        return getNextPage();
    }
    
    public void setCurrentRow(T currentRow) {
        this.currentRow = currentRow;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public boolean showConfirmationMessage(Service current) {
        boolean retValue = false;
        if (current!= null && this.currentRow!= null) {
//            retValue = (showConfirmationMessage && current.getServiceId() == this.((Service)current).getServiceId());  
        }
        return retValue;
    }

    public void setShowConfirmationMessage(boolean showConfirmationMessage) {
        this.showConfirmationMessage = showConfirmationMessage;
    }    
    
}
