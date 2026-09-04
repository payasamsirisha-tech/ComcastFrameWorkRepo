package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	@FindBy(name = "lastname")
	private WebElement lastnameTxtfld;
	
	@FindBy(xpath = "//input[@name='account_id']/following-sibling::img[@title='Select']")
	private WebElement orgplusicon;
	
	@FindBy(name = "support_start_date")
	private WebElement startdateTxtfld;
	
	@FindBy(name = "support_end_date")
	private WebElement enddateTxtfld;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastnameTxtfld() {
		return lastnameTxtfld;
	}

	public WebElement getOrgplusicon() {
		return orgplusicon;
	}

	public WebElement getStartdateTxtfld() {
		return startdateTxtfld;
	}

	public WebElement getEnddateTxtfld() {
		return enddateTxtfld;
	}
	
	public WebElement getSavebtn() {
		return savebtn;
	}

	public void CreateContact(String lastname) {
		lastnameTxtfld.sendKeys(lastname);
		savebtn.click();
		}
	 public void CreateContactwithorg(String lastname) {
		 lastnameTxtfld.sendKeys(lastname);
		 orgplusicon.click();
	 }
	 
	 public void CreateContact(String lastname,String startdate,String enddate) {
		 lastnameTxtfld.sendKeys(lastname);
		 startdateTxtfld.clear();
		 startdateTxtfld.sendKeys(startdate);
		 enddateTxtfld.clear();
		 enddateTxtfld.sendKeys(enddate);
		 savebtn.click();
		 
		 
	 }

}
