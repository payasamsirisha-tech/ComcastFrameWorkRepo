package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.time.Duration;
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
import com.comcast.crm.objectrepo.CreateOrgPage;
import com.comcast.crm.objectrepo.HomePage;
import com.comcast.crm.objectrepo.LoginPage;
import com.comcast.crm.objectrepo.OrgInfoPage;
import com.comcast.crm.objectrepo.OrgPage;

public class CreateOrg {

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

		//      click on org link
		HomePage home=new HomePage(driver);
		home.getOrgLink().click();

		//click on orgplus icon
		OrgPage org=new OrgPage(driver);
		org.getorgpusicon().click();

		//create org with name
		CreateOrgPage createorg=new CreateOrgPage(driver);
		createorg.createorg(orgname);

		//verify the header info 
		OrgInfoPage orginfo=new OrgInfoPage(driver);
		String headerinfo=orginfo.getOrgHeaderMsg().getText();

		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+" is created sucessfully");
		}
		else
			System.out.println(orgname+ " not created");

		//verify with name
		String actorgName=orginfo.getOrgnameinfomsg().getText();
		if(actorgName.equals(orgname)) {
			System.out.println(orgname+" is created sucessfully====>PASS");
		}
		else {
			System.out.println(orgname+ " not created");
		}
		home.signout();
		driver.quit();

	}

}
