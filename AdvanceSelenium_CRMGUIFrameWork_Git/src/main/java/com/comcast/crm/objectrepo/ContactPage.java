package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	
	@FindBy(xpath = "//img[contains(@title,'Create Contact')]")
	private WebElement contactpusicon;

	@FindBy(name = "search_text")
	private WebElement searchEdt;

	@FindBy(name = "search_field")
	private WebElement searchdropdown;

	@FindBy(name= "submit")
	private WebElement searchtn;

	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getContactpusicon() {
		return contactpusicon;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchdropdown() {
		return searchdropdown;
	}

	public WebElement getSearchtn() {
		return searchtn;
	}
	
	


}
