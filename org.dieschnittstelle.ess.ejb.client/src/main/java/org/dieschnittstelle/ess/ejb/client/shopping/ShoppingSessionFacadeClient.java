package org.dieschnittstelle.ess.ejb.client.shopping;

import org.apache.logging.log4j.Logger;

import org.dieschnittstelle.ess.ejb.client.ejbclients.EJBProxyFactory;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.ShoppingException;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping.ShoppingSessionFacade;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping.ShoppingSessionFacadeRemote;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

public class ShoppingSessionFacadeClient implements ShoppingBusinessDelegate {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(ShoppingSessionFacadeClient.class);

	/*
	 * TODO use a proxy for the ShoppingSessionFacadeRemote interface
	 */
	private ShoppingSessionFacadeRemote ssfProxy;

	public ShoppingSessionFacadeClient() {
		/* TODO: instantiate the proxy using the EJBProxyFactory (see the other client classes) */
		ssfProxy = EJBProxyFactory.getInstance().getProxy(ShoppingSessionFacadeRemote.class, "ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.crm/ShoppingSessionFacade!org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping.ShoppingSessionFacadeRemote?stateful");
	}

	/* TODO: implement the following methods using the proxy */

	@Override
	public void setTouchpoint(AbstractTouchpoint touchpoint) {
		ssfProxy.setTouchpoint(touchpoint);
	}

	@Override
	public void setCustomer(Customer customer) {
		ssfProxy.setCustomer(customer);
	}

	@Override
	public void addProduct(AbstractProduct product, int units) {
		ssfProxy.addProduct(product,units);
	}

	@Override
	public void purchase() throws ShoppingException {
		ssfProxy.purchase();
	}

}
