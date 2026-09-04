package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBactToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
//		Step2: Open the workbook read mode 
		Workbook wb= WorkbookFactory.create(fis);
		
//		Step3: get the control of the ‘org’ sheet 
		 Sheet sh=wb.getSheet("org");
		 Row r=sh.getRow(0);

	}

}
