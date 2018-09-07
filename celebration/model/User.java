package celebration.model;



public class User {
private Integer user_id;
private String name;
private String position;
private Integer phone;
private String address;
private String shop;

/* the default constructor */
/*public User(){
	this(null,null,null);
}

 public User(Integer user_id, String name, Integer phone){
	 this.user_id = new SimpleIntegerProperty(user_id);
	 this.name = new SimpleStringProperty(name);
	 this.phone = new SimpleIntegerProperty(phone);
	 this.address = new SimpleStringProperty("address");
	 this.shop = new SimpleStringProperty("Adnan-shop");
 }*/
 public User(Integer user_id, String name,String position, Integer phone,String shop, String address ){
	 this.user_id = user_id;
	 this.name = name;
	 this.position=position;
	 this.phone =phone;
	 this.address = address;
	this.shop = shop;
 }

 public int getUser_id(){
		return user_id;
		}

 public void setUser_id(int user_id){
		this.user_id=user_id;
		}
 public String getName(){
	return name;
	}
 public void setName(String name){
		this. name=name;
		}
 public String getPosition(){
		return position;
		}
	 public void setPosition(String position){
			this. position=position;
			}
 public int getPhone(){
	return phone;
	}
 public void setPhone(int phone){
		this. phone=phone;
		}
 public String getAddress(){
	return address;
	}
 public void setAddress(String address){
		this. address=address;
		}

public String getShop() {
	return shop;
}

public void setShop(String shop) {
	this.shop = shop;
}
 
}
