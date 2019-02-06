package com.api.script;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.base.TestBase;
import static org.hamcrest.Matchers.*;
import com.relevantcodes.extentreports.LogStatus;
public class Script2 extends TestBase{


	@BeforeClass
	public void beforeClass(){
		createExcelWorkBookAndStartExtentTest(1, "REST-Test_02"); // provide excel sheet index number and extent test name
	}

	@Test
	public void s2_single_user_not_found(){

		String URI=baseURL+excel.getCellValue("t1", "ServiceURL");
		extentTest.log(LogStatus.INFO, "s2_t1");
		given().pathParam("users", excel.getCellValue("t1", "PathParam")).
		when().get(baseURL).
		then().assertThat().statusCode(200);

	}

	@Test(enabled=false)
	public void s2_create() {

		String URI=baseURL+excel.getCellValue("t2", "ServiceURL");
		extentTest.log(LogStatus.INFO, "s2_t2");
		//EmployeeDetails details = new EmployeeDetails();
		//details.setName("Maximus");
		//details.setJob("employee");

		Response response =  given().contentType(ContentType.JSON).formParam("name", "Maximus","job","employee").
		when().post();
		
		response.then().assertThat().statusCode(201);
		
		System.out.println(response.getBody().asString());

	}
	 

}
