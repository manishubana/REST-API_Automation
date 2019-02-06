package com.api.script;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.*;
import com.relevantcodes.extentreports.LogStatus;

public class Script1 extends TestBase{

	@BeforeClass
	public void beforeClass(){
		createExcelWorkBookAndStartExtentTest(0, "REST-Test_01"); // provide excel sheet index number and extent test name
	}
	
	@Test(enabled=true) //query parameter
	public void s1_t1(){

		URI=excel.getCellValue("t1", "ServiceURL");
		extentTest.log(LogStatus.INFO, "s1_t1");
		/*given().param("page", excel.getCellValue("t1", "QueryParam")).
		when().get(URI).
		then().assertThat().statusCode(200).
		and().body("data[0].first_name",is("Eve")).
		and().body("data[0].id",is(4));
		
		extentTest.log(LogStatus.INFO, "");*/
	}

	@Test(enabled=true) //path parameter
	public void s1_t2(){ 

		URI=URI+excel.getCellValue("t2", "ServiceURL");
		extentTest.log(LogStatus.INFO, "s1_t2");
		/*given().pathParam("users", excel.getCellValue("t2", "PathParam")).
		when().get(URI).
		then().assertThat().statusCode(200).
		and().body("data.first_name", is("Janet"));*/
	}

}
