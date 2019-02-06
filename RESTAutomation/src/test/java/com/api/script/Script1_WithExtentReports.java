package com.api.script;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.utilities.ReadExcel;

import static org.hamcrest.Matchers.*;

public class Script1_WithExtentReports extends TestBase{

	ReadExcel excel;

	@BeforeClass
	public void beforeClass(){
		excel = new ReadExcel();
		excel.createWorkBookAndSheet(0);
	}


	@Test //query parameter
	public void t1(){

		String URI=config.getProperty("baseURL")+excel.getCellValue("t1", "ServiceURL");
		
		Response response=given().param("page", excel.getCellValue("t1", "QueryParam")).when().get(URI);
		
		if(response.getStatusCode()==200)
			System.out.println();
		else
			System.out.println();
		
		
	}
	
	@Test //path parameter
	public void t2(){ 

		String URI=config.getProperty("baseURL")+excel.getCellValue("t2", "ServiceURL");

		given().pathParam("users", excel.getCellValue("t2", "PathParam")).
		when().get(URI).
		then().assertThat().statusCode(200);
	}

}
