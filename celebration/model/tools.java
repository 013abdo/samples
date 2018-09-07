package celebration.model;

public class tools {
	private Integer tool_num;
	private String tool_name; 
	private String category;
	private Integer cost; 
	private Integer quantity; 
	private String shop; 
	private String address;

	public tools(Integer tool_num, String tool_name,String category,
			Integer cost, int quantity, String shop, String address){
		this.tool_num =( tool_num);
		this.tool_name= (tool_name); 
		this.category =(category);
		this.cost = (cost); 
		this.quantity = (quantity); 
		this.shop =  (shop); 
		this.address = (address);
	}

	public int getTool_num(){
		return tool_num;
	}
	public void setTool_num(int tool_num){
		this.tool_num=(tool_num);
	}

	public String getTool_name(){
		return tool_name;
	}
	public void setTool_name(String tool_name){
		this.tool_name=(tool_name);
	}

	public String getCategory(){
		return category;
	}
	public void setCategory(String category){
		this.category=(category);
	}

	public int getCost(){
		return cost;
	}
	public void setCost(int cost){
		this.cost=(cost);
	}

	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int  quantity){
		this.quantity=(quantity);
	}

	public String getShop(){
		return shop;
	}
	public void setShop(String shop){
		this.shop=(shop);
	}

	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address=(address);
	}


}
