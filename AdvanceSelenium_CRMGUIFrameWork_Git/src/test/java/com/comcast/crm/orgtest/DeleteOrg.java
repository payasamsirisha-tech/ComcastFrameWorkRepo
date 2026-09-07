package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepo.CreateOrgPage;
import com.comcast.crm.objectrepo.HomePage;
import com.comcast.crm.objectrepo.LoginPage;
import com.comcast.crm.objectrepo.OrgInfoPage;
import com.comcast.crm.objectrepo.OrgPage;

public class DeleteOrg {
	public static void main(String[] args) throws Throwable {
		/* createobject for fileutility */
		FileUtility fLib=new FileUtility();
		String BROWSER=fLib.getDataFromPropertyFile("browser");
		String URL=fLib.getDataFromPropertyFile("url");
		String USERNAME=fLib.getDataFromPropertyFile("username");
		String PASSWORD=fLib.getDataFromPropertyFile("password");

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
		JavaUtility jLib=new JavaUtility();
		/* create object excelUtility */
		ExcelUtility eLib=new ExcelUtility();
		String orgname=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();


		driver.manage().window().maximize();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//loginpage
		LoginPage loginPom=new LoginPage(driver);
		loginPom.LoginToApp(URL, USERNAME, PASSWORD);
		//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//		driver.findElement(By.id("submitButton")).click();
		HomePage home=new HomePage(driver);
		home.getOrgLink();
		//driver.findElement(By.linkText("Organizations")).click();

		OrgPage org=new OrgPage(driver);
		org.getorgpusicon();
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		CreateOrgPage createorg=new CreateOrgPage(driver);
		createorg.createorg(orgname);
//driver.findElement(By.name("accountname")).sendKeys(orgname);
		
		//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();



		//verify the header info 
		OrgInfoPage orginfo=new OrgInfoPage(driver);
		String headerinfo=orginfo.getOrgHeaderMsg().getText();
		//String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+" is created sucessfully");
		}
		else
			System.out.println(orgname+ " not created");

		//verify with name
		String actorgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actorgName.equals(orgname)) {
			System.out.println(orgname+" is created sucessfully====>PASS");
		}
		else {
			System.out.println(orgname+ " not created");
		}
		home.getOrgLink();
		org.getSearchEdt().sendKeys(orgname);
	
	wLib.select(org.getSearchdropdown(), "Organization Name");
	org.getSearchtn().click();
	driver.findElement(By.xpath("//a[text()='"+orgname+"']/../..//a[text()='del']")).click();
	driver.switchTo().alert().accept();
	
	

		//delete org
		
		home.signout();
		driver.quit();

	}


}
