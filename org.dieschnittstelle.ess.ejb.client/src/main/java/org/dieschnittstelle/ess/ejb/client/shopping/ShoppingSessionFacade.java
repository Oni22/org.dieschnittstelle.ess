package org.dieschnittstelle.ess.ejb.client.shopping;

import org.dieschnittstelle.ess.ejb.ejbmodule.crm.*;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping.ShoppingSessionFacadeRemote;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;


@Stateful
@Remote
public class ShoppingSessionFacade implements ShoppingSessionFacadeRemote {

    @EJB
    private ShoppingCartLocal shoppingCart;

    @EJB
    private CustomerTrackingLocal customerTracking;

    @EJB
    private CampaignTrackingLocal campaignTracking;

    @Override
    public void setTouchpoint(AbstractTouchpoint touchpoint) {

    }

    @Override
    public void setCustomer(Customer customer) {

    }

    @Override
    public void addProduct(AbstractProduct product, int units) {

    }

    @Override
    public void purchase() throws ShoppingException {

    }
}
