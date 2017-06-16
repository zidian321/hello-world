package com.ibm.esw.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
import java.util.Random;

import com.ibm.esw.db2.JDBCUtil;
import com.mysql.jdbc.PreparedStatement;
public class ConnectiontoMySql {
	
	static {
		
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(String url,String name,String password) throws SQLException{
			return DriverManager.getConnection(url, name, password);
		
	}
	
	public static void delete() throws SQLException{
		String sql="delete from persons where id>5";
		 PreparedStatement pst = (PreparedStatement) getConnection("jdbc:mysql://localhost:3306/test", "root", "yang8611018").prepareStatement(sql);
	     pst.execute();
	}
	public static void insert() throws SQLException{
		Connection conn = getConnection("jdbc:mysql://9.123.143.77:3306/test", "root", "yang8611018");
		Statement sta = conn.createStatement();
		ResultSet rs =sta.executeQuery("Select * from test.persons");
		for(int i=0;i<100;i++){
		String firstName = ((char)(new Random().nextInt(25)+'a'))+""+((char)(new Random().nextInt(25)+'a')+"Name");
		String lastName = "Yang";
		String Address = "Haidian";
		String city ="Beijing";
		String insertSql ="INSERT INTO Persons (LastName,FirstName,Address,city) VALUES (?,?,?,?)";
		PreparedStatement prt = (PreparedStatement) conn.prepareStatement(insertSql);
		prt.setString(1, firstName);
		prt.setString(2, lastName);
		prt.setString(3, Address);
		prt.setString(4, city);
		prt.execute();
		}
   }
	
	public static void main(String [] args) throws SQLException{
		
		
        //delete();
		insert();
		//int col = rs.getMetaData().getColumnCount();
		//System.out.println(col);
		//sta.executeUpdate(insertSql);
		int rowCount = 0;    
//		while(rs.next())    
//		{    
//		    rowCount++;
//		    System.out.println(rs.getString(2));
//		}  
//        System.out.println(rowCount);
	}

}
