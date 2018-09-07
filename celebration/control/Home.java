package celebration.control;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Home {
	@FXML
	private void tool(){
	try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass()
				.getResource("/celebration/view/tools.fxml"));
		AnchorPane anchorpane = loader.load();
		Scene scene = new Scene(anchorpane);
	Main.shop.setScene(scene);
	Main.shop.show();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	@FXML
	private void Users(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/Users.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private void Bill(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/bills.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
		}catch(Exception e){
System.err.println("note:"+e.getMessage());	


}
	}
	@FXML
	private void shop(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/shop.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
		}catch(Exception e){
			System.err.println("note:"+e.getMessage());	
		}
	}
	@FXML
	private void category(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/category.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
		}catch(Exception e){
			System.err.println("note:"+e.getMessage());	
		}
	}
	
	@FXML
	private void customer(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass()
					.getResource("/celebration/view/customer's_bill.fxml"));
			AnchorPane anchorpane = loader.load();
			Scene scene = new Scene(anchorpane);
		Main.shop.setScene(scene);
		Main.shop.show();
		}catch(Exception e){
			System.err.println("note:"+e.getMessage());	
		}
	}
	@FXML private void handleExit() {
		System.exit(0);
	}
}