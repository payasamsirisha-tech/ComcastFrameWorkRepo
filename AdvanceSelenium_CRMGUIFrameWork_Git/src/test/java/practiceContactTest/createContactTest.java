package practiceContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class createContactTest {

	public static void main(String[] args) throws IOException, Exception {

		FileInputStream fis=new FileInputStream("./src/test/resources/data/CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER=prop.getProperty("browser");
		String URL=prop.getProperty("url");
		String USERNAME=prop.getProperty("username");
		String PASSWORD=prop.getProperty("password");
		   WebDriver driver=null;
	         if(BROWSER.contentEquals("chrome")) {
	        	  driver=new ChromeDriver();
	         }
	         else if(BROWSER.equalsIgnoreCase("edge")){
	        	 driver=new EdgeDriver();
	        	 
	         }
	         else if(BROWSER.equalsIgnoreCase("Firefox")) {
	        	 driver=new FirefoxDriver();
	         }
	         else {
	        	 driver=new ChromeDriver();
	         }
	         Random random=new Random();
	         int randomint= random.nextInt(1000);
	         
	       FileInputStream fis2=new FileInputStream("./src/test/resources/data/testdata.xlsx"); 
	       Workbook wb=WorkbookFactory.create(fis2);
	       Sheet sh=wb.getSheet("contact");
	        Row row=sh.getRow(1);
	        String lastname=row.getCell(2).toString()+randomint;
	        wb.close();
	         
	       driver.manage().window().maximize();
	 	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	 	
	 	driver.get(URL);
	 	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();

		
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		
		//verify the header info 
		String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(lastname)) {
			System.out.println(lastname+" is created sucessfully");
		}
		else
			System.out.println(lastname+ " not created");
		
		//verify with name
		String actorgName=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actorgName.equals(lastname)) {
			System.out.println(lastname+" is created sucessfully====>PASS");
		}
		else {
			System.out.println(lastname+ " not created");
		}
		Actions act=new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
	}

}
