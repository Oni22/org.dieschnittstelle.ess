package org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.Remote;
import javax.ejb.Stateful;

public interface ShoppingSessionFacadeRemote {

	public void setTouchpoint(AbstractTouchpoint touchpoint);
	
	public void setCustomer(Customer customer);
	
	public void addProduct(AbstractProduct product, int units);
	
	public void purchase() throws ShoppingException;
	
}
