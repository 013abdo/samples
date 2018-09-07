package celebration.control;

import java.net.URL;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.cdb;
import celebration.model.Shop;
import celebration.model.User;
import celebration.view.Addshop;
import celebration.view.UpdateShop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class shop implements Initializable{

	Connection 	con=null;
	Statement stmt =null;
	ResultSet rs = null;
	private Stage shops ;
	private boolean okClicked = false;

	// the user properties from user table
	@FXML private TableView<Shop> shoptable; 

	@FXML private TableColumn<Shop, String> shop_nameCol;

	@FXML private TableColumn<Shop, Integer>  shop_phoneCol;
	@FXML private TableColumn<Shop, String>  shop_cityCol;
	@FXML private TableColumn<Shop, Integer>  shop_telCol;
	@FXML private TableColumn<Shop, String>  shop_addressCol;
	@FXML private TableColumn<Shop, String>  shop_ownerCol;
	@FXML private TableColumn<Shop, Integer>  shop_numCol;
	@FXML private TextField deletefild;
	@FXML private TextField sortfild;

	@FXML
	private TextField  shop_numfield;
	@FXML
	private TextField  shop_namefield;
	@FXML
	private TextField  shop_phonefield;
	@FXML
	private TextField  shop_cityfield;
	@FXML
	private TextField  shop_telfield;
	@FXML 
	private TextField shop_addressfield;
	@FXML 
	private TextField  shop_ownerfield;

	ObservableList<Shop> Data = FXCollections.observableArrayList();

	public Stage getShops() {
		return shops;
	}
	public void setShops(Stage shops) {
		this.shops = shops;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	public void initialize(URL location, ResourceBundle resources){
		// select the controller for display the data!
		shop_nameCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shop_name"));
		shop_numCol.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("shop_num"));
		shop_phoneCol.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("shop_phone"));
		shop_cityCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shop_city"));
		shop_telCol.setCellValueFactory(new PropertyValueFactory<Shop, Integer>("shop_tel"));
		shop_addressCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shop_address"));
		shop_ownerCol.setCellValueFactory(new PropertyValueFactory<Shop, String>("shop_owner"));

		loadDataDasedata();
		FilteredList<Shop> filter = new FilteredList<>(Data, u -> true);
		sortfild.textProperty().addListener((observable, oldValue, newValue) ->{
			filter.setPredicate(Shop -> {
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter =  newValue.toLowerCase();
				if(Shop.getShop_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(Shop.getShop_address().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<Shop> sorted = new SortedList<>(filter);
		sorted.comparatorProperty().bind(shoptable.comparatorProperty());
		shoptable.setItems(sorted);
	}

	private void loadDataDasedata() {
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("SELECT * FROM `shop`") ;
				){ 
			while(rs.next()){
				shoptable.getItems().add(new Shop(rs.getInt("shop_num"), rs.getString("shop_name"), rs.getInt("shop_tel"),
						rs.getInt("shop_phone"), rs.getString("shop_city"), rs.getString("shop_address"), rs.getString("shop_owner")));
				shoptable.setItems(Data);
			}
		}catch(SQLException s){
			System.err.println("s "+s.getSQLState());
			System.err.println("s "+s.getMessage());
		}

	}
	
	public void newshop(){
	market();
	}
	public boolean market(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/Addshop.fxml"));
			AnchorPane anchorpane = loader.load();
			Stage open = new Stage();
			open.setTitle("New shop !!");
			open.initModality(Modality.WINDOW_MODAL);
			open.initOwner(shops);
			Scene scene = new Scene(anchorpane);
			open.setScene(scene);
			open.show();
			Addshop s = loader.getController();
			s.setNewshop(open);
			return s.isOkClicked();
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
	}
	public void editshop(){
		Shop place = shoptable.getSelectionModel().getSelectedItem();
		if(place != null){
			boolean okClicked = edit(place);
			if(okClicked){
				
			}
		}
	}
	public boolean edit(Shop mark){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/Editshop.fxml"));
			AnchorPane anchorpane = loader.load();
			Stage repair = new Stage();
			repair.setTitle("Repair / update shop !!");
			repair.initModality(Modality.WINDOW_MODAL);
			repair.initOwner(shops);
			Scene scene = new Scene(anchorpane);
			repair.setScene(scene);
			repair.show();
			UpdateShop e = loader.getController();
			e.setBuilding(repair);
			e.setShop(mark);
			return e.isOkClicked();
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		}
	
	public void deleteshop(){
		try {
			con = cdb.sqlcon(); 
			stmt =con.createStatement(); 
			stmt.executeUpdate("delete from shop where shop_num = '"+deletefild.getText()+"' ") ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			e.printStackTrace();
		}
	}
	public void setShop(Shop shop) {
		if(shop  != null){
		shop_numfield.setText(Integer.toString(shop.getShop_num()));
		shop_namefield.setText(shop.getShop_name());
		shop_telfield.setText(Integer.toString(shop.getShop_num()));
		shop_phonefield.setText(Integer.toString(shop.getShop_num()));
		shop_addressfield.setText(shop.getShop_address());
		shop_cityfield.setText(shop.getShop_city());
		shop_ownerfield.setText(shop.getShop_owner());
		}else{
			shop_numfield.setText("");
			shop_namefield.setText("");
			shop_telfield.setText("");
			shop_phonefield.setText("");
			shop_addressfield.setText("");
			shop_cityfield.setText("");
			shop_ownerfield.setText("");
			}
	}
}
