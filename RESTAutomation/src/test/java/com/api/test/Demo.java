package com.api.test;

import org.testng.annotations.Test;

public class Demo {

	@Test
	public void t1(){
		
		System.out.println(new Throwable().getStackTrace()[0].getMethodName());
		
	}

}
