package com.unosquare;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static io.restassured.RestAssured.*;

public class FirstAPITest {

	ApiCore apiCore;
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
	public void PostRegister() throws IOException, ParseException {
		RestAssured.baseURI = "https://reqres.in/";
		JSONParser json = new JSONParser();
		FileReader jsonFile = new FileReader("src/test/resources/Json/Register.json");
		Object obj = json.parse(jsonFile);

		JSONObject requestParams = (JSONObject) obj;

		Response response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(requestParams.toString())
				.post("/api/register");

		int statusCode = response.getStatusCode();
		String body = response.getBody().asString();

		Reporter.log("Response Code: " + statusCode);
		Reporter.log("Response Body: " + body);
		Reporter.log("JSON Body: " + requestParams);
	}

	@Test
	public void PostLogin() throws IOException, ParseException {
		Response test = apiCore.PostLogin("src/test/resources/Json/Login.json", "/login");

		Assert.assertEquals(200, test.statusCode());
	}

	@BeforeSuite
	public void beforeSuite() {
		apiCore = new ApiCore();
	}

	@BeforeMethod
	public void beforeMethod() {
	}

}
