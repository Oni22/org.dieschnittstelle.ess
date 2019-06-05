package org.dieschnittstelle.ess.ejb.ejbmodule.crm.crud;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductCRUDStateless implements ProductCRUDRemote {

    //@PersistenceContext()


    @Override
    public AbstractProduct createProduct(AbstractProduct prod) {
        return null;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        return new ArrayList<AbstractProduct>();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {
        return null;
    }

    @Override
    public AbstractProduct readProduct(long productID) {
        return null;
    }

    @Override
    public boolean deleteProduct(long productID) {
        return false;
    }
}
