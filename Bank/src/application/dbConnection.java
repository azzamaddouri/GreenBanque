package application;

import java.sql.*;

public class dbConnection {
	 public static Connection connect() throws SQLException{
	     try {
	 Class.forName("com.mysql.jdbc.Driver");
	 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banque","root","");
	 return con;
	 }catch(Exception e) {
	 e.printStackTrace();
	 }
	return null ;
	}
}

