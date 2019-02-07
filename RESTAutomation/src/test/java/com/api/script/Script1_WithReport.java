package com.api.script;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.utilities.RESTUtil;
import com.relevantcodes.extentreports.LogStatus;

import static org.hamcrest.Matchers.*;

public class Script1_WithReport extends TestBase{
	
	RESTUtil apiUtil;
	
	@BeforeClass
	public void beforeClass(){
		apiUtil=new RESTUtil();
		createExcelWorkBookAndStartExtentTest(0, "REST-Test_01"); // provide excel sheet index number and extent test name
		report.setDescription("Testing REST-Test_01");
	}
	
	@Test //path parameter
	public void s1_singleUser_verifyStatusCodeIs200(){ 
		
		String URI=baseURL+excel.getCellValue("t2", "ServiceURL");
		Response response = given().pathParam("user", excel.getCellValue("t2", "PathParam")).when().get(URI);
		
		if(apiUtil.verifyStatusCode(response, 200))
			report.log(LogStatus.PASS, "Status is 200 for singleUser Test");
		else
			report.log(LogStatus.FAIL, "Status is not 200 for singleUser Test");
	}
	
	@Test //path parameter
	public void s1_singleUser_verifyFirstNameInResponse(){ 
		
		String URI=baseURL+excel.getCellValue("t2", "ServiceURL");
		Response response = given().pathParam("user", excel.getCellValue("t2", "PathParam")).when().get(URI);
		
		if(apiUtil.verifyBody(response, "data.first_name", "Janet"))
			report.log(LogStatus.PASS, "FirstName is as expected for singleUser Test");
		else
			report.log(LogStatus.FAIL, "FirstName is not as expected for singleUser Test");
	}
	
	@Test
	public void s1_listUsers_verifyResponseContents(){
		
		String URI=baseURL+excel.getCellValue("t1", "ServiceURL");
		Response response = given().param("page", excel.getCellValue("t1", "QueryParam")).when().get(URI);
		
		if(apiUtil.verifyBody(response, "data.first_name[0]", "Eve"))
			report.log(LogStatus.PASS, "First Name at 0th index is as expected for listUsers Test");
		else
			report.log(LogStatus.FAIL, "First Name at 0th index is not as expected for listUsers Test");
		
		if(apiUtil.verifyBody(response, "data.id[0]", "4"))
			report.log(LogStatus.PASS, "Id at 0th index is as expected for listUsers Test");
		else
			report.log(LogStatus.FAIL, "Id at 0th index is not as expected for listUsers Test");
	}
	
	@Test
	public void s1_demo(){
		
		
		
		
	}
}
