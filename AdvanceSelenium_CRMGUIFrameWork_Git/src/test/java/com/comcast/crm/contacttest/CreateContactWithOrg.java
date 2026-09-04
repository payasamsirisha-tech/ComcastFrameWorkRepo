package com.comcast.crm.contacttest;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepo.ContactPage;
import com.comcast.crm.objectrepo.ContactinfoPage;
import com.comcast.crm.objectrepo.CreateContactPage;
import com.comcast.crm.objectrepo.CreateOrgPage;
import com.comcast.crm.objectrepo.HomePage;
import com.comcast.crm.objectrepo.LoginPage;
import com.comcast.crm.objectrepo.OrgInfoPage;
import com.comcast.crm.objectrepo.OrgPage;

public class CreateContactWithOrg {

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

		ExcelUtility eLib=new ExcelUtility();
		String orgname=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
		String lastname=eLib.getDataFromExcel("contact", 7, 3)+jLib.getRandomNumber();


		WebDriverUtility wLib=new WebDriverUtility();

		driver.manage().window().maximize();
		

		//login to app
		LoginPage loginPom=new LoginPage(driver);
		loginPom.LoginToApp(URL, USERNAME, PASSWORD);

		//create org click on orglink
		HomePage home=new HomePage(driver);
		home.getOrgLink().click();

		//click on orgplus icon
		OrgPage org=new OrgPage(driver);
		org.getorgpusicon().click();

		//create org with name
		CreateOrgPage createorg=new CreateOrgPage(driver);
		createorg.createorg(orgname);

		OrgInfoPage orginfo=new OrgInfoPage(driver);

		//verify the header info 
		String headerinfo=orginfo.getOrgHeaderMsg().getText();
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+" is created sucessfully====");
		}
		else
			System.out.println(orgname+ " not created");

		//create contact
		home.getContactLink().click();

		//		//identify the plus icon
		ContactPage contact=new ContactPage(driver);
		contact.getContactpusicon().click();

		//create contact with name and save
		CreateContactPage createcontact=new CreateContactPage(driver) ;
		createcontact.CreateContactwithorg(lastname);

		String parent=driver.getWindowHandle();
		wLib.switchToChildWindow(driver, "module=Accounts&action");


		driver.findElement(By.id("search_txt")).sendKeys(orgname);
		//click on search
		driver.findElement(By.name("search")).click();

		//select our org name
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();

		wLib.parentWindow(driver, parent);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//verify the header info 
		ContactinfoPage contatcinfo=new ContactinfoPage(driver);

		String contactheaderinfo=contatcinfo.getContactheaderinfo().getText();
		if(contactheaderinfo.contains(lastname)) {
			System.out.println(lastname+" is created sucessfully");
		}
		else
			System.out.println(lastname+ " not created");

		//verify with orgname
		String actorgName=contatcinfo.getContactorgnameinfo().getText();
		if(actorgName.trim().equals(orgname)) {
			System.out.println(orgname+" is created sucessfully====>PASS");
		}
		else {
			System.out.println(orgname+ " not created");
		}
		home.signout();

		driver.quit();



	}

}