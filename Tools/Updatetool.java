package Tools;

import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Updatetool implements Initializable {
	Connection con=null;
	Statement stmt = null;
	ResultSet rs= null; 
	
	private Stage editdialog;
	private boolean okClicked = false;
	private tools tool;
	
@FXML	private TextField tool_numfield;
@FXML	private TextField tool_namefield; 
//@FXML	private TextField categoryfield;
@FXML	private TextField costfield; 
@FXML	private TextField quantityfield; 
//@FXML	private TextField shopfield; 
@FXML 	private TextField addressfield;
@FXML   private ComboBox<String> box;
@FXML private ChoiceBox<String>mark;

public Stage getEditdialog() {
	return editdialog;
}
public void setEditdialog(Stage editdialog) {
	this.editdialog = editdialog;
}
public boolean isOkClicked() {
	return okClicked;
}
public void setOkClicked(boolean okClicked) {
	this.okClicked = okClicked;
}
public tools getTool() {
	return tool;
}
public void setTool(tools tool) {
	this.tool = tool;
	tool_numfield.setText(Integer.toString(tool.getTool_num()));
	tool_namefield.setText(tool.getTool_name());
	costfield.setText(Integer.toString(tool.getCost()));
	quantityfield.setText(Integer.toString(tool.getQuantity()));
	addressfield.setText(tool.getAddress());
}
@Override
public void initialize(URL location, ResourceBundle resources) {
	combo();
	choice();
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
public void choice(){
	try(
			Connection 	con = cdb.sqlcon(); 
			Statement 	stmt =con.createStatement(); 
			ResultSet 	rs = stmt.executeQuery("select shop_name  from shop") ;
			){
		while(rs.next()){
			mark.getItems().add(rs.getString("shop_name"));
		//	box.getValue().toString();
		}
	}catch(SQLException e){
		System.err.println("error "+e.getSQLState());
}
}
@FXML
private void ok(){
	try{
		con = cdb.sqlcon();
		stmt =  con.createStatement();
		String update ="UPDATE `tools` SET `tool_name`='"+tool_namefield.getText()+"',`category`='"+box.getValue().toString()+"',`quantity`='"+quantityfield.getText()+
				"',`cost`='"+costfield.getText()+"',`shop`='"+mark.getValue().toString()+"', `address`='"+addressfield.getText()+"' WHERE `tool_num` = '"+tool_numfield.getText()+"'";
				stmt.executeUpdate(update);
	}catch(SQLException e){
		System.err.println("problem in:"+e.getSQLState());
		System.err.println("problem in:"+e.getMessage());
	}
	
	okClicked = true;
	editdialog.close();
}
@FXML
private void cancel(){ 
	editdialog.close();
}

}
