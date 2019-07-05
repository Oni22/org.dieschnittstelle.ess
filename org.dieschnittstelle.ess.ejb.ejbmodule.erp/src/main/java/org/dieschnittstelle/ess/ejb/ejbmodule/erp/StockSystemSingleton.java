package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.PointOfSaleCRUDLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/*
* EJB SERVER STARTET NICHT
* Alle seiten abgeschrieben
* TODO: Start server and yell herr kreutel look at this error
* */


@Singleton
@Remote(StockSystemRemote.class)
public class StockSystemSingleton  implements StockSystemRemote{

    //ejb:org.dieschnittstelle.ess.ejb/org.dieschnittstelle.ess.ejb.ejbmodule.erp/StockSystemSingleton!org.dieschnittstelle.ess.ejb.ejbmodule.erp.StockSystemRemote
    @EJB
    private StockItemCRUDLocal stockCRUD;

    @EJB
    private PointOfSaleCRUDLocal pointCRUD;

    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale point = pointCRUD.readPointOfSale(pointOfSaleId);
        StockItem si = stockCRUD.readStockItem(product,point);

        if(si == null){
            si = new StockItem(product,point,units);
            stockCRUD.createStockItem(si);
        }
        else{
            si.setUnits(si.getUnits() + units);
        }

    }
    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        addToStock(product,pointOfSaleId,-units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {

        List<IndividualisedProductItem> pi = new ArrayList<>();

        PointOfSale p = pointCRUD.readPointOfSale(pointOfSaleId);
        List<StockItem> si = stockCRUD.readStockItemsForPointOfSale(p);

        for(StockItem s : si){
            pi.add(s.getProduct());
        }

        return pi;

    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {

        List<IndividualisedProductItem> pi = new ArrayList<>();
        List<PointOfSale> points = pointCRUD.readAllPointsOfSale();

        for(PointOfSale p : points){
            for(IndividualisedProductItem i : getProductsOnStock(p.getId())){
                if(!pi.contains(i)){
                    pi.add(i);
                }
            }
        }

        return pi;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {

        PointOfSale ps = pointCRUD.readPointOfSale(pointOfSaleId);

        StockItem si = stockCRUD.readStockItem(product,ps);

        if(si == null){
            return 0;
        }

        return si.getUnits();
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {

        return stockCRUD.readStockItemsForProduct(product)
                .stream()
                .mapToInt(si -> si.getUnits())
                .sum();
    }

	 /* here you can use readStockItemsForProduct() and create a list of the stock items'
         pointOfSale Ids*/
    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {

        return stockCRUD.readStockItemsForProduct(product)
                .stream()
                .map(si -> si.getPos().getId())
                .collect(Collectors.toList());
    }
}
