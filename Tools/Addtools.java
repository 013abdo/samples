package Tools;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.cdb;
import celebration.model.Categories;
import celebration.model.Shop;
import celebration.model.tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Addtools implements Initializable{
	Connection con=null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs= null; 
	
	private Stage tooldialog;
	

	@FXML private TextField tool_numfield;
	@FXML private TextField tool_namefield;
	//@FXML private TextField categoryfield;
	@FXML private TextField costfield;
	@FXML private TextField quantityfield;
	//@FXML private TextField shopfield;
	@FXML private TextField addressfield;

	@FXML
	private ComboBox<String> box;
	ObservableList<Categories> detail = FXCollections.observableArrayList();
	
@FXML 
private ChoiceBox<String>mark;
ObservableList<Shop> market = FXCollections.observableArrayList();


	public boolean okClicked = false;

	public boolean isOkClicked(){
		return okClicked;
	}
	public Stage getTooldialog() {
		return tooldialog;
	}
	public void setTooldialog(Stage tooldialog) {
		this.tooldialog = tooldialog;
	}

	@FXML
	public void combo(){
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("select cate_name  from category") ;
				){
			while(rs.next()){
				box.getItems().add(rs.getString("cate_name"));
				//	box.getValue().toString();
			}
		}catch(SQLException e){
			System.err.println("error "+e.getSQLState());
		}
	}
	@FXML
	public void choose(){
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("select shop_name  from shop") ;
				){
			while(rs.next()){
				mark.getItems().add(rs.getString("shop_name"));
			}
		}catch(SQLException e){
			System.err.println("error "+e.getSQLState());
		}
	}
	@FXML
	public void ok(){
		try {
			con= cdb.sqlcon(); 
			stmt = con.createStatement();

			String insert = "INSERT INTO `tools`(`tool_num`, `tool_name`,`category`, `quantity`, `cost`, `address`, `shop`)"
					+" VALUES('"+tool_numfield.getText()+"','"+tool_namefield.getText()+"','"
					+box.getValue().toString()+"','"+costfield.getText()+"','"+quantityfield.getText()+"','"+mark.getValue().toString()+"','"+addressfield.getText()+"')";

			stmt.executeUpdate(insert);
			okClicked = true;
			tooldialog.close();
		}catch(SQLException s){
			System.err.println("problem in:"+s.getSQLState());
			System.err.println("problem in:"+s.getMessage());
		}
	}

	@FXML
	private void cancel(){

		tooldialog.close();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combo();
choose();	}
}
