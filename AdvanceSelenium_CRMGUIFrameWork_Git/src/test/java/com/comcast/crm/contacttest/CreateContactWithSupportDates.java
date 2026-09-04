package com.comcast.crm.contacttest;

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
import com.comcast.crm.objectrepo.HomePage;
import com.comcast.crm.objectrepo.LoginPage;

public class CreateContactWithSupportDates {

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

		FileInputStream fis2=new FileInputStream("./testdata/testdata.xlsx"); 
		ExcelUtility eLib=new ExcelUtility();
		String lastname=eLib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNumber();


		driver.manage().window().maximize();
		WebDriverUtility wLib=new WebDriverUtility();
		
		//login to app
		LoginPage loginPom=new LoginPage(driver);
		loginPom.LoginToApp(URL, USERNAME, PASSWORD);

		HomePage home=new HomePage(driver);
		home.getContactLink().click();
		//driver.findElement(By.linkText("Contacts")).click();

		ContactPage contact=new ContactPage(driver);
		contact.getContactpusicon().click();

		String startdate=jLib.getSystemDateyyyyMMDD();
		String enddate=jLib.getRequiredDateyyyyMMdd(30);

		//create contact wth name startdate and end date
		CreateContactPage createcontact=new CreateContactPage(driver);
		createcontact.CreateContact(lastname, startdate, enddate);

		//verify with startdate
		ContactinfoPage contactinfo=new ContactinfoPage(driver);

		String actstartdate=contactinfo.getStartdateinfomsg().getText();
		if(actstartdate.equals(startdate)) {
			System.out.println(startdate+" is created sucessfully====>PASS");
		}
		else {
			System.out.println(startdate+ " not created");
		}

		//verify with enddate
		
		
		String actenddate=contactinfo.getEnddateinfomsg().getText();
		if(actenddate.equals(enddate)) {
			System.out.println(enddate+" is created sucessfully====>PASS");
		}
		else {
			System.out.println(enddate+ " not created");
		}
		home.signout();
		

		driver.quit();


	}

}
