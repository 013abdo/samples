package billing;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Exceptions;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrderList implements Initializable {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private Stage inOrder;
	private Stage onOrder;
	private boolean onClicked = false;
	public Stage getInOrder() {
		return inOrder;
	}
	public Stage getOnOrder() {
		return onOrder;
	}
	public boolean isOnClicked() {
		return onClicked;
	}
	public void setInOrder(Stage inOrder) {
		this.inOrder = inOrder;
	}
	public void setOnOrder(Stage onOrder) {
		this.onOrder = onOrder;
	}
	public void setOnClicked(boolean onClicked) {
		this.onClicked = onClicked;
	}
	@FXML	private TableView<bills> ordertable;
	@FXML	private TableColumn<bills,Integer> bill_numCol;
	@FXML	private TableColumn<bills, String> shopCol;
	@FXML	private TableColumn<bills,String> bill_dateCol;
	@FXML	private TableColumn<bills,Integer> tool_numCol;
	@FXML	private TableColumn<bills,String> tool_nameCol;
	@FXML	private TableColumn<bills,String> cate_nameCol;
	@FXML	private TableColumn<bills,Integer> quantityCol;
	//	@FXML	private TableColumn<bills,Integer> durationCol;
	@FXML	private TableColumn<bills,Integer> costCol;
	@FXML	private TableColumn<bills,Integer> deliveryCol;

	@FXML	private TextField deletefild;
	@FXML	private TextField sortfild;

	@FXML	private TextField bill_numfild;
	@FXML	private TextField shopfild;
	@FXML	private TextField bill_datefild;
	@FXML	private TextField customer_namefild;
	@FXML	private TextField customer_phonefild;
	@FXML	private TextField tool_numfild;
	@FXML	private TextField tool_namelbl;
	@FXML	private TextField cate_namelbl;
	@FXML	private TextField quantityfild;
	@FXML	private TextField costfild;
	@FXML	private TextField deliveryfild;
	
	ObservableList<Categories> detail = FXCollections.observableArrayList();

	//load the data into table view
	ObservableList<bills> orderlist = FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadbilldatafromdatabase();
		bill_numCol.setCellValueFactory(new PropertyValueFactory<bills,Integer>("bill_num"));
		shopCol.setCellValueFactory(new PropertyValueFactory<bills,String>("shop"));
		bill_dateCol.setCellValueFactory(new PropertyValueFactory<bills,String>("bill_date"));
		tool_numCol.setCellValueFactory(new PropertyValueFactory<bills,Integer>("tool_num"));
		tool_nameCol.setCellValueFactory(new PropertyValueFactory<bills,String>("tool_name"));
		cate_nameCol.setCellValueFactory(new PropertyValueFactory<bills,String>("cate_name"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<bills,Integer>("quantity"));
		costCol.setCellValueFactory(new PropertyValueFactory<bills,Integer>("cost"));
		deliveryCol.setCellValueFactory(new PropertyValueFactory<bills,Integer>("delivery"));

	}
	public void loadbilldatafromdatabase(){
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("select * from bills") ;
				){
			while(rs.next()){
				ordertable.getItems().add(new bills(rs.getInt("bill_num"),rs.getString("shop"),rs.getString("bill_date"),
						rs.getInt("tool_num"), rs.getString("tool_name"),rs.getString("cate_name")
						, rs.getInt("quantity"), rs.getInt("cost"),rs.getString("delivery")));
				ordertable.setItems(orderlist);
			}
		}catch(SQLException s){
			System.err.println("note:"+s.getMessage());
		}
	}


	@FXML
	private void newbill(){
		buy();
	}
public boolean buy(){
	try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/celebration/view/addbill.fxml"));
		AnchorPane anchorpane = loader.load();
		Stage onserv = new Stage();
		onserv.setTitle("InOrder Bill !!!");
		onserv.initModality(Modality.WINDOW_MODAL);
		onserv.initOwner(inOrder);
		Scene scene = new Scene(anchorpane);
		onserv.setScene(scene);
		onserv.show();
		addbill in =loader.getController();
		in.setPreorder(onserv);
		return in.isOkClicked();
	}catch(Exception e){
		System.err.println("Error: "+ e.getMessage());
	}
	return onClicked;
	
}
	@FXML
	private void editbill(){
	
		bills order = ordertable.getSelectionModel().getSelectedItem();
	if(order != null){
		boolean okClicked = replace(order);
		if(okClicked){
			billfilds(order);
		}
	}
	}
	public boolean replace(bills order){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/editbill.fxml"));
			AnchorPane anchorpane = loader.load();
			Stage outserv = new Stage();
			outserv.setTitle("Retrieve Bill !!!");
			outserv.initModality(Modality.WINDOW_MODAL);
			outserv.initOwner(onOrder);
			Scene scene = new Scene(anchorpane);
			outserv.setScene(scene);
			outserv.show();
			editbill on =loader.getController();
			on.setOutorder(outserv);
			on.setOrder(order);
			return on.isOkClicked();
		}catch(Exception e){
e.printStackTrace();
}
		return onClicked;
		}
	@FXML	public void Delete(){
		try {
			con = cdb.sqlcon();
			stmt = con.createStatement();
			String del = "delete from bills where bill_num = '"+deletefild.getText()+"'";
			stmt.executeUpdate(del);

		}catch(Exception e){
			System.err.println("Massage: "+e.getMessage());
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
	public void billfilds(bills bills){
		if(bills != null){
			bill_numfild.setText(Integer.toString(bills.getBill_num()));
			shopfild.setText(bills.getShop());
			bill_datefild.setText(bills.getBill_date());
			customer_namefild.setText(bills.getCustomer_name());
			customer_phonefild.setText(Integer.toString(bills.getCustomer_phone()));
			tool_numfild.setText(Integer.toString(bills.getTool_num()));
			quantityfild.setText(Integer.toString(bills.getQuantity()));
			costfild.setText(Integer.toString(bills.getCost()));
			deliveryfild.setText(bills.getDelivery());
		}else{
			bill_numfild.setText("");
			shopfild.setText("");
			bill_datefild.setText("");
			customer_namefild.setText("");
			customer_phonefild.setText("");
			shopfild.setText("");
			tool_numfild.setText("");
			quantityfild.setText("");
			costfild.setText("");
			deliveryfild.setText("");
		}
	}
	@FXML
	private void customers(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/customer's_bill.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
			System.err.println("Error: "+ e.getMessage());
		}
	}
}

