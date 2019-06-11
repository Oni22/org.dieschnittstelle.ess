package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud.ProductCRUDRemote;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductCRUDStateless implements ProductCRUDRemote {

    @PersistenceContext(unitName = "erp_PU")
    EntityManager em;


    @Override
    public AbstractProduct createProduct(AbstractProduct prod)
    {
        prod = em.merge(prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        return em.createQuery("SELECT p FROM AbstractProduct p").getResultList();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {

        if(em.contains(update)){
            em.remove(update);
            em.persist(update);
        }

        return update;
    }

    @Override
    public AbstractProduct readProduct(long productID) {

        return em.find(AbstractProduct.class,productID);
    }

    @Override
    public boolean deleteProduct(long productID) {
        try{
            AbstractProduct p = em.find(AbstractProduct.class,productID);
            em.remove(p);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
