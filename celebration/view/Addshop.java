package celebration.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Exceptions;
import application.Main;
import application.cdb;
import celebration.model.Shop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Addshop {
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
	 private Stage newshop;
	public boolean okClicked = false;

	public Stage getNewshop() {
		return newshop;
	}

	public void setNewshop(Stage newshop) {
		this.newshop = newshop;
	}

	public boolean isOkClicked(){
		return okClicked;
	}

	@FXML
	public void ok(){
		try {
			con= cdb.sqlcon(); 
			stmt = con.createStatement();
			String insert = "INSERT INTO `shop`(`shop_num`, `shop_name`, `shop_tel`, `shop_phone`, `shop_address`, `shop_city`, `shop_owner`)"
					+" VALUES('"+shop_numfield.getText()+"','"+shop_namefield.getText()+"','"+shop_telfield.getText()+"','"+shop_phonefield.getText()+"','"+shop_addressfield.getText()+"','"+shop_cityfield.getText()+"','"+shop_ownerfield.getText()+"')";
			stmt.executeUpdate(insert);
			Alert al = new Alert(AlertType.INFORMATION);
			al.setContentText("the shop is inserted!!");
			al.show();
/*			try{
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
newshop.close();
		}catch(SQLException s){
			Exceptions.SQLExceptions(s); 
		}
	}

	@FXML
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
			Exceptions.Exception(e);
		}*/
		newshop.close();
	}
}
