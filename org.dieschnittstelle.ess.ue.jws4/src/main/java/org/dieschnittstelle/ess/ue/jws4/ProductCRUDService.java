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
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.ProductType;

/*
 * UE JWS4: machen Sie die Funktionalitaet dieser Klasse als Web Service verfuegbar und verwenden Sie fuer 
 * die Umetzung der Methoden die Instanz von GenericCRUDExecutor<AbstractProduct>,
 * die Sie aus dem ServletContext auslesen koennen
 */
@WebService(targetNamespace = "http://dieschnittstelle.org/ess/jws", name = "IProductCRUDService", serviceName = "ProductCRUDWebService", portName = "ProductCRUDPort")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ProductCRUDService {

	@Resource
	private WebServiceContext wscontext;

	public ProductCRUDService() {

	}

	@WebMethod
	public List<AbstractProduct> readAllProducts() {
		GenericCRUDExecutor<AbstractProduct> products = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");
		return products.readAllObjects();
	}

	@WebMethod
	public AbstractProduct createProduct(AbstractProduct product) {
		GenericCRUDExecutor<AbstractProduct> products = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");
		return products.createObject(product);
	}

	@WebMethod
	public AbstractProduct updateProduct(AbstractProduct update) {
		GenericCRUDExecutor<AbstractProduct> products = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");
		return products.updateObject(update);
	}

	@WebMethod
	public boolean deleteProduct(long id) {
		GenericCRUDExecutor<AbstractProduct> products = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");
		return products.deleteObject(id);
	}

	@WebMethod
	public IndividualisedProductItem readProduct(long id) {
		GenericCRUDExecutor<AbstractProduct> products = (GenericCRUDExecutor<AbstractProduct>) ((ServletContext) wscontext
				.getMessageContext().get(MessageContext.SERVLET_CONTEXT))
				.getAttribute("productCRUD");
		return (IndividualisedProductItem)products.readObject(id);
	}

}
