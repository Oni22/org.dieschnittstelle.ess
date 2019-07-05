package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.ProductAtPosPK;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Local(StockItemCRUDLocal.class)
public class StockItemCRUDStateless implements StockItemCRUDLocal {


    @PersistenceContext(unitName = "erp_PU")
    EntityManager em;

    @Override
    public StockItem createStockItem(StockItem item) {
        item.setProduct(em.merge(item.getProduct()));
        em.persist(item);
        return item;
    }

    @Override
    public StockItem readStockItem(IndividualisedProductItem prod, PointOfSale pos) {
        return em.find(StockItem.class,new ProductAtPosPK(prod,pos));
    }

    @Override
    public StockItem updateStockItem(StockItem item) {
        return em.merge(item);
    }

    @Override
    public List<StockItem> readAllStockItems() {
        return em.createQuery("SELECT p FROM StockItem s AS p").getResultList();

    }

    @Override
    public List<StockItem> readStockItemsForProduct(IndividualisedProductItem prod) {

        return em.createQuery("SELECT t FROM StockItem AS t WHERE t.product =" + prod.getId()).getResultList();

    }

    @Override
    public List<StockItem> readStockItemsForPointOfSale(PointOfSale pos) {
        return em.createQuery("SELECT t FROM StockItem AS t WHERE t.pos =" + pos.getId()).getResultList();


    }
}
