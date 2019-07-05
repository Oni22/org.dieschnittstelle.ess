package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;
import java.lang.annotation.*;
import java.lang.reflect.Method;

import static org.dieschnittstelle.ess.utils.Utils.*;


public class ShowAnnotations {

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


	private static void showAttributes(Object consumable)
	{

		Class klass = consumable.getClass();
		String result = "{"+klass.getSimpleName();

		for(Field field : klass.getDeclaredFields())
		{
			field.setAccessible(true);
			result +=  " " +GetAttributeName(field) + ":";

			try
			{
				result += field.get(consumable).toString() + ",";
			}
			catch (Exception e)
			{
				result += "Attribute does not match class";
			}
		}

		char[] stringAsArray = result.toCharArray();
		stringAsArray[stringAsArray.length -1] = '}';

		result = String.valueOf(stringAsArray);

		System.out.println(result);
	}


	private static String GetAttributeName(Field checkForAnnotation)
	{
		String outPut = " ";


		Annotation[] annotations =  checkForAnnotation.getDeclaredAnnotations();


			for(int i =0; i<= annotations.length-1;i++)
			{


				if(annotations[i].annotationType().toString().contains("DisplayAs"))
				{
						Class<? extends Annotation> type = annotations[i].annotationType();

						for (Method method : type.getDeclaredMethods())
						{
							method.setAccessible(true);
							try
							{
								Object value = method.invoke(annotations[i], (Object[]) null);
								//System.out.println(" " + method.getName() + ": " + value);
								return (String) value;
							}
							catch(Exception e)
							{
								return checkForAnnotation.getName();
							}
						}
					}

			}


		return checkForAnnotation.getName();

	}
}


