package celebration.model;

public class Shop {
	private int shop_num;
	private	String shop_name;
	private	int shop_tel;
	private	int shop_phone;
	private	String shop_city;
	private	String shop_address;
	private	String shop_owner;
	
	public Shop(){}
	
	public Shop(int shop_num,String shop_name,int shop_tel,	int shop_phone,String shop_city,String shop_address ,String shop_owner){
		this.shop_num = shop_num;
		this.shop_name = shop_name;
		this.shop_tel =shop_tel;
		this.shop_phone = shop_phone;
		this.shop_address = shop_address;
		this.shop_city = shop_city;
		this.shop_owner = shop_owner;
	}
	public Shop(int shop_num,String shop_name,int shop_tel,String shop_city,String shop_address,String shop_owner){
		this.shop_num = shop_num;
		this.shop_name = shop_name;
		this.shop_tel =shop_tel;
		this.shop_address = shop_address;
		this.shop_city = shop_city;
		this.shop_owner = shop_owner;
	
	}
	public Shop(String shop_name,int shop_tel){
		this.shop_name = shop_name;
		this.shop_tel =shop_tel;
	}
	
	public int getShop_num() {
		return shop_num;
	}
	public String getShop_name() {
		return shop_name;
	}
	public int getShop_tel() {
		return shop_tel;
	}
	public int getShop_phone() {
		return shop_phone;
	}
	public String getShop_city() {
		return shop_city;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_num(int shop_num) {
		this.shop_num = shop_num;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public void setShop_tel(int shop_tel) {
		this.shop_tel = shop_tel;
	}
	public void setShop_phone(int shop_phone) {
		this.shop_phone = shop_phone;
	}
	public void setShop_city(String shop_city) {
		this.shop_city = shop_city;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getShop_owner() {
		return shop_owner;
	}

	public void setShop_owner(String shop_owner) {
		this.shop_owner = shop_owner;
	}
}
