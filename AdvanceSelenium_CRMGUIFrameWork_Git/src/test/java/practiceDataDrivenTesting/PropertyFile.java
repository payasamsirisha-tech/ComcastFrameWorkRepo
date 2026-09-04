package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;



public class PropertyFile {

	public static void main(String[] args) throws IOException {
		//1. get the java representation object of the physical file 
		FileInputStream fis=new FileInputStream("C:\\Users\\USER\\OneDrive\\Documents\\commondata.properties");

		//2. using property class , and load all the keys
		Properties prop=new Properties();
		prop.load(fis);
		prop.put("colleg", "raght");
		//3. get the value based on key
		System.out.println(prop.getProperty("browser"));
		 prop.setProperty("name", "siri");
		FileOutputStream fos=new FileOutputStream("C:\\Users\\USER\\OneDrive\\Documents\\commondata.properties");
	
     
     prop.store(fos, "hii");
	}

}
