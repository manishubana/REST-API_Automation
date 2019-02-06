package com.api.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.utilities.ReadExcel;

public class ExcelReadTest {

	public static void main(String[] args) {

		
		ReadExcel excel = new ReadExcel();

		//excel.createWorkBookAndGetSheet(0);
		
		System.out.println(excel.getCellValue("t2", "PathParam"));
		

	}

}
