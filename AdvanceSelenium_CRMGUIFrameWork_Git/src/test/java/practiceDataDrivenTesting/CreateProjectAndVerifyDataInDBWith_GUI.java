package practiceDataDrivenTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.mysql.jdbc.Driver;

public class CreateProjectAndVerifyDataInDBWith_GUI {

	public static void main(String[] args) throws Exception {
		//create project in GUI using selenium code
		String projectname="FB_100";
	
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://49.249.29.4:8091/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999")	;
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		
		
		driver.findElement(By.name("projectName")).sendKeys(projectname);
		driver.findElement(By.name("createdBy")).sendKeys("sirisha");
		Thread.sleep(2000);
		WebElement ele=driver.findElement(By.xpath("//label[text()='Project Status* ']/following-sibling::select"));
		Select sel=new Select(ele);
		Thread.sleep(2000);
		sel.selectByValue("onGoing");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//verify the project in db backend using jdbc

		boolean flag=false;  
		
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		 Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceprj", "root", "root");
		Statement stat = conn.createStatement();//		Step4: Execute Select Query get Result.
		ResultSet resultset=stat.executeQuery("select * from advanceprj");
		while(resultset.next()) {
			String actname=resultset.getString(2);
			if(projectname.equals(actname)) {   //if we use else block it always gives not availble upto name came so using flag
				flag=true;
				
			System.out.println(projectname+" is avalible====PASS");	
			 }
			
			}
		if(flag==false) {
			System.out.println(projectname  + " is not availbale====Fail");
			Assert.fail();   //if we give asset then test case will fail other wise insted of if block it will execute else block but tastcase pass
		}
		
		conn.close();
	}

}
