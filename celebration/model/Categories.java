package celebration.model;

public class Categories {
private Integer cate_id;
private String cate_name;
private Integer quantity;


/*public Categories( String cate_name){
	this.cate_id = cate_id;
	this.cate_name = cate_name;
	this.quantity = quantity;
}*/
public Categories( Integer cate_id, String cate_name, Integer quantity){
	this.cate_id = cate_id;
	this.cate_name = cate_name;
	this.quantity = quantity;
}
public Integer getCate_id() {
	return cate_id;
}
public void setCate_id(Integer cate_id) {
	this.cate_id = cate_id;
}
public String getCate_name() {
	return cate_name;
}
public void setCate_name(String cate_name) {
	this.cate_name = cate_name;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}

}
