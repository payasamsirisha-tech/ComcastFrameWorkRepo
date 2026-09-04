package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws Throwable, IOException {
		FileInputStream efis=new FileInputStream("./testdata/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(efis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		return data;

	}
	public int getRowCount(String sheetName) throws Exception {
		FileInputStream efis=new FileInputStream("./testdata/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(efis);
		int rowCount= wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}

	public void setDataInttoExel(String sheetName,int rowNum,int celNum,String data) throws Exception, IOException {
		FileInputStream efis=new FileInputStream("./testdata/testdata.xlsx");
		Workbook wb=WorkbookFactory.create(efis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celNum).setCellValue(data);

		FileOutputStream efos=new FileOutputStream("./testdata/testdata.xlsx");
		wb.write(efos);
		wb.close();



	}

}
