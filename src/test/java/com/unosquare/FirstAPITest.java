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

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;

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
	  		.then()
	  		.assertThat().statusCode(200)
	  		.assertThat().contentType(ContentType.JSON);
	  
	  Reporter.log("Success 200 validation");
  }
  
  @Test
  public void PostCreate() {
	  RestAssured.baseURI = "https://reqres.in/";
      JSONObject requestParams = new JSONObject();
      requestParams.put("name", "John");
      requestParams.put("job", "QA Engineer");
      
      Response response = RestAssured.given()
              .header("Content-Type", "application/json")
              .body(requestParams.toString())
              .post("/api/users");
      
      int statusCode = response.getStatusCode();
      String body = response.getBody().asString();
      
      Reporter.log("Response Code: " + statusCode);
      Reporter.log("Response Body: " + body);
      Reporter.log("JSON Body: " + requestParams);
  }
  
  @Test
  public void PostRegister() throws FileNotFoundException {
	  RestAssured.baseURI = "https://reqres.in/";
	  FileReader jsonFile = new FileReader("src/test/resources/Json/Register.json");
      JSONObject requestParams = new JSONObject(jsonFile);
      
      Response response = RestAssured.given()
              .header("Content-Type", "application/json")
              .body(requestParams.toString())
              .post("/api/users");
      
      int statusCode = response.getStatusCode();
      String body = response.getBody().asString();
      
      Reporter.log("Response Code: " + statusCode);
      Reporter.log("Response Body: " + body);
      Reporter.log("JSON Body: " + requestParams);
  }
  @BeforeMethod
  public void beforeMethod() {
  }

}
