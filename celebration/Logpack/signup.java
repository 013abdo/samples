package celebration.Logpack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.cdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class signup {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  
	
	@FXML Label check;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private PasswordField confirm;

	
	@FXML
	public void sign(){
		if(password.getText().equals(confirm.getText())){
			try {
				con= cdb.sqlcon(); 
				stmt = con.createStatement();
				String insert = "INSERT INTO `log`(`username`,`pass`)"
						+" VALUES('"+username.getText()+"','"+password.getText()+"')";
				stmt.executeUpdate(insert);

			}catch(SQLException s){
				System.err.println("problem in:"+s.getSQLState());
				System.err.println("problem in:"+s.getMessage());
			}
		}else{
			System.out.println("Sorry but pasword do not match try again!");
		}
	
	}
	@FXML
	public void login(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/login.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@FXML
	public void Cancel(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/login.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
