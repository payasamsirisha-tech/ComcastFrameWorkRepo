package com.comcast.crm.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class products {
	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement productimgbtn;
	
	public products(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getProductimgbtn() {
		return productimgbtn;
	}
	

}
 