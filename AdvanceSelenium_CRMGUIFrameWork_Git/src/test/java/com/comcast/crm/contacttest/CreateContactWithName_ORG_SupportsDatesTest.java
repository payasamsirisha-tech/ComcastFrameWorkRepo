package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcarst.crm.listnerutility.ListnerImpClass;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.basetest.UtilityClassObject;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepo.ContactPage;
import com.comcast.crm.objectrepo.ContactinfoPage;
import com.comcast.crm.objectrepo.CreateContactPage;
import com.comcast.crm.objectrepo.CreateOrgPage;
import com.comcast.crm.objectrepo.HomePage;
import com.comcast.crm.objectrepo.LoginPage;
import com.comcast.crm.objectrepo.OrgInfoPage;
import com.comcast.crm.objectrepo.OrgPage;

@Listeners(com.comcarst.crm.listnerutility.ListnerImpClass.class)
//listner give in suite level
/**
 * @author lakshmi
 */
public class CreateContactWithName_ORG_SupportsDatesTest extends BaseClass {

	@Test(groups = "smokeTest")
	
	public void createContact() throws IOException, Throwable {
		// ListnerImpClass.test.log(Status.INFO, "read the date from excel");
		UtilityClassObject.getTest().log(Status.INFO, "read the date from excel");

		String lastname = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		/* click on contactLink */

		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		HomePage home = new HomePage(driver);
		home.getContactLink().click();

		// click on contact plus icon
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		ContactPage contact = new ContactPage(driver);
		contact.getContactpusicon().click();

		// create contact with last name
		UtilityClassObject.getTest().log(Status.INFO, "crate contact");
		CreateContactPage createcontact = new CreateContactPage(driver);
		createcontact.CreateContact(lastname);

		// verify the header info
		ContactinfoPage contactinfo = new ContactinfoPage(driver);

		String actheaderinfo = contactinfo.getContactheaderinfo().getText();
		boolean status = actheaderinfo.contains(lastname);
		Assert.assertEquals(status, true);
		UtilityClassObject.getTest().log(Status.INFO, lastname + "crate header");

//		if (headerinfo.contains(lastname)) {
//			System.out.println(lastname + " is created sucessfully");
//		} else
//			System.out.println(lastname + " not created");

		// verify with name
		String actorgName = contactinfo.getContactnameinfo().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actorgName, lastname);
		soft.assertAll();
		Reporter.log(lastname + " is created", true);
//		if (actorgName.equals(lastname)) {
//			System.out.println(lastname + " is created sucessfully====>PASS");
//		} else {
//			System.out.println(lastname + " not created");
//		}

	}

	@Test(groups = "RegressionTest")
	public void ContactWithSupportDates() throws IOException, Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "read the date from excel");

		String lastname = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		HomePage home = new HomePage(driver);
		home.getContactLink().click();
		// driver.findElement(By.linkText("Contacts")).click();

		ContactPage contact = new ContactPage(driver);
		contact.getContactpusicon().click();

		String startdate = jLib.getSystemDateyyyyMMDD();
		String enddate = jLib.getRequiredDateyyyyMMdd(30);

		// create contact wth name startdate and end date
		CreateContactPage createcontact = new CreateContactPage(driver);
		createcontact.CreateContact(lastname, startdate, enddate);

		// verify with startdate
		ContactinfoPage contactinfo = new ContactinfoPage(driver);
		String actstartdate = contactinfo.getStartdateinfomsg().getText();

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actstartdate, startdate);
		Reporter.log(startdate + " is created successfully", true);

//		if (actstartdate.equals(startdate)) {
//			System.out.println(startdate + " is created sucessfully====>PASS");
//		} else {
//			System.out.println(startdate + " not created");
//		}

		// verify with enddate

		String actenddate = contactinfo.getEnddateinfomsg().getText();
		soft.assertEquals(actenddate, enddate);
		soft.assertAll();
		Reporter.log(enddate + " is created successfully", true);
//		if (actenddate.equals(enddate)) {
//			System.out.println(enddate + " is created sucessfully====>PASS");
//		} else {
//			System.out.println(enddate + " not created");
//		}

	}

	@Test(groups = "RegressionTest")
	public void ContactWithOrg() throws IOException, Throwable {

		String orgname = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String lastname = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		// create org click on orglink
		HomePage home = new HomePage(driver);
		home.getOrgLink().click();

		// click on orgplus icon
		OrgPage org = new OrgPage(driver);
		org.getorgpusicon().click();

		// create org with name
		CreateOrgPage createorg = new CreateOrgPage(driver);
		createorg.createorg(orgname);

		OrgInfoPage orginfo = new OrgInfoPage(driver);

		// verify the header info
		String headerinfo = orginfo.getOrgHeaderMsg().getText();
		boolean status = headerinfo.contains(orgname);
		Assert.assertEquals(status, true);
		Reporter.log(orgname + " is created successfully", true);
//		if (headerinfo.contains(orgname)) {
//			System.out.println(orgname + " is created sucessfully====");
//		} else
//			System.out.println(orgname + " not created");

		// create contact
		home.getContactLink().click();

		// //identify the plus icon
		ContactPage contact = new ContactPage(driver);
		contact.getContactpusicon().click();

		// create contact with name and save
		CreateContactPage createcontact = new CreateContactPage(driver);
		createcontact.CreateContactwithorg(lastname);

		String parent = driver.getWindowHandle();
		wLib.switchToChildWindow(driver, "module=Accounts&action");

		driver.findElement(By.id("search_txt")).sendKeys(orgname);
		// click on search
		driver.findElement(By.name("search")).click();

		// select our org name
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		wLib.parentWindow(driver, parent);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify the header info
		ContactinfoPage contatcinfo = new ContactinfoPage(driver);

		String contactheaderinfo = contatcinfo.getContactheaderinfo().getText();
		boolean contactstatus = contactheaderinfo.contains(lastname);
		Assert.assertEquals(contactstatus, true);
		Reporter.log(lastname + " is created successfully", true);
//		if (contactheaderinfo.contains(lastname)) {
//			System.out.println(lastname + " is created sucessfully");
//		} else
//			System.out.println(lastname + " not created");

		// verify with orgname
		String actorgName = contatcinfo.getContactorgnameinfo().getText();
		boolean actorginfo = actorgName.trim().equals(orgname);
		Assert.assertEquals(actorginfo, true);
		Reporter.log(orgname + " is created successfully", true);
//		if (actorgName.trim().equals(orgname)) {
//			System.out.println(orgname + " is created sucessfully====>PASS");
//		} else {
//			System.out.println(orgname + " not created");
//		}

	}

}
