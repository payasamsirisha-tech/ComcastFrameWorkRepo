package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepo.HomePage;
import com.comcast.crm.objectrepo.LoginPage;

public class BaseClass {

	public DataBaseUtility dLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver;
	public  WebDriver sdriver; // for listner
	

	@BeforeSuite(groups = { "smokeTest", "RegressionTest" })
	public void configBS() throws SQLException {
		System.out.println("===connect DB and report config===");
		dLib.getDbCOnnection();
	}

	// @Parameters("BROWSER")
	@BeforeClass(groups = { "smokeTest", "RegressionTest" })
	// public void configBC(String browser) throws Throwable { //this is for
	// parallel execution
	public void configBC() throws Throwable {
		System.out.println("===launch browser===");
		//String BROWSER = fLib.getDataFromPropertyFile("browser");
		String BROWSER=System.getProperty("browser",fLib.getDataFromPropertyFile("browser") );
		
		// String BROWSER = browser;

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else if (BROWSER.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();

		}
		//sdriver = driver; // for listner but it is not paticepate in parallel execution so creating utility claa
		UtilityClassObject.setDriver(sdriver);
	}

	@BeforeMethod(groups = { "smokeTest", "RegressionTest" })
	public void configBM() throws IOException {
		System.out.println("====loging====");
//		String URL = fLib.getDataFromPropertyFile("url");
//		String USERNAME = fLib.getDataFromPropertyFile("username");
//		String PASSWROD = fLib.getDataFromPropertyFile("password");
		String URL = System.getProperty("url",fLib.getDataFromPropertyFile("url"));
		String USERNAME = System.getProperty("username",fLib.getDataFromPropertyFile("username"));
		String PASSWROD =System.getProperty("password", fLib.getDataFromPropertyFile("password"));
		LoginPage login = new LoginPage(driver);
		login.LoginToApp(URL, USERNAME, PASSWROD);

	}

	@AfterMethod(groups = { "smokeTest", "RegressionTest" })
	public void configAM() throws Exception {
		System.out.println("===logout===");
		HomePage home = new HomePage(driver);
		home.signout();
	}

	@AfterClass(groups = { "smokeTest", "RegressionTest" })
	public void configAC() {
		System.out.println("===close Browser===");
		driver.close();
	}

	@AfterSuite(groups = { "smokeTest", "RegressionTest" })
	public void configAS() throws SQLException {
		System.out.println("===disconnect DB and Report===");
		dLib.closeDbConnection();
		
	}

}
