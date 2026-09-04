package practiceDataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class WorkingWithJDBC_with_Non_SelectQuertTest {

	public static void main(String[] args) throws SQLException {
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceprj", "root", "root");
		Statement stat = conn.createStatement();
		int result=stat.executeUpdate("insert into advanceprj values(110,'chinni');");
		//result 1 is executer
		//result 0 means not executed
   System.out.println(result);
   
   conn.close();
   }

}
