
package org.dieschnittstelle.ess.entities.erp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für individualisedProductItem complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="individualisedProductItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://dieschnittstelle.org/ess/entities/erp/ws}abstractProduct"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="expirationAfterStocked" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="productType" type="{http://dieschnittstelle.org/ess/entities/erp/ws}productType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "individualisedProductItem", propOrder = {
    "expirationAfterStocked",
    "productType"
})
public class IndividualisedProductItem
    extends AbstractProduct
{

    protected int expirationAfterStocked;
    @XmlSchemaType(name = "string")
    protected ProductType productType;

    /**
     * Ruft den Wert der expirationAfterStocked-Eigenschaft ab.
     * 
     */
    public int getExpirationAfterStocked() {
        return expirationAfterStocked;
    }

    /**
     * Legt den Wert der expirationAfterStocked-Eigenschaft fest.
     * 
     */
    public void setExpirationAfterStocked(int value) {
        this.expirationAfterStocked = value;
    }

    /**
     * Ruft den Wert der productType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ProductType }
     *     
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Legt den Wert der productType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductType }
     *     
     */
    public void setProductType(ProductType value) {
        this.productType = value;
    }

}
