package celebration.control;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Tools.Addtools;
import Tools.Updatetool;
import application.Main;
import application.cdb;
import celebration.model.User;
import celebration.model.tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class tool implements Initializable {
	Connection 	con=null;
	Statement stmt =null;

	ResultSet rs = null;
	
	private Stage toolsstages;
	private boolean okClicked = false;
	private tools kits;
	
	@FXML
	private TableView <tools> tooltable;
	@FXML
	private TableColumn<tools,Integer> tool_num;
	@FXML
	private TableColumn <tools,String> tool_name; 
	@FXML
	private TableColumn <tools,String> category;
	@FXML
	private TableColumn <tools,Integer> cost; 
	@FXML
	private TableColumn <tools,Integer> quantity; 
	@FXML
	private TableColumn <tools,String> shop; 
	@FXML
	private TableColumn <tools,String> address;
	
	@FXML	private TextField tool_numfield;
	@FXML	private TextField tool_namefield; 
	@FXML	private TextField categoryfield;
	@FXML	private TextField costfield; 
	@FXML	private TextField quantityfield; 
	@FXML	private TextField shopfield; 
	@FXML 	private TextField addressfield;
	@FXML 	private TextField searchfild;
	@FXML 	private TextField delfild;

	ObservableList<tools> details =FXCollections.observableArrayList();

	public tools getKits() {
		return kits;
	}
	public void setKits(tools kits) {
		this.kits = kits;
	}
	public boolean isOkClicked() {
		return okClicked;
	}
	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}
	public Stage getToolsstages() {
		return toolsstages;
	}
	public void setToolsstages(Stage toolsstages) {
		this.toolsstages = toolsstages;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tool_num.setCellValueFactory(new PropertyValueFactory<tools, Integer>("tool_num"));		
		tool_name.setCellValueFactory(new PropertyValueFactory<tools, String>("tool_name"));		
		category.setCellValueFactory(new PropertyValueFactory<tools, String>("category"));		
		cost.setCellValueFactory(new PropertyValueFactory<tools, Integer>("cost"));	
		quantity.setCellValueFactory(new PropertyValueFactory<tools, Integer>("quantity"));		
		shop.setCellValueFactory(new PropertyValueFactory<tools, String>("shop"));		
		address.setCellValueFactory(new PropertyValueFactory<tools, String>("address"));		
		loadDatabasedata();
		FilteredList<tools> filter = new FilteredList<>(details, u -> true);
		searchfild.textProperty().addListener((observable, oldValue, newValue) ->{
			filter.setPredicate(tools -> {
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter =  newValue.toLowerCase();
				if(tools.getTool_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(tools.getAddress().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(tools.getShop().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<tools> sorted = new SortedList<>(filter);
		sorted.comparatorProperty().bind(tooltable.comparatorProperty());
		tooltable.setItems(sorted);
	}
	public void loadDatabasedata(){
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("select * from tools") ;
				){ 
			while(rs.next()){
				tooltable.getItems().add(new tools(rs.getInt("tool_num"), rs.getString("tool_name"),
						rs.getString("category"), rs.getInt("cost"), rs.getInt("quantity"), rs.getString("shop"), rs.getString("address")));
				//	System.out.println(("user "+rs.getInt("user_id")+" name  "+ rs.getString("name")+" phone "+ rs.getInt("phone")));
				tooltable.setItems(details);
			}
		}catch(SQLException s){
			System.err.println("s "+s.getSQLState());
			System.err.println("s "+s.getMessage());
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
	@FXML
	private void Newtool() throws IOException{ 
		add();
	}
	public boolean add() throws IOException{

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/celebration/view/addtool.fxml"));
		AnchorPane anchorpane = loader.load();
		Stage anc = new Stage();
		anc.setTitle("tool Addition");
		anc.initModality(Modality.WINDOW_MODAL);
		anc.initOwner(toolsstages);
		Scene scene = new Scene(anchorpane);
		anc.setScene(scene);
		anc.show();
		Addtools t= loader.getController();
		t.setTooldialog(anc);
		return t.isOkClicked();
	}
	@FXML
	private void Edittool() throws IOException{ 
	
		tools equip = tooltable.getSelectionModel().getSelectedItem();
		if(equip != null){
			boolean okClicked =edit(equip);
			if(okClicked){
				showtool(equip);
			}
		}else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(toolsstages);
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Tool Selected");
	        alert.setContentText("Please select a tool in the table.");

	        alert.showAndWait();
	    }
	}
public boolean edit(tools kit) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/celebration/view/Edittool.fxml"));
		AnchorPane anchorpane = loader.load();
		
		Stage an = new Stage();
		an.setTitle("update tools");
		an.initModality(Modality.WINDOW_MODAL);
		an.initOwner(toolsstages);
		Scene scene = new Scene(anchorpane);
		an.setScene(scene);
		an.show();
		Updatetool e = loader.getController();
		e.setEditdialog(an);
		e.setTool(kit);
	return e.isOkClicked();
	}

	public void delete(){

			try {
			con = cdb.sqlcon();
			stmt = con.createStatement();
			String delete ="delete from tools where tool_num = '"+delfild.getText()+"'";
			stmt.executeUpdate(delete);

		}catch(Exception e){
			System.err.println("massage:"+e.getMessage());
		}

	}

	public void showtool(tools tool) {
		if(tool != null){
		tool_numfield.setText(Integer.toString(tool.getTool_num()));
		tool_namefield.setText(tool.getTool_name());
		costfield.setText(Integer.toString(tool.getCost()));
		quantityfield.setText(Integer.toString(tool.getQuantity()));
		addressfield.setText(tool.getAddress());
	}else{
		tool_numfield.setText("");
		tool_namefield.setText("");
		costfield.setText("");
		quantityfield.setText("");
		addressfield.setText("");
	}
}
	}
