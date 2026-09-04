package practiceContactTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDates {

	public static void main(String[] args) throws Exception {
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
	        Row row=sh.getRow(4);
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
		
		//date format from java
		Date dateobj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String startdate=sim.format(dateobj);
		
		 Calendar cal = sim.getCalendar();
		 cal.add(Calendar.DAY_OF_MONTH, 30);
		 String enddate=sim.format(cal.getTime());
		 driver.findElement(By.name("support_start_date")).clear();
		 driver.findElement(By.name("support_start_date")).sendKeys(startdate);
		 
		 driver.findElement(By.name("support_end_date")).clear();
		 driver.findElement(By.name("support_end_date")).sendKeys(enddate);
		 
		 
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		
	
		//verify with startdate
		String actstartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstartdate.equals(startdate)) {
			System.out.println(startdate+" is created sucessfully====>PASS");
		}
		else {
			System.out.println(startdate+ " not created");
		}
		
		//verify with enddate
				String actenddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actenddate.equals(enddate)) {
					System.out.println(enddate+" is created sucessfully====>PASS");
				}
				else {
					System.out.println(enddate+ " not created");
				}
		Actions act=new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		

	}

}
