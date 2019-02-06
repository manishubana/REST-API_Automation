package com.api.utilities;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class RESTUtil {


	public boolean verifyStatus(Response response, int expectedStatusCode){
		if(response.getStatusCode()==expectedStatusCode)
			return true;
		else
			return false;
	}
	
	public boolean verifyBody(Response response, String path, String value){
		return false;
		
		
		
	}


}
