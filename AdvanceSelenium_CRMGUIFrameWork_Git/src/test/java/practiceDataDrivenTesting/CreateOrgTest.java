package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException, Exception {
		
		//common data from propert file
		FileInputStream fis=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\commondata.properties");
         Properties prop=new Properties();
         prop.load(fis);
         String BROWSER=prop.getProperty("browser");
         String URL=prop.getProperty("url");
         String USERNAME=prop.getProperty("username");
         String PASSWORD=prop.getProperty("password");
         
         FileInputStream fis1=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
  		Workbook wb= WorkbookFactory.create(fis1);
  		
  		 Sheet sh=wb.getSheet("org");
  		Row row =sh.getRow(1);
  		String orgname=row. getCell(2).toString();
        
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
 		
 		 
 		driver.manage().window().maximize();
 		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
         
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.name("website")).sendKeys("Instagram");
		driver.findElement(By.id("tickersymbol")).sendKeys("a");
		driver.findElement(By.id("employees")).sendKeys("10");
		
		WebElement industry=driver.findElement(By.name("industry"));
		
		Select sel=new Select(industry);
		sel.selectByValue("Education");
		
		WebElement type=driver.findElement(By.name("accounttype"));
		Select seltype=new Select(type);
		seltype.selectByValue("Integrator");
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		Actions act=new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
	}

}
