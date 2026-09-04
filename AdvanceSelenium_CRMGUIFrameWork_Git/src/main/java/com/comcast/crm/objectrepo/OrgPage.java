
package com.comcast.crm.objectrepo;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPage {
	@FindBy(xpath = "//img[contains(@title,'Create Organization')]")
	private WebElement orgpusicon;

	@FindBy(name = "search_text")
	private WebElement searchEdt;

	@FindBy(name = "search_field")
	private WebElement searchdropdown;

	@FindBy(name= "submit")
	private WebElement searchtn;

	public OrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getorgpusicon() {
		return orgpusicon;
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
