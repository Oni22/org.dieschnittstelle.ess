package org.dieschnittstelle.ess.ejb.ejbmodule.erp;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.PointOfSaleCRUDLocal;
import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.StockItemCRUDLocal;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.List;


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

    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        return null;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        return null;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        return 0;
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        return 0;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        return null;
    }
}
