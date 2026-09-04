package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPage {
	WebDriver driver;
	public OrgInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement orgHeaderMsg;
	
	@FindBy(xpath="//span[@id='dtlview_Phone']")
	private WebElement phnnuminfomsg;
	
	@FindBy(xpath="//span[@id='dtlview_Industry']/font")
	private WebElement industryinfomsg;
	
	@FindBy(xpath="//span[@id='dtlview_Type']/font")
	private WebElement typeinfomsg;
	
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgnameinfomsg;
	public WebDriver getDriver() {
		return driver;
	}


	public WebElement getOrgHeaderMsg() {
		return orgHeaderMsg;
	}


	public WebElement getPhnnuminfomsg() {
		return phnnuminfomsg;
	}


	public WebElement getIndustryinfomsg() {
		return industryinfomsg;
	}


	public WebElement getTypeinfomsg() {
		return typeinfomsg;
	}


	public WebElement getOrgnameinfomsg() {
		return orgnameinfomsg;
	}
	

	
	
	
	

}
