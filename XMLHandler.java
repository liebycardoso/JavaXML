
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;


public class XMLHandler {
	
	//ORDER_NUMBER,DATE,TOTAL_VALUE,TOTAL_ITEMS,TOTAL_DISCOUNT,CUSTOMER_NUMBER,
	// CUSTOMER_FNAME,CUSTOMER_LNAME,ADDRESS,CITY,PROVINCE,COUNTRY,ZIPCODE,
	String[] params;
	String order, customer;
	
	
	
	public XMLHandler(String[] params) throws Exception{
		this.order = params[0];
		this.customer = params[5];
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();		
		Document doc = docBuilder.newDocument();
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public void createElementRoot(){
		
	}
	

}
