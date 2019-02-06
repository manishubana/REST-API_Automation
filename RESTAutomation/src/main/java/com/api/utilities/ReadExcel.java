package com.api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	private FileInputStream file;
	private XSSFWorkbook workbook;
	public XSSFSheet sheet;
	private int rowLen;
	private int colLen;
	private Row row;
	private Cell cell;
	private Map<String, Integer> rowIndexMapper;
	private Map<String, Integer> colIndexMapper;

	public void createWorkBookAndSheet(int workSheetIndex){

		try {
			file = new FileInputStream(new File(System.getProperty("user.dir")+"//src//main//java//com//api//config//API_Datapool.xlsx"));
			workbook = new XSSFWorkbook(file); // Create Workbook instance holding reference to .xlsx file 
			sheet = workbook.getSheetAt(workSheetIndex); // Get first/desired sheet from the workbook
			indexingRowsAndColumns(); //create indexes for Row Names and Column Names

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}


	public void indexingRowsAndColumns(){

		row = sheet.getRow(0);
		colLen=row.getLastCellNum(); 
		rowLen=sheet.getLastRowNum();
		colIndexMapper = new HashMap<String, Integer>();
		rowIndexMapper = new HashMap<String, Integer>();

		for(int i=1;i<colLen;i++)
			colIndexMapper.put(sheet.getRow(0).getCell(i).getStringCellValue(),i);
		//System.out.println("Col mapper->"+colIndexMapper);
		for(int i=1;i<rowLen+1;i++)
			rowIndexMapper.put(sheet.getRow(i).getCell(0).getStringCellValue(),i);
		//System.out.println("Row mapper->"+rowIndexMapper);
	}

	public String getCellValue(String rowName, String colName){
		//System.out.println("Row->"+rowIndexMapper.get(rowName));
		//System.out.println("Col->"+colIndexMapper.get(colName));
		cell=sheet.getRow(rowIndexMapper.get(rowName)).getCell(colIndexMapper.get(colName));
		if(cell.getCellType()==CellType.STRING)
			return cell.getStringCellValue();
		else if(cell.getCellType()==CellType.NUMERIC)
			return String.valueOf(Math.round(cell.getNumericCellValue())); //Math.round will convert the double to whole number Integer
		else
			return "Value Not Found"; //create a custom exception
	}

}
