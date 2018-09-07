package Users;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.cdb;
import celebration.model.User;
import celebration.model.bills;
import core.StaticObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class customer implements Initializable {

	@FXML	private TableView<bills> bill;
	@FXML	private TableColumn<bills, Integer> tool_numCol; 
	@FXML	private TableColumn<bills, String> tool_nameCol;
	@FXML	private TableColumn<bills, String> categoryCol;
	@FXML	private TableColumn<bills, Integer> quantityCol;

	@FXML private	Label bill_numlbl;
	@FXML private	TextField shoplbl;
	@FXML private	Label bill_datelbl;
	//@FXML private	TextField customer_namelbl;
	@FXML private	Label customer_phonelbl;
	@FXML private TextField user_idlbl;
	@FXML private TextField namelbl;
	@FXML private TextField addressfild;
	@FXML private TextField phonelbl;
	@FXML private TextField costfild;
	@FXML private ChoiceBox<String> customers;


	ObservableList<bills> details = FXCollections.observableArrayList();
ObservableList<String> names = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// show the details on the scene
		tool_numCol.setCellValueFactory(new PropertyValueFactory<bills, Integer>("tool_num"));
		tool_nameCol.setCellValueFactory(new PropertyValueFactory<bills, String>("tool_name"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<bills, String>("cate_name"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<bills, Integer>("quantity"));
		choosecustomer();
		
	}
	public void setdata(){
		getempdata();
		setbill();
		loaddatabases();
	}
	
	// present the customer's data via customer name
	public void choosecustomer(){
		try (Connection con = cdb.sqlcon();
				Statement stmt = con.createStatement();
				ResultSet st = stmt.executeQuery("select customer_name from bills");)
		{
			while(st.next()){
				customers.getItems().add( st.getString("customer_name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: "+ e.getMessage());
		}
	}
	//method to load the data from the database
	public void loaddatabases(){

		try (Connection con = cdb.sqlcon();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from bills where customer_name = '"+customers.getValue().toString()+"'"); 
				){
			while(rs.next()){
				bill.getItems().add(new bills(rs.getInt("bill_num"),rs.getString("shop"),rs.getString("bill_date"),
						rs.getInt("tool_num"), rs.getString("tool_name"),rs.getString("cate_name")
						,rs.getInt("quantity"), rs.getInt("cost"),rs.getString("delivery")));
				bill.setItems(details);
			}
		} catch (SQLException e) {
			System.err.println("Error: "+ e.getMessage());
		} 
	}

public void setbill(){
	try (Connection con = cdb.sqlcon();
			Statement stmt = con.createStatement();
			ResultSet st = stmt.executeQuery("select * from bills where customer_name = '"+customers.getValue()+"' ");
			){
		st.next();
		bill_numlbl.setText(Integer.toString(st.getInt("bill_num")));
		shoplbl.setText(st.getString("shop"));
		bill_datelbl.setText(st.getString("bill_date"));
		customer_phonelbl.setText(Integer.toString(st.getInt("customer_phone")));
	}catch(Exception s){
		System.out.println(s);
	}

}
	
public void getempdata(){
	try (Connection con = cdb.sqlcon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select user_id, name, phone  from users where shop= '"+shoplbl.getText()+"'");
			){
		while(rs.next()){
			 user_idlbl.setText(Integer.toString(rs.getInt("user_id")));
			 namelbl.setText(rs.getString("name"));
				 phonelbl.setText(Integer.toString(rs.getInt("phone")));
		}
	}catch(Exception s){
		System.out.println(s);
	}
}
	@FXML
	private void back(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/homepage.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
			System.err.println("Error: "+ e.getMessage());
		}
	}
}

