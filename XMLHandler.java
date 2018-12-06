
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;


public class XMLHandler {
	
	//ORDER_NUMBER,DATE,TOTAL_VALUE,TOTAL_ITEMS,TOTAL_DISCOUNT,CUSTOMER_NUMBER,
	// CUSTOMER_FNAME,CUSTOMER_LNAME,ADDRESS,CITY,PROVINCE,COUNTRY,ZIPCODE,
	private Document doc;
	private Element order;
	private Element item;
	private Element rootElement;
	
	
	public XMLHandler(){	
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
			docBuilder = dbf.newDocumentBuilder();
			this.doc = docBuilder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
/*	
	public Document createXMLDoc() throws ParserConfigurationException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();		
		this.doc = docBuilder.newDocument();
		return this.doc;
	}
*/
	public void addElementRoot(String rootName)throws Exception{
		try{
		rootElement = doc.createElement(rootName);
		doc.appendChild(rootElement);
		} catch (Exception e){
			throw new Exception("The method createElementRoot failed to create the node", e); 
		}
		
	}
	
	public void addElement(String elementName, String childName, ArrayList<Order> orderItens)throws Exception{
		try{
		
			order = doc.createElement(elementName);
			order.setAttribute("number", orderItens.get(0).orderId);
			order.setAttribute("date", orderItens.get(0).orderDate);
			order.setAttribute("total_items", orderItens.get(0).orderTotalItems);
			//order.setAttribute("value ", filteredItems.get(0).orderValue);
			rootElement.appendChild(order);
			
			if(orderItens.size()>1){
				for(Order s: orderItens)
				{	
					//Create one element item for each item
					item = doc.createElement(childName);
					System.out.println(s.orderId);
					item.setAttribute("PROD_NUMBER", s.prodId);
					item.setAttribute("PROD_NAME", s.prodName);
					item.setAttribute("QUANTITY", s.prodQuantity);
					item.setAttribute("UNIT_PRICE", s.prodValue);
					order.appendChild(item);
	
				}//for(Order s:
			}
		} catch (Exception e){
			throw new Exception("The method addElement failed to create the element", e); 
		}
		
	}
	
	
	
	public void streamToXML(String fileName) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.println(fileName);
			StreamResult streamResult = new StreamResult(new File(fileName));
			transformer.transform(source, streamResult);

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			throw new TransformerConfigurationException("The method streamToXML failed", e);
			
		}
	}
	

}
