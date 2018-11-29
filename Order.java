
public class Order {
	String orderId, orderDate, orderValue, orderTotalItems;
	String prodId, prodName,  prodQuantity, prodValue;
	String customerId, customerName;
	
	/*
	 * 0-ORDER_NUMBER,1-DATE,2-TOTAL_VALUE,3-TOTAL_ITEMS,4-TOTAL_DISCOUNT,5-CUSTOMER_NUMBER,
	 * 6-CUSTOMER_FNAME,7-CUSTOMER_LNAME,8-ADDRESS,9-CITY,10-PROVINCE,11-COUNTRY,12-ZIPCODE,
	 * 13-PROD_NUMBER,14-prodName,15-QUANTITY,16-UNIT_PRICE,17-UNIT_DISCOUNT
	 */

	public Order(Object[] orderItem){
		// Order related variables
		this.orderId = (String) orderItem[0];
		this.orderDate = (String) orderItem[1];
		this.orderValue = (String) orderItem[2];
		this.orderTotalItems = (String) orderItem[3];

		// Customer related variables
		this.customerId = (String) orderItem[5];
		this.customerName = (String) orderItem[6] + " " + (String) orderItem[7];
		
		// Detail order - Item related variables
		this.prodId = (String) orderItem[13];
		this.prodName = (String) orderItem[14];
		this.prodQuantity = (String) orderItem[15];
		this.prodValue = (String) orderItem[16];
	}

	public String getId() {
		return orderId;
	}

	public void setId(String oId) {
		this.orderId = oId;
	}

	public String getprodId() {
		return prodId;
	}

	public void setprodId(String pId) {
		this.prodId = pId;
	}

	public String getprodName() {
		return prodName;
	}

	public void setprodName(String pName) {
		this.prodName = pName;
	}

	public String getprodQuantity() {
		return prodQuantity;
	}

	public void setprodQuantity(String pQuantity) {
		this.prodQuantity = pQuantity;
	}

	public String getprodValue() {
		return prodValue;
	}

	public void setprodValue(String pValue) {
		this.prodValue = pValue;
	}

	
	@Override
    public String toString() {
	    return "order [id=" + orderId + ", prodId=" + prodId+"]";
	  }
	
}
