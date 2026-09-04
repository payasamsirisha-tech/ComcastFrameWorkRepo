package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
/**
 * 
 * @author lakshmi
 * contain login page elements and & business like login
 */
public class LoginPage extends WebDriverUtility {
	WebDriver driver;
	//declaration
	@FindBy(name = "user_name")
	private WebElement usernameEdt;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdt;
	
	@FindBy(id = "submitButton")
	private WebElement submitbtn;
	
//initialization
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//utilixation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}
     public WebElement getPasswordEdt() {
		return passwordEdt;
	}
   public WebElement getSubmitbtn() {
		return submitbtn;
	}
		
/**
 * login to application based on url,username,password arguments
 * @param url
 * @param username
 * @param password
 */
public void LoginToApp(String url,String username,String password) {
		waitforPageToLoad(driver);
	    driver.manage().window().maximize();
		driver.get(url);
		usernameEdt.sendKeys(username);	
		passwordEdt.sendKeys(password);
		submitbtn.click();
	}


	

}
