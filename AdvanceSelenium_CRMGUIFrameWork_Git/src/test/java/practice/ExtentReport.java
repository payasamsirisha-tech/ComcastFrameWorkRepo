package practice;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcarst.crm.listnerutility.ListnerImpClass;
import com.comcast.crm.basetest.BaseClass;

public class ExtentReport extends ListnerImpClass {
	@Test
	public void createCOntact() {
		WebDriver  driver=new ChromeDriver();
		driver.get("http://localhost:8080/");
//		TakesScreenshot ts=(TakesScreenshot)driver;
//		String filepath=ts.getScreenshotAs(OutputType.BASE64);
		
		
		 test = report.createTest("CreateContactTest");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HEFC")) {
			test.log(Status.PASS, "contact is crated ==pass");
		} else {
			//test.addScreenCaptureFromBase64String(filepath, "error");
			
		}
driver.close();
		System.out.println("login to app");
	}

//	@Test
//	public void createorg() {
//
//		ExtentTest test = report.createTest("CreateorgTest");
//
//		test.log(Status.INFO, "login to app");
//		test.log(Status.INFO, "navigate to org page");
//		test.log(Status.INFO, "create org");
//		if ("HDFC".equals("HDFC")) {
//			test.log(Status.PASS, "org is crated ==pass");
//		} else {
//			test.log(Status.FAIL, "not crated");
//		}
//
//		System.out.println("login to app");
//	}
}
