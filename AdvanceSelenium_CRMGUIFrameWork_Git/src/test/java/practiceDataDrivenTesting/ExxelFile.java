package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExxelFile {

	public static void main(String[] args) throws Throwable, IOException {
//		Step1: get the excel path location and java object of the physical excelFile
		FileInputStream fis=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
//		Step2: Open the workbook read mode 
		Workbook wb= WorkbookFactory.create(fis);
		
//		Step3: get the control of the ‘org’ sheet 
		 String data=wb.getSheet("org").getRow(1).getCell(2).getStringCellValue();
		 System.out.println(data);

		 String data2=wb.getSheet("org").getRow(1).getCell(3).toString();
		// Double data2=wb.getSheet("org").getRow(1).getCell(3).getNumericCellValue();
		 System.out.println(data2);
		
//		Step6: close thee
		wb.close();
	}

}
