package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadData_BasedOnCondition {

	public static void main(String[] args) throws Exception, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("org");
		
		String expTestID="tc_100";
		String data1="";
		String data2="";
		String data3="";
		boolean flag=false;
	int rowcount = sh.getLastRowNum();
	
	for(int i=0;i<=rowcount;i++) {
		String data="";
		try {  //we have empty cells so nullpointer exception will come so giving try and catch block
		data=sh.getRow(i).getCell(0).toString();
		if(data.equals(expTestID)) {
			flag=true;
			data1=sh.getRow(i).getCell(1).toString();
			data2=sh.getRow(i).getCell(2).toString();
			data3=sh.getRow(i).getCell(3).toString();
		   }
		}
			
		catch(Exception e) {}
	}
	if(flag==true) {
	System.out.println(data1);
	System.out.println(data2);
	System.out.println(data3);
	}
	else {
		System.out.println(expTestID+ " is not avaliable");
	}
		
	}

  }

