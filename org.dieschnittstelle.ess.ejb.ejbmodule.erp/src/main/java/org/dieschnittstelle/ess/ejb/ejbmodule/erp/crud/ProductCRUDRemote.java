package org.dieschnittstelle.ess.ejb.ejbmodule.erp.crud;

import java.util.List;

import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/*
 * TODO JPA3:
 * this interface shall be implemented using a stateless EJB with an EntityManager.
 * See TouchpointCRUDStateless for an example EJB with a similar scope of functionality
 */
@Remote
public interface ProductCRUDRemote {

	public AbstractProduct createProduct(AbstractProduct prod);

	public List<AbstractProduct> readAllProducts();

	public AbstractProduct updateProduct(AbstractProduct update);

	public AbstractProduct readProduct(long productID);

	public boolean deleteProduct(long productID);

}
