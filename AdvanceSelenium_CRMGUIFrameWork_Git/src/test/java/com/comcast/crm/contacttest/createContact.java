package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.experimental.theories.suppliers.TestedOn;
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

public class createContact  {

	public static void main(String[] args) throws Throwable {
		/* createobject for fileutility */
		FileUtility fLib = new FileUtility();
		String BROWSER = fLib.getDataFromPropertyFile("browser");
		String URL = fLib.getDataFromPropertyFile("url");
		String USERNAME = fLib.getDataFromPropertyFile("username");
		String PASSWORD = fLib.getDataFromPropertyFile("password");

		WebDriver driver = null;
		if (BROWSER.contentEquals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (BROWSER.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		JavaUtility jLib = new JavaUtility();

		ExcelUtility eLib = new ExcelUtility();
		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		WebDriverUtility wLib = new WebDriverUtility();

		driver.manage().window().maximize();

		// login to app
		LoginPage loginPom = new LoginPage(driver);
		loginPom.LoginToApp(URL, USERNAME, PASSWORD);

		// click on contactlink
		HomePage home = new HomePage(driver);
		home.getContactLink().click();

		// click on contact plus icon
		ContactPage contact = new ContactPage(driver);
		contact.getContactpusicon().click();

		// create contact with last name
		CreateContactPage createcontact = new CreateContactPage(driver);
		createcontact.CreateContact(lastname);

		// verify the header info
		ContactinfoPage contactinfo = new ContactinfoPage(driver);

		String headerinfo = contactinfo.getContactheaderinfo().getText();
		if (headerinfo.contains(lastname)) {
			System.out.println(lastname + " is created sucessfully");
		} else
			System.out.println(lastname + " not created");

		// verify with name
		String actorgName = contactinfo.getContactnameinfo().getText();
		if (actorgName.equals(lastname)) {
			System.out.println(lastname + " is created sucessfully====>PASS");
		} else {
			System.out.println(lastname + " not created");
		}
		home.signout();

		driver.quit();

	}

}
