package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactinfoPage {
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startdateinfomsg;
	
	@FindBy(id="dtlview_Support End Dates")
	private WebElement enddateinfomsg;
	
	@FindBy(className ="dvHeaderText")
	private WebElement contactheaderinfo;
	
	@FindBy(id ="dtlview_Last Name")
	private WebElement contactnameinfo;
	
	@FindBy(id ="mouseArea_Organization Name")
	private WebElement contactorgnameinfo;
	
	public ContactinfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getStartdateinfomsg() {
		return startdateinfomsg;
	}

	public WebElement getEnddateinfomsg() {
		return enddateinfomsg;
	}

	public WebElement getContactheaderinfo() {
		return contactheaderinfo;
	}

	public WebElement getContactnameinfo() {
		return contactnameinfo;
	}

	public WebElement getContactorgnameinfo() {
		return contactorgnameinfo;
	}
	

}
