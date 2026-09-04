package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadyMultipleDataFromExcel {

	public static void main(String[] args) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
//		Step2: Open the workbook read mode 
		Workbook wb= WorkbookFactory.create(fis);
		
//		Step3: get the control of the ‘org’ sheet 
		 Sheet sh=wb.getSheet("Sheet2");
	int rowcount=sh.getLastRowNum();
		for(int i=1;i<=rowcount;i++) {
		//for(int i=1;i<50;i++) {
			Row row=sh.getRow(i);
		String column1=row.getCell(0).toString();
		String column2=row.getCell(1).toString();
		
		System.out.println(column1 + "\t" + column2);
		}
       wb.close();

	}

}
