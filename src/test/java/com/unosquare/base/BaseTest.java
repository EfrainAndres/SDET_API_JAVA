package com.unosquare.base;

import org.testng.annotations.BeforeSuite;
import com.unosquare.ApiCore;
import io.restassured.RestAssured;

public class BaseTest {
	
	@BeforeSuite
	public void beforeSuite() {
		RestAssured.baseURI = "https://reqres.in";
	}
}
