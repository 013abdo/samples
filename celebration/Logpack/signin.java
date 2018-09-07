package celebration.Logpack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.cdb;

public class signin {
	Connection con;
public signin(){
	try {
		con = cdb.sqlcon();
		if(con == null) System.exit(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public boolean isConnect(){
	try {
		return !con.isClosed();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
}
public boolean isLogin(String user , String pass) throws SQLException{
            PreparedStatement pstmt = null ;
            ResultSet resultSet = null ;

            String query = "select * from log where username= ? and pass = ?" ;

            try {
            	pstmt =con.prepareStatement(query);
            	pstmt.setString(1, user);
            	pstmt.setString(2, pass);

                        resultSet = pstmt.executeQuery();

                        if(resultSet.next())
                        {
                                    return true;
                        }else{
                                    return false;
                        }

            } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        return false;
            }
}
}
