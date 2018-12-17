import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * - This class receive a CSV file to process and
 * convert it into a .XML file
 * - Return a String message with the process status
 *  
 * @author Lieby, Rodolfo, Valdeci
 * @version 1.0
 * @since 2018-11-15
 * 
 * CSV LAYOUT - COLUMNS:
 * 
 * 0-ORDER_NUMBER,1-DATE,2-TOTAL_VALUE,3-TOTAL_ITEMS,4-TOTAL_DISCOUNT,5-CUSTOMER_NUMBER,
 * 6-CUSTOMER_FNAME,7-CUSTOMER_LNAME,8-ADDRESS,9-CITY,10-PROVINCE,11-COUNTRY,12-ZIPCODE,
 * 13-PROD_NUMBER,14-prodName,15-QUANTITY,16-UNIT_PRICE,17-UNIT_DISCOUNT
 */

public class JInterfaceFile {

	public static String convertFile(String fileName) throws Exception {
		/**
		 * @param fileName: CSV file path
		 * @return a String message with the process status
		 */

		String FILE_NAME = fileName;
		String msgStatus = "";
		Scanner scanner = new Scanner(new File(FILE_NAME));
		scanner.nextLine(); //Skip header

		// New object that parses DOM object from XML docs
		XMLHandler xml = new XMLHandler();

		// Create element Root
		xml.addElementRoot("purchase");

		// Create list to store all CSV columns and lines
		ArrayList<Order> orderList = new ArrayList<>();

		// Create a Set list composed by unique Orders numbers
		Set<String> uniqueOrder = new HashSet<String>(); 

		//Iterate all over the CSV file 
		while (scanner.hasNextLine()) {

			//String row receive the current CSV line record
			String row = scanner.nextLine();

			//The Array fields receive all columns values splitted by the comma separator
			String[] fields = row.split(",");
			
			if (fields.length < 18){
				msgStatus ="CSV layout out of the specified.";
				break;
				
			}
			//orderList is a copy of CSV file
			orderList.add(new Order(fields));

			//Only unique order id are stored on  uniqueOrder
			uniqueOrder.add(fields[0]);	    
		}//while

		if(msgStatus.equals("")){
			try{
				//for each order number:
				for(String orderId: uniqueOrder){
	
					//Search and return only the items with the same order number
					ArrayList<Order> filteredItems = (ArrayList<Order>) orderList //cast list to ArrayList
							.stream() //stream the content
							.filter(c -> c.orderId.equals(orderId))// filter order id
							.collect(Collectors.toList()); //Transform to List object
	
					// Create element order and child item
					xml.addElement("order", "item", filteredItems);
	
				}//for(String orderId:
	
				scanner.close();
	
				// Transform the doc object into .xml file
				xml.streamToXML(FILE_NAME.replace(".csv", ".xml"));
	
				msgStatus = "XML created successfully";
	
	
			}catch(Exception e){
				msgStatus = e.getMessage();
			}//try
		}
		return msgStatus;
	};//String convertFile()

}//class