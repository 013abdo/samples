package billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.util.ResourceBundle;

import application.Main;
import application.cdb;
import celebration.model.Categories;
import celebration.model.bills;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addbill implements Initializable{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
private Stage preorder;
public boolean okClicked = false;

	public boolean isOkClicked() {
	return okClicked;
}
public void setOkClicked(boolean okClicked) {
	this.okClicked = okClicked;
}
	//bill details fields
	@FXML	private TextField bill_numfild;
	//@FXML	private TextField shopfild;
	@FXML	private TextField bill_datefild;
	@FXML	private TextField customer_namefild;
	@FXML	private TextField customer_phonefild;
	@FXML	private TextField tool_numfild;
	//@FXML	private TextField tool_namelbl;
	//@FXML	private TextField cate_namelbl;
	@FXML	private TextField quantityfild;
	@FXML	private TextField costfild;
	@FXML	private TextField deliveryfild;

	@FXML
	private ComboBox<String> box;
	@FXML 
	private ChoiceBox<String> toollist;
	@FXML 
	private ChoiceBox<String> markets;
	
	@FXML private Spinner<Integer> spin;
	 int initialValue = 0;
	SpinnerValueFactory<Integer> value = new  SpinnerValueFactory.IntegerSpinnerValueFactory(1,100, initialValue);
	
	@FXML private DatePicker date;
	public LocalDate now;
	public Stage getPreorder() {
		return preorder;
	}
	public void setPreorder(Stage preorder) {
		this.preorder = preorder;
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
			}
		}catch(SQLException e){
			System.err.println("error "+e.getSQLState());
			System.err.println("error "+e.getMessage());
		}
	}
	@FXML
	public void choice(){
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("select tool_name from tools") ;
				){
			while(rs.next()){
				toollist.getItems().add(rs.getString("tool_name"));
			}
		}catch(Exception c){
			System.err.println("Error"+c.getMessage());
		}
	}
	@FXML
	public void shops(){
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("select shop_name from shop") ;
				){
			while(rs.next()){
				markets.getItems().add(rs.getString("shop_name"));
			}
		}catch(Exception c){
			System.err.println("Error"+c.getMessage());
		}
	}
	@FXML
	public void spinval(){
		spin.setValueFactory(value);
	}
	@FXML public void calender(){
		date.setChronology(HijrahChronology.INSTANCE);
		// LocalDate now = date.getValue().now();
	}
	
	public void culc(){
	int quant ;
	int cost;
	int total;
	
	quant = spin.getValue();
	cost = Integer.parseInt(costfild.getText());
	total = quant * cost;
	
	deliveryfild.setText(Integer.toString(total));
		
	}
	
	
	@FXML 
	public void New(){
		try{
			con = cdb.sqlcon();
			stmt = con.createStatement();

			String insert = "INSERT INTO `bills`(`bill_num`,  `bill_date`, `shop`,"
					+ " `customer_name`, `customer_phone`,"
					+ " `tool_num`, `tool_name`, `cate_name`,"
					+ "`quantity`, `cost`, `delivery`)" 
					+"VALUES ('"+bill_numfild.getText()+"','"+/*bill_datefild.getText()*/date.getValue().toString()+"','"+markets.getValue().toString()+
					"','"+customer_namefild.getText()+"','"+customer_phonefild.getText()+
					"','"+tool_numfild.getText()+"','"+toollist.getValue().toString()+"','"+box.getValue().toString()+
					"','"+/*quantityfild.getText()*/spin.getValue().toString()+"','"+costfild.getText()+"','"+deliveryfild.getText()+"')";
			stmt.executeUpdate(insert);
		/*	try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass()
						.getResource("/celebration/view/bills.fxml"));
				AnchorPane anchorpane = loader.load();
				Scene scene = new Scene(anchorpane);
				Main.shop.setScene(scene);
				Main.shop.show();
			}catch(Exception e){
				System.err.println("Error: "+ e.getMessage());
			}*/
			okClicked = false;
			preorder.close();
		} catch(SQLException e){
			System.err.println("error "+e.getSQLState());
			System.err.println("error "+e.getMessage());
		}

	}
	@FXML
	public void Cancel(){
	/*	try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/bills.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
			System.err.println("Error: "+ e.getMessage());
		}*/
		preorder.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		shops();choice();combo();spinval();calender();
	//	culc();
	}
}
