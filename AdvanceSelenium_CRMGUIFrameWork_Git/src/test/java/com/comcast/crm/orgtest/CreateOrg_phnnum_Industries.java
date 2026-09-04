package com.comcast.crm.orgtest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepo.CreateOrgPage;
import com.comcast.crm.objectrepo.HomePage;
import com.comcast.crm.objectrepo.LoginPage;
import com.comcast.crm.objectrepo.OrgInfoPage;
import com.comcast.crm.objectrepo.OrgPage;
@Listeners(com.comcarst.crm.listnerutility.ListnerImpClass.class)
//listner give in suite level

public class CreateOrg_phnnum_Industries extends BaseClass {
	
	@Test(groups="smokeTest")
	public void createOrg() throws IOException, Throwable {

		String orgname = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
//      click on org link
		HomePage home = new HomePage(driver);
		home.getOrgLink().click();

		// click on orgplus icon
		OrgPage org = new OrgPage(driver);
		org.getorgpusicon().click();

		// create org with name
		CreateOrgPage createorg = new CreateOrgPage(driver);
		createorg.createorg(orgname);

		// verify the header info
		OrgInfoPage orginfo = new OrgInfoPage(driver);
		String headerinfo = orginfo.getOrgHeaderMsg().getText();
		boolean head=headerinfo.contains(orgname);
		Assert.assertEquals(head, true);
		Reporter.log(orgname+" is created successfully header",true);

//		if (headerinfo.contains(orgname)) {
//			System.out.println(orgname + " is created sucessfully");
//		} else
//			System.out.println(orgname + " not created");

		// verify with name
		String actorgName = orginfo.getOrgnameinfomsg().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actorgName, orgname);
		soft.assertAll();
		Reporter.log(orgname+" is created successfully",true);
		
//		if (actorgName.equals(orgname)) {
//			System.out.println(orgname + " is created sucessfully====>PASS");
//		} else {
//			System.out.println(orgname + " not created");
//		}

	}

	@Test(groups="RegressionTest")
	public void orgWithPhnnum() throws IOException, Throwable {
		String orgname = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phnnum = eLib.getDataFromExcel("org", 7, 3);

//       click on org link
		HomePage home = new HomePage(driver);
		home.getOrgLink().click();

		// click on orgplus icon
		OrgPage org = new OrgPage(driver);
		org.getorgpusicon().click();

		// create org with phnnum
		CreateOrgPage createorg = new CreateOrgPage(driver);
		createorg.createorg(orgname, phnnum);

		// verify the header info
		OrgInfoPage orginfo = new OrgInfoPage(driver);
		String headerinfo = orginfo.getOrgHeaderMsg().getText();
		boolean head=headerinfo.contains(orgname);
		Assert.assertEquals(head, true);
		Reporter.log(orgname+" is created successfully header",true);
//		if (headerinfo.contains(orgname)) {
//			System.out.println(orgname + " is created sucessfully");
//		} else
//			System.out.println(orgname + " not created");

		// verify with name
		String actorgName = orginfo.getOrgnameinfomsg().getText();
	SoftAssert soft=new SoftAssert();
	soft.assertEquals(actorgName, orgname);
	Reporter.log(orgname+" is created successfully",true);
//		if (actorgName.equals(orgname)) {
//			System.out.println(orgname + " is created sucessfully====>PASS");
//		} else {
//			System.out.println(orgname + " not created");
//		}
	

		// verify with phn
		String actphn = orginfo.getPhnnuminfomsg().getText();
		soft.assertEquals(actphn, phnnum);
		soft.assertAll();
		Reporter.log(phnnum+" is created successfully",true);
//		if (actphn.equals(phnnum)) {
//			System.out.println(phnnum + " is created sucessfully====>PASS");
//		} else {
//			System.out.println(phnnum + " not created");
//		}
	}

	@Test(groups="RegressionTest")
	public void orgWithIndustries() throws IOException, Throwable {
		String orgname = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String Industryname = eLib.getDataFromExcel("org", 4, 3);
		String Type = eLib.getDataFromExcel("org", 4, 4);

		// click on org link
		HomePage home = new HomePage(driver);
		home.getOrgLink().click();

		// click on org plus icon
		OrgPage org = new OrgPage(driver);
		org.getorgpusicon().click();

		// create org with industry and type
		CreateOrgPage createorg = new CreateOrgPage(driver);
		createorg.createorg(orgname, Industryname, Type);

		// verify the header info
		OrgInfoPage orginfo = new OrgInfoPage(driver);
		String headerinfo = orginfo.getOrgHeaderMsg().getText();
		boolean head=headerinfo.contains(orgname);
		Assert.assertEquals(head, true);
		Reporter.log(orgname+" is created successfully header",true);
//		if (headerinfo.contains(orgname)) {
//			System.out.println(orgname + " is created sucessfully");
//		} else
//			System.out.println(orgname + " not created");

		// verify with name
		String actorgName = orginfo.getOrgnameinfomsg().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actorgName, orgname);
		Reporter.log(orgname+" is created successfully",true);
//		if (actorgName.equals(orgname)) {
//			System.out.println(orgname + " is created sucessfully====>PASS");
//		} else
//			System.out.println(orgname + " not created");

		// verify with industry;
		String expindustry = orginfo.getIndustryinfomsg().getText();
	
		soft.assertEquals(expindustry, Industryname);
		Reporter.log(Industryname+" is created successfully",true);
//		if (expindustry.equals(Industryname)) {
//			System.out.println(Industryname + " is verified sucessfully");
//		} else
//			System.out.println(Industryname + " not verified");

		// verify with type
		String exptype = orginfo.getTypeinfomsg().getText();
		soft.assertEquals(exptype, Type);
		Reporter.log(Type+" is created successfully",true);
		soft.assertAll();

//		if (exptype.equals(Type)) {
//			System.out.println(Type + " is verified sucessfully");
//		} else
//			System.out.println(Type + " not verified");

	}

}
