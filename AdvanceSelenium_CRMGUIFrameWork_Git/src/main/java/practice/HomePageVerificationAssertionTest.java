package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageVerificationAssertionTest {
	
@Test
	public void homeVerification() {
		String expResult="Home";
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		String actualres=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		Assert.assertEquals(actualres, expResult);
//		
//	if(actualres.trim().equals(expResult)) {
//			System.out.println(expResult+"===>pass");
//		}else {
////			System.out.println(expResult+"Fail");
//			}
		
		driver.close();
	}
	@Test
	public void homeLogoVErification() {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		Boolean actualres=driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
		Assert.assertTrue(actualres);
//		if(actualres) {
//			System.out.println(actualres+"===>pass");
//		}else {
//			System.out.println(actualres+"Fail");
//		}
		
		driver.close();
	}
	

}
