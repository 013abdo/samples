package celebration.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.cdb;
import celebration.model.Shop;
import celebration.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UpdateShop {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  
	@FXML
	private TextField  shop_numfield;
	@FXML
	private TextField  shop_namefield;
	@FXML
	private TextField  shop_phonefield;
	@FXML
	private TextField  shop_cityfield;
	@FXML
	private TextField  shop_telfield;
	@FXML 
	private TextField shop_addressfield;
	@FXML 
	private TextField  shop_ownerfield;
	
	private Shop shop;
	private Stage building;
	public boolean okClicked = false;
	
	public Stage getBuilding() {
		return building;
	}

	public void setBuilding(Stage building) {
		this.building = building;
	}

	public boolean isOkClicked(){
		return okClicked;
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
		shop_numfield.setText(Integer.toString(shop.getShop_num()));
		shop_namefield.setText(shop.getShop_name());
		shop_telfield.setText(Integer.toString(shop.getShop_num()));
		shop_phonefield.setText(Integer.toString(shop.getShop_num()));
		shop_addressfield.setText(shop.getShop_address());
		shop_cityfield.setText(shop.getShop_city());
		shop_ownerfield.setText(shop.getShop_owner());
	}

	public void ok(){
		try {
			con= cdb.sqlcon(); 
			stmt = con.createStatement();
			String update ="UPDATE `shop` SET `shop_name`='"+shop_namefield.getText()+"',`shop_tel`='"+shop_telfield.getText()+"',`shop_phone`='"+shop_phonefield.getText()+"',`shop_address`='"+shop_addressfield.getText()+ "',`shop_city`='"+shop_cityfield.getText()+"',`shop_owner`='"+shop_ownerfield.getText()+"' WHERE `shop_num`='"+shop_numfield.getText()+"'";
			stmt.executeUpdate(update);
			Alert al = new Alert(AlertType.INFORMATION);
			al.setContentText("the selected emp is updated!!");
			al.show();
		}catch(SQLException s){
			System.err.println("problem in:"+s.getSQLState());
			System.err.println("problem in:"+s.getMessage());
		}
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/shop.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		okClicked = true;
		building.close();
	}
	
	public void Cancel(){
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/shop.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		building.close();
	}

}
