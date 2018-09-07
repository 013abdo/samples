package celebration.Logpack;

import java.sql.*;
import application.Main;
import application.cdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class login{
Connection con;

@FXML Label check;
@FXML
private TextField username;
@FXML
private TextField password;

@FXML
public void login(){
	logged();
}
public boolean logged(){
	try (Connection 	con = cdb.sqlcon(); 
			Statement 	stmt =con.createStatement(); 
			ResultSet 	rs = stmt.executeQuery("select * from log where username= '"+username.getText()+"' and pass = '"+password.getText()+"'") ;
			){
		if(rs.next()){
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass()
						.getResource("/celebration/view/homepage.fxml"));
				AnchorPane anchorpane = loader.load();
				Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
			return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			check.setText("Notice: Sorry username or the password is incorrect. \n ^^ signup and try Again please!");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;	
}
@FXML
public void sign(){
	try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/celebration/view/addpage.fxml"));
		AnchorPane anchorpane = loader.load();
		Scene scene = new Scene(anchorpane);
	Main.shop.setScene(scene);
	Main.shop.show();
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
