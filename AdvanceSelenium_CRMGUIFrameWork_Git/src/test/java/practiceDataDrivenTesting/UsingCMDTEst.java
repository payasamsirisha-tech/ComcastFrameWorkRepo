package practiceDataDrivenTesting;

import org.testng.annotations.Test;

public class UsingCMDTEst {
	@Test
	public void runtimeParameter() {
		
		String url=System.getProperty("url");
		String Browser=System.getProperty("browser");
		String username=System.getProperty("username");
		String password=System.getProperty("pssword");
		System.out.println("ENV data===>url===>"+url);
		System.out.println("ENV data===>browser===>"+Browser);
		System.out.println("ENV data===>usedname===>"+username);
		System.out.println("ENV data===>password===>"+password);
		
	}

}
