package practiceBasecalss;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClassPractie {
	
	@BeforeSuite
	public void configBS() {
		System.out.println("===connect DB and report config");
	}

	@BeforeClass
	public void configBC() {
		System.out.println("===launch browser");
	}
	@BeforeMethod
	public void configBM() {
		System.out.println("loging");
	}

	
	
	@AfterMethod
	public void configAM() {
		System.out.println("logout");
	}
	@AfterClass
	public void configAC() {
		System.out.println("===close Browser");
	}
	@AfterSuite
	public void configAS() {
		System.out.println("===disconnect DB and Report");
	}

}
