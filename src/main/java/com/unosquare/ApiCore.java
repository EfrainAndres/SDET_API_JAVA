package com.unosquare;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import java.io.FileReader;
import java.io.IOException;

public class ApiCore {
	
	Response response;
	
	public Response postRequest(String filePath, String url) throws IOException, ParseException {

		JSONParser json = new JSONParser();
		FileReader reader = new FileReader(filePath);
		Object obj = json.parse(reader);

		JSONObject requestParams = (JSONObject) obj;
		response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(requestParams.toString())
				.post(url);

		Reporter.log(RestAssured.baseURI + url);
		Reporter.log("Body sent: " + requestParams.toString());
		Reporter.log(String.valueOf(response.getStatusCode()));
		Reporter.log(response.getBody().asString());

		return response; 
	}
	
	public Response getRequest(String url){

		response = RestAssured.given()
				.get(url);

		Reporter.log(RestAssured.baseURI + url);
		Reporter.log(String.valueOf(response.getStatusCode()));
		Reporter.log(response.getBody().asString());

		return response; 

	}
	
	public Response putRequest(String filePath, String url) throws IOException, ParseException {

		JSONParser json = new JSONParser();
		FileReader reader = new FileReader(filePath);
		Object obj = json.parse(reader);

		JSONObject requestParams = (JSONObject) obj;
		response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(requestParams.toString())
				.put(url);

		Reporter.log(RestAssured.baseURI + url);
		Reporter.log("Body sent: " + requestParams.toString());
		Reporter.log(String.valueOf(response.getStatusCode()));
		Reporter.log(response.getBody().asString());

		return response; 
	}
}
