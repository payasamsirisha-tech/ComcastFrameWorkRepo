package practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;


//@Listeners(com.comcarst.crm.listnerutility.ListnerImpClass.class)
//we can give in suite level also
public class Listener extends BaseClass {
	@Test(retryAnalyzer = com.comcarst.crm.listnerutility.RetryListnerImp.class)
	public void createInvoiceTest() {
		System.out.println("execute create invoice test");
		String acttitle = driver.getTitle(); 
		Assert.assertEquals(acttitle, "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

//	@Test
//	public void createContactTest() {
//
//		System.out.println("execute create contact test");
//
//		System.out.println("Step-1");
//		System.out.println("Step-2");
//		System.out.println("Step-3");
//		System.out.println("Step-4");
//	}

}
