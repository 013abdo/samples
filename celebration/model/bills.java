package celebration.model;

public class bills {
	private	int bill_num;
	private	String shop;
	private	String bill_date;
	private	String customer_name;
	private	int customer_phone;
	private	int tool_num;
	private	String tool_name;
	private	String cate_name;
	private	int quantity;
	private	int cost;
	private	String delivery;
	
	public  bills(	int bill_num, String shop, String bill_date,
	String customer_name, int customer_phone,
		int tool_num, String tool_name, String cate_name, 
		int quantity, int cost, String delivery){
	this.bill_num = bill_num;
		this. shop = shop;
		this. bill_date = bill_date;
		this.customer_name= customer_name;
			this.customer_phone= customer_phone;
			this.tool_num= tool_num;
			this.tool_name= tool_name;
			this.cate_name= cate_name;
			this.quantity= quantity;
			this.cost =cost;
			this.delivery= delivery;
	}
	
	
	public  bills(	int bill_num, String shop, String bill_date,
				int tool_num, String tool_name, String cate_name, 
				int quantity, int cost, String delivery){
			this.bill_num = bill_num;
				this. shop = shop;
				this. bill_date = bill_date;
					this.tool_num= tool_num;
					this.tool_name= tool_name;
					this.cate_name= cate_name;
					this.quantity= quantity;
					this.cost =cost;
					this.delivery= delivery;
			}
			
	public  bills(	
				int tool_num, String tool_name, String cate_name, 
				int quantity, int cost, String delivery){
					this.tool_num= tool_num;
					this.tool_name= tool_name;
					this.cate_name= cate_name;
					this.quantity= quantity;
					this.cost =cost;
					this.delivery= delivery;
			}
	
	public  bills(	
	String customer_name, int customer_phone,
		int tool_num, String tool_name, String cate_name, 
		int quantity, int cost, String delivery){
		this.customer_name= customer_name;
			this.customer_phone= customer_phone;
			this.tool_num= tool_num;
			this.tool_name= tool_name;
			this.cate_name= cate_name;
			this.quantity= quantity;
			this.cost =cost;
			this.delivery= delivery;
	}
	
	public  bills(	String customer_name, int customer_phone){
		this.customer_name= customer_name;
			this.customer_phone= customer_phone;
			}
	
	public  bills(	String customer_name){
		this.customer_name= customer_name;
			}

public int getBill_num() {
		return bill_num;
	}
	public void setBill_num(int bill_num) {
		this.bill_num = bill_num;
	}
	public String getShop() {
		return shop;
	}
	public void setshop(String shop) {
		this.shop = shop;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(int customer_phone) {
		this.customer_phone = customer_phone;
	}
	public int getTool_num() {
		return tool_num;
	}
	public void setTool_num(int tool_num) {
		this.tool_num = tool_num;
	}
	public String getTool_name() {
		return tool_name;
	}
	public void setTool_name(String tool_name) {
		this.tool_name = tool_name;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}


}
