
/**
 * Create a DOM object to manipulate arrayList and transform
 * it into a .XML object
 * 
 * @author Lieby,Rodolfo,Valdeci
 * @Since 2018-12-01
 * 
 * XML SCHEMA:
 * 
 * <purchase>
 *   <order>
 *     <item 1/>
 *     <item 2/>
 *     <item 3/>
 *   </order>
 * </purchase>
 */
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

	private Document doc;
	private Element order;
	private Element item;
	private Element rootElement;


	public XMLHandler(){	
		/**
		 * Creates the DocumentBuilder instance
		 */

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

	public void addElementRoot(String rootName)throws Exception{
		/**
		 * Creates a XML node root element
		 * @param rootName: A String to name the root node
		 */

		try{
			rootElement = doc.createElement(rootName);
			doc.appendChild(rootElement);
		} catch (Exception e){
			throw new Exception("The method createElementRoot failed to create the node", e); 
		}
	}

	public void addElement(String elementName, String childName, ArrayList<Order> orderItens)throws Exception{
		/**
		 * Creates a XML element.
		 * if the element has children, it creates a node of nested elements
		 * @param elementName: A String to name the element
		 * @param childName:  A String to name the child
		 * @param orderList: A list of objects to process
		 */
		try{
			order = doc.createElement(elementName);
			order.setAttribute("number", orderItens.get(0).orderId);
			order.setAttribute("date", orderItens.get(0).orderDate);
			order.setAttribute("total_items", orderItens.get(0).orderTotalItems);
			rootElement.appendChild(order);

			if(orderItens.size()>1){
				for(Order s: orderItens)
				{	
					//Create one element item for each item
					createNestedElement(s, childName);
				}//for(Order s:
			} else {
				createNestedElement(orderItens.get(0), childName);				
			}
		} catch (Exception e){
			throw new Exception("The method addElement failed to create the element", e); 
		}
	}	

	public void createNestedElement(Order orderItem, String childName){
		/**
		 * Create one element item for each item
		 */
		
		item = doc.createElement(childName);
		item.setAttribute("PROD_NUMBER", orderItem.prodId);
		item.setAttribute("PROD_NAME", orderItem.prodName);
		item.setAttribute("QUANTITY", orderItem.prodQuantity);
		item.setAttribute("UNIT_PRICE", orderItem.prodValue);
		order.appendChild(item);

		
	}
	public void streamToXML(String fileName) throws TransformerException{
		/**
		 * Transform a DOMSource object into a XML file.
		 * Saves the file in the specified directory
		 * @param fileName: The name of the XML file
		 */
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;

		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(new File(fileName));
			transformer.transform(source, streamResult);

		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			throw new TransformerConfigurationException("The method streamToXML failed", e);

		}
	}


}
