package com.comcarst.crm.listnerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.UtilityClassObject;

public class ListnerImpClass implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public  ExtentReports report; // need in every test case so use static
	public  ExtentTest test;  //if it is sattic this can't invole in pparallel

	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		  spark.config().setTheme(Theme.DARK);
		

		// add environment and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "window 10");
		report.setSystemInfo("Browser", "chrome-10");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();

	}

	public void onTestStart(ITestResult result) {
		// method name
		System.out.println("=====" + result.getMethod().getMethodName() + "===execution started======");
		test = report.createTest(result.getMethod().getMethodName());
		//test is static then only we can call in another class but it wn't paticepate in parallel so given like this
		
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() +" strated");
	
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("=====" + result.getMethod().getMethodName() + "===execution completed======");
		test.log(Status.PASS, result.getMethod().getMethodName() +" ===>completed");
		}

	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		//TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		WebDriver driver=UtilityClassObject.getDriver();
		if(driver!=null)
		{
	 try {
		TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getDriver();

		String src = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		// File des = new File("./screenshot/" + testname+ "-"+ time+".png");
//		try {
//			FileHandler.copy(src, des);
//		} catch (IOException e) {
//           e.printStackTrace();
//		}

		test.addScreenCaptureFromBase64String(src, testname+"_"+time);
		}
		catch(Exception e)
		{
			System.out.println("ScreenShot Skipped");
		}
		}
		test.log(Status.FAIL, result.getMethod().getMethodName() +" ==>fail");

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailureButWithinSuccessPercentage(ITestResult Result) {

	}

	
}
