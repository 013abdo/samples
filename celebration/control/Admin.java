package celebration.control;



import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Users.AddAdmin;
import Users.UpdateAdmin;
import application.Main;
import application.cdb;
import celebration.model.User;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 *dispay the data from the database into tableview!
 *select the data from table view to get the whole user information
 *show the data into an interface!*/
public class Admin implements Initializable{

	Connection 	con=null;
	Statement stmt =null;

	ResultSet rs = null;
	// the user properties from user table
	@FXML private TableView<User> table; 

	/* admin tableview columns*/
	@FXML private TableColumn<User, Integer> user_idColumn;
	@FXML private TableColumn<User, String> nameColumn;
	@FXML private TableColumn<User, Integer> phoneColumn;
	@FXML private TableColumn<User, String> positionColumn;
	@FXML private TableColumn<User, String> addressColumn;
	@FXML private TableColumn<User, String> shopColumn;
	
	@FXML private TextField searchfild;
	@FXML private TextField delfild;
	public  Stage newdialog;
	public  Stage editdialog;

	public boolean onClicked = false;
	@FXML private ImageView imgv;
	Image img;
	//a list gotten from the database!
	ObservableList<User> data = FXCollections.observableArrayList();

	public void initialize(URL location, ResourceBundle resources){
		// select the controller for display the data!
		user_idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("user_id"));	
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		phoneColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("phone"));
		positionColumn.setCellValueFactory(new PropertyValueFactory<User, String>("position"));
		shopColumn.setCellValueFactory(new PropertyValueFactory<User, String>("shop"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
//table.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue)-> User(newvalue));
		loadDataDasedata();
		FilteredList<User> filter = new FilteredList<>(data, u -> true);
		searchfild.textProperty().addListener((observable, oldValue, newValue) ->{
			filter.setPredicate(User -> {
				if(newValue == null || newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter =  newValue.toLowerCase();
				if(User.getName().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}else if(User.getAddress().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<User> sorted = new SortedList<>(filter);
		sorted.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sorted);
	}
	/*method to show the data from database*/
	public void loadDataDasedata(){
		try(
				Connection 	con = cdb.sqlcon(); 
			Statement 	pstmt =con.createStatement(); 
				ResultSet 	rs = pstmt.executeQuery("select * from users ") ;
				){ 
			while(rs.next()){
				table.getItems().add(new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("position"), rs.getInt("phone"),rs.getString("shop"), rs.getString("address")));
				table.setItems(data);
			}
		}catch(SQLException s){
			System.err.println("s "+s.getSQLState());
			System.err.println("s "+s.getMessage());
		}
	}
	
	public void deleteuser(){
			try (
					Connection 	con = cdb.sqlcon(); 
					Statement 	stmt =con.createStatement(); 
						){
				 stmt.executeUpdate("delete from users where user_id = '"+delfild.getText()+"' ") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Massage: "+e.getMessage());
		}
	}
	public boolean newadmin(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/celebration/view/Addadmin.fxml"));
			AnchorPane anchorpane = loader.load();
			
			Stage adduser = new Stage();
			adduser.setTitle("New Users!!");
			adduser.initModality(Modality.WINDOW_MODAL);
			adduser.initOwner(newdialog);
			Scene scene = new Scene(anchorpane);
			adduser.setScene(scene);
			adduser.show();
			
			AddAdmin put = loader.getController();
			put.setAdddialog(adduser);
		
		}catch(Exception e){
			System.err.println("Massage: "+e.getMessage());
		}
		return onClicked;
		
	}
	public boolean editadmindialog(User user){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/celebration/view/Editadmin.fxml"));
			AnchorPane anchorpane = loader.load();
			Stage edituser = new Stage();
			edituser.setTitle("update Users!!");
			edituser.initModality(Modality.WINDOW_MODAL);
			edituser.initOwner(editdialog);
			Scene scene = new Scene(anchorpane);
			edituser.setScene(scene);
			edituser.show();
			
			UpdateAdmin put = loader.getController();
			put.setShopdialog(edituser);
		put.setUser(user);
		}catch(Exception e){
			System.err.println("Massage: "+e.getMessage());
		}
		return onClicked;
	}
public void edituser(){
	User selectuser = table.getSelectionModel().getSelectedItem();
	if(selectuser != null){
		boolean okClicked = editadmindialog(selectuser);
		if(okClicked){
		//	showUserInfo(selectuser);
		}
	} else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(editdialog);
        alert.setTitle("No Selection");
        alert.setHeaderText("No User Selected");
        alert.setContentText("Please select a User in the table.");

        alert.showAndWait();
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
			System.err.println("Massage: "+e.getMessage());
		}
	}

}

