package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromCreateorgWithXML {
	@Test

	public  void createorg(XmlTest test) throws FileNotFoundException, Throwable {
		//read data from json

		
       String BROWSER=test.getParameter("browser");
       String URL=test.getParameter("url");
       String USERNAME=test.getParameter("username");
       String PASSWORD=test.getParameter("password");
       

		FileInputStream fis1=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Random random=new Random();
      	int randomint= random.nextInt(1000);
		Sheet sh=wb.getSheet("org");
		Row row =sh.getRow(1);
		String orgname=row. getCell(2).toString()+randomint;

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

		

		driver.quit();


	}
}
