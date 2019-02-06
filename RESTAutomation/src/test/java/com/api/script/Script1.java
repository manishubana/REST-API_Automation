package com.api.script;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

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
	public void s1_listUsers(){

		String URI=baseURL+excel.getCellValue("t1", "ServiceURL");
		//System.out.println("URI->"+URI);

		given().param("page", excel.getCellValue("t1", "QueryParam")).
		when().get(URI).
		then().assertThat().statusCode(200).
		and().body("data.first_name[0]",is("Eve")).
		and().body("data.id[0]",is(4));
	}

	/*@Test(enabled=true) //path parameter
	public void s1_singleUser(){ 

		String URI=baseURL+excel.getCellValue("t2", "ServiceURL");

		given().pathParam("user", excel.getCellValue("t2", "PathParam")).
		when().get(URI).
		then().assertThat().statusCode(200).
		and().body("data.first_name", is("Janet"));
	}*/

	@Test(enabled=true) //path parameter
	public void s1_singleUser(){ 

		String URI=baseURL+excel.getCellValue("t2", "ServiceURL");

		Response response = given().pathParam("user", excel.getCellValue("t2", "PathParam")).when().get(URI);
		
		System.out.println(verifyStatus(response, 200));	
		
		
		/*then().assertThat().statusCode(200).
		and().body("data.first_name", is("Janet"));*/
	}

}
