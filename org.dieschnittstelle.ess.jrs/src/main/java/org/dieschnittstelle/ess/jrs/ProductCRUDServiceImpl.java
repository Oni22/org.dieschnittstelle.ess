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

	public IndividualisedProductItem createProduct(
			IndividualisedProductItem prod) {
		return (IndividualisedProductItem)this.productCRUD.createObject(prod);

	}

	public List<IndividualisedProductItem> readAllProducts() {
		return (List)this.productCRUD.readAllObjects();
	}


	public IndividualisedProductItem updateProduct(long id,
			IndividualisedProductItem update) {
		update.setId(id);
		return (IndividualisedProductItem)this.productCRUD.updateObject(update);
	}

	public boolean deleteProduct(long id) {
		return this.productCRUD.deleteObject(id);
	}

	public IndividualisedProductItem readProduct(long id) {
		return (IndividualisedProductItem)this.productCRUD.readObject(id);
	}
	
}
