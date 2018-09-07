package Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.cdb;
import celebration.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UpdateAdmin {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  
	//@FXML private ComboBox idcombo;
	@FXML
	private TextField user_idfield;
	@FXML
	private TextField namefield;
//	@FXML
	//private TextField positionfield;
	@FXML
	private TextField phonefield;
	@FXML
	private TextField shopfield;
	@FXML
	private TextField addressfield;
	@FXML private RadioButton Emp;
	@FXML private RadioButton Adm;
	@FXML private RadioButton wor;
	@FXML private RadioButton deli;

	private Stage shopdialog;
	private User user;
	public boolean okClicked = false;


	@FXML
	public void initialize(){

	}

	public void setUser(User user){
		this.user = user;

		user_idfield.setText(Integer.toString(user.getUser_id()));
		namefield.setText(user.getName());
	//	positionfield.setText(user.getPosition());
		phonefield.setText(Integer.toString(user.getPhone()));
		shopfield.setText(user.getShop());
		addressfield.setText(user.getAddress());
	}
	public Stage getShopdialog() {
		return shopdialog;
	}

	public void setShopdialog(Stage shopdialog) {
		this.shopdialog = shopdialog;
	}
	
	public boolean isOkClicked(){
		return okClicked;
	}

	public void ok(){
		String post;
		if(isValidInput()){
			user.setUser_id(Integer.parseInt(user_idfield.getText()));
			user.setName(namefield.getText());
			if(this.Adm.isSelected()){
				post = "Admin";
				System.out.println("adm");
			}else if(this.deli.isSelected()){
				post = "Delievery";
				System.out.println("deli");
			}else if(this.wor.isSelected()){
				post = "Worker";
				System.out.println("wor");
			}else{
				post = "Emp";
				System.out.println("emp");
			}
			user.setPhone(Integer.parseInt(phonefield.getText()));
			user.setShop(shopfield.getText());
			user.setAddress(addressfield.getText());
		try {
			con= cdb.sqlcon(); 
			stmt = con.createStatement();

			String update ="UPDATE `users` SET `name`='"+namefield.getText()
			+"',`position`='"+post+"',`phone`='"+phonefield.getText()+"',`shop`='"+shopfield.getText()+"',`address`='"+addressfield.getText()+ "' WHERE `user_id`='"+user_idfield.getText()+"'";
			stmt.executeUpdate(update);
		
		}catch(SQLException s){
			System.err.println("problem in:"+s.getSQLState());
			System.err.println("problem in:"+s.getMessage());
		}
		}
		/*try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/DataAdmin.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		okClicked = true;
		shopdialog.close();
		}

	public void Cancel(){
		/*try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/Users.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
			e.printStackTrace();
		}*/
		shopdialog.close();
	}

	public boolean isValidInput(){
		String msg ="";
		if(user_idfield.getText()==null || user_idfield.getText().length()==0 ){
			msg ="Not Number Again please!!";
		}else{
			try{
				Integer.parseInt(user_idfield.getText());
			}
			catch(NumberFormatException num){
				System.out.println("error"+num);
			}
		}
		if(namefield.getText()==null || namefield.getText().length()==0 ){
			msg =" Again please!!";
		}
		/*if(positionfield.getText()==null || positionfield.getText().length()==0 ){
			msg =" Again please!!";
		}*/
		if(phonefield.getText()==null || phonefield.getText().length()==0 ){
			msg ="Not Number Again please!!";
		}else{
			try{
				Integer.parseInt(phonefield.getText());
			}
			catch(NumberFormatException num){
				System.out.println("error"+num);
			}
		}
		
		if(addressfield.getText()==null || addressfield.getText().length()==0 ){
			msg =" Again please!!";
		}
		if(shopfield.getText()==null || shopfield.getText().length()==0 ){
			msg =" Again please!!";
		}
		
		 if(msg.length()==0){
         return true;
		 }else{
		return false;
		}
	}
}
