package celebration.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class login {
	@FXML
	private void login(){
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
	private void sign(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/addpage.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
