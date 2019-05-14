package org.dieschnittstelle.ess.ue.jws4;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.ProductType;

/*
 * UE JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer 
 * die Umetzung der Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>,
 * die Sie aus dem ServletContext auslesen koennen
 */
@WebService()
public class ProductCRUDService {


	@Context
	private ServletContext servletContext;

	GenericCRUDExecutor<AbstractProduct> products;

	ProductCRUDService() {
		this.products = (GenericCRUDExecutor<AbstractProduct>)servletContext.getAttribute("productCRUD");
	}


	public List<AbstractProduct> readAllProducts() {
		return new ArrayList();
	}

	public AbstractProduct createProduct(AbstractProduct product) {
		return product;
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
		return update;
	}

	public boolean deleteProduct(long id) {
		return false;
	}

	public IndividualisedProductItem readProduct(long id) {
		return null;
	}

}
