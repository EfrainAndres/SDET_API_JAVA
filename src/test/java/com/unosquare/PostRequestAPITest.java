package com.unosquare;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.unosquare.base.BaseTest;

import io.restassured.response.Response;

public class PostRequestAPITest extends BaseTest {
	
	@Test
	public void testSuccessfulRegister() throws IOException, ParseException {
		Response successfulRegister = apiCore.postRequest("src/test/resources/Json/Register.json", "/api/register");
		Assert.assertEquals(200, successfulRegister.statusCode());
	}
	
	@Test
	public void testUnsuccessfulRegister() throws IOException, ParseException {
		Response unsuccessfulRegister = apiCore.postRequest("src/test/resources/Json/BadBody.json", "/api/register");
		Assert.assertEquals(400, unsuccessfulRegister.statusCode());
		Assert.assertEquals("Missing password", unsuccessfulRegister.jsonPath().getString("error"));
	}
	
	@Test
	public void testSuccessfulLogin() throws IOException, ParseException {
		Response successfulLogin = apiCore.postRequest("src/test/resources/Json/Login.json", "/api/login");
		Assert.assertEquals(200, successfulLogin.statusCode());
	}
	
	@Test
	public void testUnsuccessfulLogin() throws IOException, ParseException {
		Response unsuccessfulLogin = apiCore.postRequest("src/test/resources/Json/BadBody.json", "/api/login");

		Assert.assertEquals(400, unsuccessfulLogin.statusCode());
		Assert.assertEquals("Missing password", unsuccessfulLogin.jsonPath().getString("error"));

	}
}
