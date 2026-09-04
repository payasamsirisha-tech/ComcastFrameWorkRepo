package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateOrgPage {
	@FindBy(name = "accountname")
	private WebElement orgnameEdt;

	@FindBy(id = "phone")
	private WebElement phnnumEdt;

	@FindBy(name = "industry")
	private WebElement IndustryDropDown;

	@FindBy(name="accounttype")
	private WebElement TypeDropDown;

	@FindBy( xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	public CreateOrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgname() {
		return orgnameEdt;
	}

	public WebElement getPhnnum() {
		return phnnumEdt;
	}

	public WebElement getIndustryDropDown() {
		return IndustryDropDown;
	}

	public WebElement getTypeDropDown() {
		return TypeDropDown;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

	public void createorg(String orgname) {
		orgnameEdt.sendKeys(orgname);
		savebtn.click();
	}

	public void createorg(String orgname,String phnnum) {
		orgnameEdt.sendKeys(orgname);
		phnnumEdt.sendKeys(phnnum);
		savebtn.click();
	}
	

	public void createorg(String orgname,String Industry,String Type) {
		orgnameEdt.sendKeys(orgname);
		Select sel=new Select(IndustryDropDown);
		sel.selectByVisibleText(Industry);

		Select sel2=new Select(TypeDropDown);
		sel2.selectByVisibleText(Type);
		savebtn.click();
	}


}
