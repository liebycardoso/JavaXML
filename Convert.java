import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import org.w3c.dom.*;

/*
 * 0-ORDER_NUMBER,1-DATE,2-TOTAL_VALUE,3-TOTAL_ITEMS,4-TOTAL_DISCOUNT,5-CUSTOMER_NUMBER,
 * 6-CUSTOMER_FNAME,7-CUSTOMER_LNAME,8-ADDRESS,9-CITY,10-PROVINCE,11-COUNTRY,12-ZIPCODE,
 * 13-PROD_NUMBER,14-PROD_NAME,15-QUANTITY,16-UNIT_PRICE,17-UNIT_DISCOUNT
 */
/**
 * @author Lieby, Rodolfo, Valdeci
 * @version 1.0
 * @since 2018-11-15
 */
public class Convert {
	
	/**
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(new File("ORDER_ITEMS.csv"));
		scanner.nextLine(); //Skip header
		
		//Enables applications to obtain a parser that produces DOM object trees from XML documents.
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();		
		Document doc = docBuilder.newDocument();
		
		//Create element root purchase
		Element rootElmt = doc.createElement("purchase");
		doc.appendChild(rootElmt);
				
		Element order;
		Element item;
		
		ArrayList<Order> orderList = new ArrayList<>();
		Set<String> uniqueOrder = new HashSet<String>(); 
		
		//Iterate all over the CSV file 
		while (scanner.hasNextLine()) {
			
			//String row receive the current csv line record
			String row = scanner.nextLine();

			//The Array fields receive all columns values splited by the comma separator
		    String[] fields = row.split(",");
		    
		    //orderList is a copy of csv file
		    orderList.add(new Order(fields));
		    
		    //Only unique order id are stored on  uniqueOrder
		    uniqueOrder.add(fields[0]);	    
		}//while
		
		//for each order number:
		for(String orderId: uniqueOrder){
			
			//Search and return only the items with the same order number
			ArrayList<Order> filteredItems = (ArrayList<Order>) orderList //cast list to ArrayList
					.stream() //stream the content
					.filter(c -> c.orderId.equals(orderId))// filter order id
					.collect(Collectors.toList()); //Transform to List object
			
			//Create one element order per order number
			order = doc.createElement("order");
			order.setAttribute("number", filteredItems.get(0).orderId);
			order.setAttribute("date", filteredItems.get(0).orderDate);
			order.setAttribute("total_items", filteredItems.get(0).orderTotalItems);
			//order.setAttribute("value ", filteredItems.get(0).orderValue);
			rootElmt.appendChild(order);
			
			//For each item in the order
			for(Order s: filteredItems)
			{	
				//Create one element item for each item
				item = doc.createElement("item");
				System.out.println(s.orderId);
				item.setAttribute("PROD_NUMBER", s.prodId);
				item.setAttribute("PROD_NAME", s.prodName);
				item.setAttribute("QUANTITY", s.prodQuantity);
				item.setAttribute("UNIT_PRICE", s.prodValue);
				order.appendChild(item);

			}//for(Order s:
			//itemExists.forEach(System.out::println);			
		}//for(String orderId:
		
		
		System.out.println("Tamanho csv" + orderList.size());
		System.out.println("Tamanho unique" + uniqueOrder.size());
		
		scanner.close();
		
				
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult streamResult = new StreamResult(new File("P:\\lambton\\data.xml"));
		transformer.transform(source, streamResult);
		
		WindowForm myWindow = new WindowForm(); 
		myWindow.main(null);
		
		
	}//main

}//class