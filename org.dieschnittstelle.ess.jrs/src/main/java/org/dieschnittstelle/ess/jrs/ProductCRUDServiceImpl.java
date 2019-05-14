package org.dieschnittstelle.ess.jrs;

import java.util.List;

import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/*
UE JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	private GenericCRUDExecutor<AbstractProduct> productCRUD;

	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {

		// read out the dataAccessor
		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>)servletContext.getAttribute("productCRUD");
	}

	public AbstractProduct createProduct(
			IndividualisedProductItem prod) {
		return (IndividualisedProductItem)this.productCRUD.createObject(prod);

	}

	public List<AbstractProduct> readAllProducts() {
		return (List)this.productCRUD.readAllObjects();
	}


	public AbstractProduct updateProduct(long id,
										 AbstractProduct update) {
		update.setId(id);
		return (AbstractProduct)this.productCRUD.updateObject(update);
	}

	public boolean deleteProduct(long id) {
		return this.productCRUD.deleteObject(id);
	}

	public AbstractProduct readProduct(long id) {
		return (AbstractProduct)this.productCRUD.readObject(id);
	}
	
}
