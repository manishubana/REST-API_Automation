package com.api.base;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.api.utilities.RESTUtil;
import com.api.utilities.ReadExcel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestBase{

	public Properties config;
	public static ExtentTest report;
	private static ExtentReports extentReports;
	private static String extentReportFile;
	public String baseURL;
	public ReadExcel excel;

	@BeforeSuite
	public void init(){

		config = new Properties(); //loading config.properties file
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//com//api//config//config.properties");
			config.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		baseURL=config.getProperty("baseURL"); //fetch baseURL
		if(config.getProperty("isProxyEnabled").equals("true")) //setting proxy
			RestAssured.proxy(config.getProperty("proxyURL"), Integer.parseInt(config.getProperty("proxyPort")));
		//initializing Extent Reports
		extentReportFile=System.getProperty("user.dir")+config.getProperty("extentReportPath")+config.getProperty("projectNameAppender")+"_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".html";
		extentReports =  new ExtentReports(extentReportFile);

	}

	public void createExcelWorkBookAndStartExtentTest(int excelSheetIndex, String extentTestName){
		excel = new ReadExcel();
		excel.createWorkBookAndSheet(excelSheetIndex);
		report=extentReports.startTest(extentTestName);
	}
	
	public void methodInfo(){ //method to return the method name of the respective @Test
		report.log(LogStatus.INFO, new Throwable().getStackTrace()[2].getMethodName());
	}
	
	@AfterSuite
	public void tearDown_suite(){
		extentReports.flush(); // writing report
	}
	
	@AfterClass
	public void tearDown_class() throws IOException{
		report.log(LogStatus.INFO, "Script Ends");
		extentReports.endTest(report); // close extent report
	}
}
