/**
 * Create the order object and its fields
 * @author User
 * @version 1.0
 * @since 2018-12-01
 * 
 */

public class Order {
	String orderId, orderDate, orderValue, orderTotalItems;
	String prodId, prodName,  prodQuantity, prodValue;
	String customerId, customerName;	


	public Order(Object[] orderItem){
		/**
		 * Creates an object Order features.
		 */
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

	public String getOrderId() {
		/**
		 * @return Order number
		 */
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getOrderDate() {
		/**
		 * @return Order date
		 */
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getOrderValue() {
		return orderValue;
	}


	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}


	public String getOrderTotalItems() {
		/**
		 * @return Order total items
		 */
		return orderTotalItems;
	}


	public void setOrderTotalItems(String orderTotalItems) {
		this.orderTotalItems = orderTotalItems;
	}


	public String getProdId() {
		/**
		 * @return Product ID
		 */
		return prodId;
	}


	public void setProdId(String prodId) {
		this.prodId = prodId;
	}


	public String getProdName() {
		/**
		 * @return Product Name
		 */
		return prodName;
	}


	public void setProdName(String prodName) {
		this.prodName = prodName;
	}


	public String getProdQuantity() {
		/**
		 * @return Product quantity ordered
		 */
		return prodQuantity;
	}


	public void setProdQuantity(String prodQuantity) {
		this.prodQuantity = prodQuantity;
	}


	public String getProdValue() {
		/**
		 * @return Product value listed
		 */
		return prodValue;
	}


	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}


	public String getCustomerId() {
		/**
		 * @return Customer number
		 */
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		/**
		 * @return Customer name
		 */
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	@Override
	public String toString() {
		return "order [id=" + orderId + ", prodId=" + prodId+"]";
	}

}
