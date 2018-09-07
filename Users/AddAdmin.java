package Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.cdb;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddAdmin {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null;  
	@FXML
	private TextField user_idfield;
	@FXML
	private TextField namefield;
	@FXML
	private TextField positionfield;
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

	private Stage adddialog;
	public boolean okClicked = false;


	public Stage getAdddialog() {
		return adddialog;
	}

	public void setAdddialog(Stage adddialog) {
		this.adddialog = adddialog;
	}

	public boolean isOkClicked(){
		return okClicked;
	}


	@FXML
	public void ok(){
		String post;
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
		try {
			con= cdb.sqlcon(); 
			stmt = con.createStatement();
			String insert = "INSERT INTO `users`(`user_id`,`name`,`position`, `phone`,`shop`, `address`)"
					+" VALUES('"+user_idfield.getText()+"','"+namefield.getText()+"','"+post+"','"+phonefield.getText()+"','"+shopfield.getText()+"','"+addressfield.getText()+"')";
			stmt.executeUpdate(insert);

		}catch(SQLException s){
			System.err.println("problem in:"+s.getSQLState());
			System.err.println("problem in:"+s.getMessage());
		}
		okClicked = true;
		adddialog.close();
	}
	@FXML
	public void Cancel(){

		adddialog.close();
	}


}
