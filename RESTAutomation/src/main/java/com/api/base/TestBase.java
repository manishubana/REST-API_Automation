package com.api.base;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.api.utilities.ReadExcel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestBase {
	
	public XSSFSheet sheet;
	public Properties config;
	public ExtentTest extentTest;
	public ExtentReports extentReports;
	public String extentReportFile;
	public String URI;
	public ReadExcel excel;
	
	@BeforeSuite
	public void init(){
		
		config = new Properties(); //loading config.properties file
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//api//config//config.properties");
			config.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		URI=config.getProperty("baseURL"); //fetch baseURL
		if(config.getProperty("isProxyApplicable").equals("true")) //setting proxy
			RestAssured.proxy(config.getProperty("proxyURL"), Integer.parseInt(config.getProperty("proxyPort")));

		//initializing Extent Reports
		extentReportFile=System.getProperty("user.dir")+config.getProperty("extentReportPath")+config.getProperty("projectNameAppender")+"_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".html";
//		System.out.println(extentReportFile);
		
	}
	
	public void createExcelWorkBookAndStartExtentTest(int excelSheetIndex, String extentTestName){
		excel = new ReadExcel();
		excel.createWorkBookAndSheet(excelSheetIndex);
		System.out.println("Test Name -"+extentTestName);
		extentReports =  new ExtentReports(extentReportFile);
		extentTest=extentReports.startTest(extentTestName);
		try{
		System.out.println("Extent Test Obj -"+extentTest);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown_suite(){
		extentReports.flush(); // writing report
	}

	@AfterClass
	public void tearDown_class() throws IOException{
		extentTest.log(LogStatus.INFO, "Script Ends");
		extentReports.endTest(extentTest); // close extent report
	}
}
