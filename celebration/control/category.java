package celebration.control;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.cdb;
import celebration.model.Categories;
import core.StaticObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class category implements Initializable {
	Connection con =null;
	Statement stmt = null ;
	ResultSet rs = null;
	@FXML private Label cate_idlbl;
	@FXML private Label cate_namelbl;
	@FXML private Label quantitylbl;
	
	@FXML private TextField cate_idfild;
	@FXML private TextField cate_namefild;
	@FXML private TextField quantityfild;
	
	@FXML private TableView <Categories>list;
	@FXML private TableColumn <Categories, String>cate_namecol;
	
ObservableList<Categories> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cate_namecol.setCellValueFactory(new PropertyValueFactory<Categories,String>("cate_name"));
	getdata();
	list.getSelectionModel().selectedItemProperty().addListener(
			(observable, oldvalue, newvalue)-> categorydata(newvalue));
	}
	public void getdata(){
		try(
				Connection 	con = cdb.sqlcon(); 
				Statement 	stmt =con.createStatement(); 
				ResultSet 	rs = stmt.executeQuery("select * from category") ;
				){
			while(rs.next()){
				list.getItems().add(new Categories(rs.getInt("cate_id"),rs.getString("cate_name"),rs.getInt("quantity")));
				list.setItems(data);
			}
		}catch(SQLException s){
			System.err.println("error "+s.getMessage());
		}
	}

	public void categorydata(Categories categories){
		if(categories != null){
			cate_idlbl.setText(Integer.toString((categories.getCate_id())));
		 cate_namelbl.setText(categories.getCate_name());
			quantitylbl.setText(Integer.toString(categories.getQuantity()));
		}else{
			cate_idlbl.setText("");
		 cate_namelbl.setText("");
			quantitylbl.setText("");
		}
	}

	@FXML	public void New(){
		try {
			con = cdb.sqlcon();
			stmt = con.createStatement();
			String insert= "INSERT INTO `category`(`cate_id`, `cate_name`, `quantity`) "
					+ "VALUES ('"+cate_idfild.getText()+"', '"+cate_namefild.getText()+"','"+quantityfild.getText()+"')";
         stmt.executeUpdate(insert);
         Alert al = new Alert(AlertType.INFORMATION);
         al.setContentText("the new category is inserted seucces!!");
         al.show();
         
    	 
		} catch (SQLException e) {
System.err.println("Massage: "+e.getMessage());
System.err.println("Massage: "+e.getSQLState());	
		}
		
		}
	@FXML	public void Update(){
		try {
			con = cdb.sqlcon();
			stmt = con.createStatement();
			String update= "UPDATE `category` SET `cate_name`='"+cate_namefild.getText()+"',`quantity`='"+quantityfild.getText()+"' WHERE `cate_id`='"+cate_idfild.getText()+"'";
stmt.execute(update);
Alert al = new Alert(AlertType.INFORMATION);
al.setContentText("the selected category is updated!!");
al.show();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	@FXML	public void delete(){
		try {
			con = cdb.sqlcon(); 
			stmt =con.createStatement(); 
			 stmt.executeUpdate("delete from category where cate_id = '"+cate_idfild.getText()+"' " ) ;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Massage: "+e.getMessage());
		}
	}
	@FXML	public void back(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/homepage.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
			Main.shop.setScene(scene);
			Main.shop.show();
		}catch(Exception e){
		System.err.println("Massage: "+e.getMessage());
		}
	}

}
