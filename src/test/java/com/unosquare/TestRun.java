package com.unosquare;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.unosquare.base.BaseTest;

import io.restassured.response.Response;
import junit.framework.Assert;

public class TestRun extends BaseTest {

	@Test
	public void testGetListUsers() {
		Response listUsers = apiCore.getRequest("/api/users?page=2");
		Assert.assertEquals(200, listUsers.statusCode());
		Assert.assertEquals(12, listUsers.jsonPath().getInt("total"));
	}

	@Test
	public void testGetSingleUser() {
		Response singleUser = apiCore.getRequest("/api/users/2");
		Assert.assertEquals(200, singleUser.statusCode());
		Assert.assertEquals("janet.weaver@reqres.in", singleUser.jsonPath().getString("data.email"));
	}

	@Test
	public void testGetSingleUserNotFound() {
		Response singleUserNotFound = apiCore.getRequest("/api/users/23");
		Assert.assertEquals(404, singleUserNotFound.statusCode());
	}

	@Test
	public void testGetListResource() {
		Response listUsers = apiCore.getRequest("/api/unknown");
		Assert.assertEquals(200, listUsers.statusCode());
		Assert.assertEquals(12, listUsers.jsonPath().getInt("total"));
	}

	@Test
	public void testPostSuccessfulRegister() throws IOException, ParseException {
		Response successfulRegister = apiCore.postRequest("src/test/resources/Json/Register.json", "/api/register");
		Assert.assertEquals(200, successfulRegister.statusCode());
	}

	@Test
	public void testPostUnsuccessfulRegister() throws IOException, ParseException {
		Response unsuccessfulRegister = apiCore.postRequest("src/test/resources/Json/BadBody.json", "/api/register");
		Assert.assertEquals(400, unsuccessfulRegister.statusCode());
		Assert.assertEquals("Missing password", unsuccessfulRegister.jsonPath().getString("error"));
	}

	@Test
	public void testPostSuccessfulLogin() throws IOException, ParseException {
		Response successfulLogin = apiCore.postRequest("src/test/resources/Json/Login.json", "/api/login");
		Assert.assertEquals(200, successfulLogin.statusCode());
	}

	@Test
	public void testPostUnsuccessfulLogin() throws IOException, ParseException {
		Response unsuccessfulLogin = apiCore.postRequest("src/test/resources/Json/BadBody.json", "/api/login");

		Assert.assertEquals(400, unsuccessfulLogin.statusCode());
		Assert.assertEquals("Missing password", unsuccessfulLogin.jsonPath().getString("error"));

	}

	@Test
	public void testUpdateUser() throws IOException, ParseException {
		Response updateUser = apiCore.putRequest("src/test/resources/Json/UpdateUser.json", "/api/users/2");
		Assert.assertEquals(200, updateUser.statusCode());
		Assert.assertEquals("Efrain", updateUser.jsonPath().getString("name"));
		Assert.assertEquals("SDET", updateUser.jsonPath().getString("job"));
	}
}
