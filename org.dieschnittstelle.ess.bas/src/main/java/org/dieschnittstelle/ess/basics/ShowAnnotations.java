package org.dieschnittstelle.ess.basics;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;
import java.util.ArrayList;

import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface DisplayAs{
		String value();
	}

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			;
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}

	/*
	 * UE BAS2 
	 */
	private static void showAttributes(Object consumable) {

		Field[] fields = consumable.getClass().getDeclaredFields();
		String s = "";

		for(int i = 0; i < fields.length; i++){

			fields[i].setAccessible(true);

			DisplayAs da = null;

			//Check if this field has the annotation DisplayAs
			if(fields[i].isAnnotationPresent(DisplayAs.class))
				da = fields[i].getAnnotation(DisplayAs.class);


			try{

				s += "{<" + consumable.getClass().getSimpleName() + ">" + " <" +
						(da == null ? fields[i].getName() : da.value()) + ">:<" +
						fields[i].get(consumable) + ">},\n";

				System.out.println(s);
			}
			catch (Exception e){
				System.out.println(e);
			}
		}
	}

}
