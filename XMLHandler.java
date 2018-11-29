
import java.io.File;

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
	Document doc;
	
	
	public XMLHandler(Document doc) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();		
		this.doc = docBuilder.newDocument();
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
		Element rootElement = doc.createElement(rootName);
		doc.appendChild(rootElement);
		} catch (Exception e){
			throw new Exception("The method createElementRoot failed", e); 
		}
		
	}
	
	
	
	public void streamToXML(Document doc, String fileName) throws TransformerException{
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
