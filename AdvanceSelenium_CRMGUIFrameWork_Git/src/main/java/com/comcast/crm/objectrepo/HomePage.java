package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	@FindBy(linkText = "Organizations")
	private WebElement OrgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminbtn;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutlink;

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this. driver=driver;
		PageFactory.initElements(driver, this);
	}


	public WebElement getOrgLink() {
		return OrgLink;
	}


	public WebElement getContactLink() {
		return contactLink;
	}
	public WebElement getadminbtn() {
		return adminbtn;

	}


	public WebElement getSignoutlink() {
		return signoutlink;
	}

	public void signout() throws Exception {
		Actions act=new Actions(driver);
		Thread.sleep(1000);
		act.moveToElement(adminbtn).perform();
		Thread.sleep(1000);
		signoutlink.click();

	}

}
