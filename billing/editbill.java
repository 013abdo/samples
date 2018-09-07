package billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.chrono.HijrahChronology;
import java.util.ResourceBundle;

import application.cdb;
import celebration.model.Categories;
import celebration.model.bills;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class editbill implements Initializable {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	private Stage outorder;
	public boolean okClicked = false;
	private bills order;
		public bills getOrder() {
		return order;
	}
	
		public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
		//bill details fields
		@FXML	private TextField bill_numfild;
	//	@FXML	private TextField shopfild;
		@FXML	private TextField bill_datefild;
		@FXML	private TextField customer_namefild;
		@FXML	private TextField customer_phonefild;
		@FXML	private TextField tool_numfild;
		@FXML	private TextField quantityfild;
		@FXML	private TextField costfild;
		@FXML	private TextField deliveryfild;

		@FXML
		private ComboBox<String> box;
		@FXML 
		private ChoiceBox<String> toollist;
		@FXML 
		private ChoiceBox<String> marketlist;
		
		@FXML private Spinner<Integer> spin;
		 int initialValue = 0;
		SpinnerValueFactory<Integer> value = new  SpinnerValueFactory.IntegerSpinnerValueFactory(1,100, initialValue);
		@FXML private DatePicker date;
		
	public Stage getOutorder() {
			return outorder;
		}
		public void setOutorder(Stage outorder) {
			this.outorder = outorder;
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
		public void shops(){
			try(
					Connection 	con = cdb.sqlcon(); 
					Statement 	stmt =con.createStatement(); 
					ResultSet 	rs = stmt.executeQuery("select shop_name from shop") ;
					){
				while(rs.next()){
					marketlist.getItems().add(rs.getString("shop_name"));
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
	@FXML	public void Edit(){
		try{	con = cdb.sqlcon();
		stmt = con.createStatement();
		String update = "	UPDATE `bills` SET `bill_date`='"+/*bill_datefild.getText()*/date.getValue().toString()+"',`shop`='"+marketlist.getValue().toString()+
				"',`customer_name`='"+customer_namefild.getText()+"',`customer_phone`='"+customer_phonefild.getText()+
				"',`tool_num`='"+tool_numfild.getText()+"',`tool_name`='"+toollist.getValue().toString()+"',`cate_name`='"+box.getValue().toString()+
				"',`quantity`='"+/*quantityfild.getText()*/spin.getValue().toString()+"',`cost`='"+costfild.getText()+"',`delivery`='"+deliveryfild.getText()+
				"' WHERE `bill_num`='"+bill_numfild.getText()+"'";
		stmt.executeUpdate(update);
		Alert al = new Alert(AlertType.INFORMATION);
		al.setContentText("this bill is updated!!");
		al.show();

		}catch(Exception e){
System.out.println(e.getMessage());
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
		outorder.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		shops();choice();combo();spinval();calender();
	}
	public void setOrder(bills order) {
		this.order = order;
		bill_numfild.setText(Integer.toString(order.getBill_num()));
	//	bill_datefild.setText(Integer.toString(order.getBill_date()));
		customer_namefild.setText(order.getCustomer_name());
		customer_phonefild.setText(Integer.toString(order.getCustomer_phone()));
		tool_numfild.setText(Integer.toString(order.getTool_num()));
	//	quantityfild.setText(Integer.toString(order.getQuantity()));
		costfild.setText(Integer.toString(order.getCost()));
		deliveryfild.setText(order.getDelivery());
	}

}
