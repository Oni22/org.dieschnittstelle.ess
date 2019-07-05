package org.dieschnittstelle.ess.ejb.client.shopping;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.*;
import org.dieschnittstelle.ess.ejb.ejbmodule.crm.shopping.ShoppingSessionFacadeRemote;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRemote;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.crm.Customer;
import org.dieschnittstelle.ess.entities.crm.CustomerTransaction;
import org.dieschnittstelle.ess.entities.crm.ShoppingCartItem;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.ProductBundle;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.List;


@Stateful
@Remote(ShoppingSessionFacade.class)
public class ShoppingSessionFacade implements ShoppingSessionFacadeRemote {

    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ShoppingSessionFacade.class);

    @EJB
    private ShoppingCartLocal shoppingCart;

    @EJB
    private CustomerTrackingLocal customerTracking;

    @EJB
    private CampaignTrackingLocal campaignTracking;

    @EJB
    private ProductCRUDRemote productCRUD;

    @EJB
    private StockSystemRemote stockSystem;

    private Customer customer;

    private AbstractTouchpoint touchpoint;

    @Override
    public void setTouchpoint(AbstractTouchpoint touchpoint) {

    }

    @Override
    public void setCustomer(Customer customer) {

    }

    @Override
    public void addProduct(AbstractProduct product, int units) {

    }

    public void verifyCampaigns() throws ShoppingException {
        if (this.customer == null || this.touchpoint == null) {

            throw new RuntimeException("cannot verify campaigns! No touchpoint has been set!");

        }

        for (ShoppingCartItem item : this.shoppingCart.getItems()) {

            if (item.isCampaign()) {

                int availableCampaigns = this.campaignTracking.existsValidCampaignExecutionAtTouchpoint(

                        item.getErpProductId(), this.touchpoint);

                //logger.info("got available campaigns for product " + item.getErpProductId() + ": " + availableCampaigns);

                // we check whether we have sufficient campaign items available

                if (availableCampaigns < item.getUnits()) {

                    throw new ShoppingException("verifyCampaigns() failed for productBundle " + item

                            + " at touchpoint " + this.touchpoint + "! Need " + item.getUnits()

                            + " instances of campaign, but only got: " + availableCampaigns);

                }

            }

        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void purchase() throws ShoppingException {

        logger.info("purchase()");

        if (this.customer == null || this.touchpoint == null) {

            throw new RuntimeException("cannot commit shopping session! Either customer or touchpoint has not been set: " + this.customer + "/" + this.touchpoint);

        }        // verify the campaigns

        verifyCampaigns();        // remove the products from stock

        checkAndRemoveProductsFromStock();        // then we add a new customer transaction for the current purchase

        List<ShoppingCartItem> products = this.shoppingCart.getItems();
        CustomerTransaction transaction = new CustomerTransaction(this.customer, this.touchpoint, products);
        transaction.setCompleted(true);

        customerTracking.createTransaction(transaction);

        logger.info("purchase(): done.\n");

    }

    private void checkAndRemoveProductsFromStock() {

        logger.info("checkAndRemoveProductsFromStock");

        for (ShoppingCartItem item : this.shoppingCart.getItems()) {            // TODO: ermitteln Sie das AbstractProduct für das gegebene ShoppingCartItem. Nutzen Sie dafür dessen erpProductId und die ProductCRUD EJB            AbstractProduct prod = productCRUD.readProduct(item.getErpProductId());            if (item.isCampaign()) {


            AbstractProduct prod = productCRUD.readProduct(item.getErpProductId());

            if(item.isCampaign()) {


            this.campaignTracking.purchaseCampaignAtTouchpoint(item.getErpProductId(), this.touchpoint,

                    item.getUnits());                // TODO: wenn Sie eine Kampagne haben, muessen Sie hier

            // 1) ueber die ProductBundle Objekte auf dem Campaign Objekt iterieren, und

            Campaign campaign = (Campaign)prod;

            for(ProductBundle prodB : campaign.getBundles()){                    // 2) fuer jedes ProductBundle das betreffende Produkt in der auf dem Bundle angegebenen Anzahl, multipliziert mit dem Wert von


                int units = prodB.getUnits();
                int unitsStock = stockSystem.getUnitsOnStock(prodB.getProduct(),this.touchpoint.getErpPointOfSaleId());

                if(unitsStock >= units){

                    stockSystem.removeFromStock(prodB.getProduct(),this.touchpoint.getErpPointOfSaleId(),units);

                }

            }

            } else {

            // TODO: andernfalls (wenn keine Kampagne vorliegt) muessen Sie

            // 1) das Produkt in der in item.getUnits() angegebenen Anzahl hinsichtlich Verfuegbarkeit ueberpruefen und

            // 2) das Produkt, falls verfuegbar, in der entsprechenden Anzahl aus dem Warenlager entfernen                IndividualisedProductItem product = (IndividualisedProductItem) prod;                int unitsStock = stockSystem.getUnitsOnStock(product, this.touchpoint.getErpPointOfSaleId());

            IndividualisedProductItem product = (IndividualisedProductItem)prod;
            int unitsStock = stockSystem.getUnitsOnStock(product,this.touchpoint.getErpPointOfSaleId());

            if(unitsStock >= item.getUnits()){

                stockSystem.removeFromStock(product,this.touchpoint.getErpPointOfSaleId(),item.getUnits());

            }

        }
        }

}
}
