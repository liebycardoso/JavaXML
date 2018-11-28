import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.*;

/*
 * 0-ORDER_NUMBER,1-DATE,2-TOTAL_VALUE,3-TOTAL_ITEMS,4-TOTAL_DISCOUNT,5-CUSTOMER_NUMBER,
 * 6-CUSTOMER_FNAME,7-CUSTOMER_LNAME,8-ADDRESS,9-CITY,10-PROVINCE,11-COUNTRY,12-ZIPCODE,
 * 13-PROD_NUMBER,14-PROD_NAME,15-QUANTITY,16-UNIT_PRICE,17-UNIT_DISCOUNT
 */
public class Convert {
	
	public static void main(String[] args) throws Exception {
	
				
		Scanner scanner = new Scanner(new File("ORDER_ITEMS.csv"));
		scanner.nextLine(); //Skip header
		String orderNumber = "";
		boolean child=true;
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();		
		Document doc = docBuilder.newDocument();
		Element rootElmt = doc.createElement("purchase");
		doc.appendChild(rootElmt);
		ArrayList<String> previewsLine = new ArrayList<String>(); 
		previewsLine.add("0");
		
		Element order;
		Element item;
		
		
		while (scanner.hasNextLine()) {
			String row = scanner.nextLine();
			 
		    String[] fields = row.split(",");
		    child = (orderNumber.equals(fields[0]));
		    order = doc.createElement("order");
		    			
		    System.out.println("row:" + fields[0]);
			System.out.println("preview:" + previewsLine.get(previewsLine.size() - 1));

					    
//		    if (!child){
//		    	orderNumber=fields[0];		    	
//				/*
//				order.setAttribute("Number", fields[0]);
//				order.setAttribute("Date", fields[1]);
//				order.setAttribute("total_value", fields[2]);
//				order.setAttribute("Total_Itens", fields[3]);
//				order.setAttribute("Total_discount", fields[4]);
//				*/
//				//rootElmt.appendChild(order);
//				/*
//				Element customer = doc.createElement("Customer");
//				customer.appendChild(doc.createTextNode("Address2"));
//				Element fname = doc.createElement("Fname");
//				fname.appendChild(doc.createTextNode(fields[6]));
//				fname = doc.createElement("Lname");
//				fname.appendChild(doc.createTextNode(fields[6]));				
//				customer.appendChild(fname);
//				fname = doc.createElement("Lname");
//				fname.appendChild(doc.createTextNode(fields[7]));				
//				customer.appendChild(fname);
//				//customer.setAttribute("FirstName", fields[5]);
//				//customer.setAttribute("lastName", fields[6]);
//				order.appendChild(customer);
//				
//				Element shipping = doc.createElement("Shipping");
//				shipping.appendChild(doc.createTextNode("Address2"));
//				order.appendChild(shipping);
//				*/
//				//order.appendChild(item);
//				item.setAttribute("PROD_NUMBER", fields[13]);
//				item.setAttribute("PROD_NAME", fields[14]);
//				item.setAttribute("QUANTITY", fields[15]);
//				item.setAttribute("UNIT_PRICE", fields[16]);
//				item.setAttribute("UNIT_DISCOUNT", fields[17]);
//				//order.appendChild(item);
//		    }else{
//		    	//Integer.parseInt(fields[0]);
//		    	//order.appendChild(item);
//				item.setAttribute("PROD_NUMBER", fields[13]);
//				item.setAttribute("PROD_NAME", fields[14]);
//				item.setAttribute("QUANTITY", fields[15]);
//				item.setAttribute("UNIT_PRICE", fields[16]);
//				item.setAttribute("UNIT_DISCOUNT", fields[17]);
//				//order.appendChild(item);
//		    	//System.out.println(fields[0]);	    			    
//
//		    }
		    if (previewsLine.get(previewsLine.size() - 1).equals(fields[0])){
		    	System.out.println("igual:");
		    	//order.appendChild(item);
		    	//rootElmt.appendChild(order);
		    	item = doc.createElement("item");
		    	item.setAttribute("PROD_NUMBER", fields[13]);
				item.setAttribute("PROD_NAME", fields[14]);
				item.setAttribute("QUANTITY", fields[15]);
				item.setAttribute("UNIT_PRICE", fields[16]);
				item.setAttribute("UNIT_DISCOUNT", fields[17]);
				order.appendChild(item);
				
		    }
		    else{
		    	rootElmt.appendChild(order);
		    	item = doc.createElement("item");
		    	item.setAttribute("PROD_NUMBER", fields[13]);
				item.setAttribute("PROD_NAME", fields[14]);
				item.setAttribute("QUANTITY", fields[15]);
				item.setAttribute("UNIT_PRICE", fields[16]);
				item.setAttribute("UNIT_DISCOUNT", fields[17]);
		    	order.appendChild(item);
		    	item = doc.createElement("item");
		    	item.setAttribute("PROD_NUMBER", fields[13]);
				item.setAttribute("PROD_NAME", fields[14]);
				item.setAttribute("QUANTITY", fields[15]);
				item.setAttribute("UNIT_PRICE", fields[16]);
				item.setAttribute("UNIT_DISCOUNT", fields[17]);
		    	order.appendChild(item);
		    }
		    //previewsLine.add(fields[0]);

	    	previewsLine.add(fields[0]);
		    
		    
	    	
		    
		    
		}
		scanner.close();
		
				
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult streamResult = new StreamResult(new File("P:\\lambton\\data.xml"));
		transformer.transform(source, streamResult);
		
		
		
	}//main
}//class