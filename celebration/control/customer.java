package celebration.control;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class customer {
	@FXML
	private void back(){
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
