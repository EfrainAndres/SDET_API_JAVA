package com.unosquare.base;

import org.testng.annotations.BeforeSuite;
import com.unosquare.ApiCore;
import io.restassured.RestAssured;

public class BaseTest {
	protected ApiCore apiCore;
	
	@BeforeSuite
	public void beforeSuite() {
		apiCore = new ApiCore();
		RestAssured.baseURI = "https://reqres.in";
	}
}
