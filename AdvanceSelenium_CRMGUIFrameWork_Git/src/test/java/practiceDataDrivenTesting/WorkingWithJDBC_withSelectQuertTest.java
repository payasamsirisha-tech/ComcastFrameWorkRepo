package practiceDataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class WorkingWithJDBC_withSelectQuertTest {

	public static void main(String[] args) throws SQLException {
		 Connection conn=null;
		 try {
//		Step1: Load/ register the Database driver. 
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		
//		Step2: Connect to Database  ..connection fron java.sqlpachage 
          conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceprj", "root", "root");
		
		
//		Step3: Create SQl statement 
		Statement stat = conn.createStatement();
//		Step4: Execute Select Query get Result.
		ResultSet resultset=stat.executeQuery("select * from advanceprj");
		while(resultset.next()){
		//if table is there then for getting values
		System.out.println(resultset.getInt(1)+"\t"+resultset.getString(2));
	}
		 }
		 catch (Exception e) {
			System.out.println("exception handled");
		}
//		Step5: Close the connection.
		 finally {
		conn.close();
		 }

	}

}
