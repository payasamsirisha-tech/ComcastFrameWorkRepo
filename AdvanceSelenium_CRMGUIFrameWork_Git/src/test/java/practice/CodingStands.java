package practice;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepo.LoginPage;

public class CodingStands extends BaseClass {
	/**
	 * Scenario: login()==>navigate to contact==create contact ==verify
	 */
	
	@Test
	public void searchCOntact() {
		/*  Step 1. LOgin to Application*/
		LoginPage lp=new LoginPage(driver);
		lp.LoginToApp("url", "admin", "password");
	
	
		
	}

}
