package application;

public class LoginPage {
	/*package application;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.image.Image;
	import javafx.stage.Stage;
	 
	public class loginController {
	                Connection conection;
	                @FXML
	                private TextField username;
	                @FXML
	                private TextField password;
	                @FXML
	                private Label label1;
	 
	                public boolean login() throws SQLException, IOException{
	 
	                                try{
	                                                conection = dbconn.Connector();
	 
	                                                Statement st = conection.createStatement();
	                                                ResultSet rs = st.executeQuery("select * from Login where username =  '"+username.getText()+"' and password=  '"+password.getText()+"'");
	 
	                                                if(rs.next()){
	 
	                                                                Stage primaryStage = new Stage();
	                                                                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
	                                                                Parent root = (Parent) loader.load();
	                                                                Scene scene = new Scene(root);
	                                                                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	                                                                primaryStage.setTitle("Employee System");
	                                                                primaryStage.getIcons().add(new Image("file:Resources/Images/syn-kf-badge-design.png"));
	                                                                primaryStage.setScene(scene);
	                                                                primaryStage.show();
	                                                               
	                                                               
	                                                                return true;
	                                                }else{
	                                                                label1.setText("Password is incorrect. Please Try Again");
	                                                }
	 
	                                                st.close();
	                                                rs.close();
	                                                conection.close();
	                                                return true;
	 
	                                } catch (SQLException ex) {
	                                                ex.printStackTrace();
	                                                return false;
	                                }catch (Exception e) {
	                                                e.printStackTrace();
	                                                return false;
	                                }
	                }
	               
	}
	 
	 
	package application;
	 
	import java.sql.*;
	 
	public class LoginModel {
	 
	            Connection conection;
	 
	            public LoginModel(){
	                        conection = dbconn.Connector();
	 
	                        if(conection == null) System.exit(1);
	 
	            }
	 
	            public boolean isDbConnected()
	            {
	 
	                        try {
	                                    return  !conection.isClosed();
	                        } catch (SQLException e) {
	                                    // TODO Auto-generated catch block
	                                    e.printStackTrace();
	                                    return false;
	                        }
	 
	            }
	 
	            public boolean isLogin(String user , String pass) throws SQLException
	            {
	 
	                        PreparedStatement preparedStatement = null ;
	                        ResultSet resultSet = null ;
	 
	                        String query = "select * from Login where username= ? and password = ?" ;
	 
	                        try {
	                                    preparedStatement =conection.prepareStatement(query);
	                                    preparedStatement.setString(1, user);
	                                    preparedStatement.setString(2, pass);
	 
	                                    resultSet = preparedStatement.executeQuery();
	 
	                                    if(resultSet.next())
	                                    {
	                                                return true;
	                                    }
	                                    else
	                                    {
	                                                return false;
	                                    }
	 
	                        } catch (SQLException e) {
	                                    // TODO Auto-generated catch block
	                                    return false;
	                        }finally
	                        {
	                                    preparedStatement.close();
	                                    resultSet.close();
	                        }
	            }
	 
	 
	}*/
}
