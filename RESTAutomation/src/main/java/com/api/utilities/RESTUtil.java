package com.api.utilities;

import com.api.base.TestBase;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class RESTUtil {

	TestBase base = new  TestBase();
	
	public boolean verifyStatusCode(Response response, int expectedStatusCode){
		base.methodInfo(); //adding method name information to Report Logs
		if(response.getStatusCode()==expectedStatusCode)
			return true;
		else
			return false;
	}

	public boolean verifyBody(Response response, String path, String value){
		base.methodInfo(); //adding method name information to Report Logs
		if(response.jsonPath().get(path).toString().equalsIgnoreCase(value))
			return true;
		else
			return false;
	}


}
