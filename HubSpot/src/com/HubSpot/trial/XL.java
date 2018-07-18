package com.HubSpot.trial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XL {

	
	
	
	public static String getCellValue(String xlFile, int sheetId, int rowId, int cellId) {
		String cellValue=null;
		
		try {
			FileInputStream fis=new FileInputStream(xlFile);
			
			XSSFWorkbook book=new XSSFWorkbook(fis);
			XSSFSheet sheet=book.getSheetAt(sheetId);
			XSSFRow row=sheet.getRow(rowId);
			XSSFCell cell=row.getCell(cellId);
			cellValue=cell.getStringCellValue();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return cellValue;
	}
	
	
	
}
