package com.unosquare;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;

public class FirstAPITest {
  @Test
  public void f() {
	  	RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/2");
		
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);
		Reporter.log("Success 200 validation");
		
		response.then().body("data.first_name", equalTo("Janet"));
		Reporter.log(response.body().asString());
  }
  
  @Test
  public void f_Gherkin() {
	  
	  given()
	  .when()
	  	.get("https://reqres.in/api/users/2")
	  		.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
	  
	  Reporter.log("Sucess 200 validation");
  }

  @BeforeMethod
  public void beforeMethod() {
  }

}
