package practiceDataDrivenTesting;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTestCheckPrjInBackEnd {
	@Test
	public void projectCheckTest() throws SQLException {
//		Step1: Load/ register the Database driver.
		String Expname="siri";
		boolean flag=false;  
		
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		
//		Step2: Connect to Database  ..connection fron java.sqlpachage 
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceprj", "root", "root");
		
		
//		Step3: Create SQl statement 
		Statement stat = conn.createStatement();
//		Step4: Execute Select Query get Result.
		ResultSet resultset=stat.executeQuery("select * from advanceprj");
		while(resultset.next()) {
			String actname=resultset.getString(2);
			if(Expname.equals(actname)) {   //if we use else block it always gives not availble upto name came so using flag
				flag=true;
				
			System.out.println(Expname+" is avalible====PASS");	
			}
		}
		if(flag==false) {
			System.out.println(Expname+ " is not availbale====Fail");
			Assert.fail();   //if we give asset then test case will fail other wise insted of if block it will execute else block but tastcase pass
		}
		//if table is there then for getting values
		
		
//		Step5: Close the connection.
		conn.close();

	}

	

}
