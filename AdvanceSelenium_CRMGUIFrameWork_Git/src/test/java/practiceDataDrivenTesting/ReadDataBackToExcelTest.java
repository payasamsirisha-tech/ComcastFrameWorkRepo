package practiceDataDrivenTesting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBackToExcelTest {

	public static void main(String[] args) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("org");
		Row row=sh.getRow(1);
		Cell cel=row.createCell(4);
	//	cel.setCellType(CellType.STRING);
		cel.setCellValue("Pass");
//		Row row1=sh.getRow(4);
//		Cell cel1=row1.createCell(4);
//		cel1.setCellValue("fail");
//		Row row2=sh.getRow(7);
//		Cell cel3=row2.createCell(4);
//		cel3.setCellValue("pass");
		FileOutputStream fos=new FileOutputStream("C:\\Users\\USER\\OneDrive\\Desktop\\Excel\\testdata.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("executed");
	}

}
