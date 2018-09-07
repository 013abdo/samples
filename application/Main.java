package application;




import core.StaticObject;

import java.sql.Connection;

import java.sql.SQLException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;



public class Main extends Application {
	public static cdb sql;
	public static Stage shop;
	//public  static Admin adminData;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/celebration/view/homepage.fxml"));
			AnchorPane anc = loader.load();
			Scene scene = new Scene(anc);
			shop = new Stage();
			shop.setTitle("Welcome to Celebration shop");
			shop.getIcons().add(new Image("/img/celebration.jpg"));
			shop.setScene(scene);
			shop.show();
			//	adminData.getDataDasedata();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//	StaticObject.onStart();
		try (//closable 
				Connection con = cdb.sqlcon(); 
				){
			System.out.println("ใสีแ!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		launch(args);

	}
}
