package com.unosquare;

import org.testng.annotations.Test;

import com.unosquare.base.BaseTest;

import io.restassured.response.Response;
import junit.framework.Assert;

public class GetRequestAPITest extends BaseTest {

	@Test
	public void testListUsers() {
		Response listUsers = apiCore.getRequest("/api/users?page=2");
		Assert.assertEquals(200, listUsers.statusCode());
		Assert.assertEquals(12, listUsers.jsonPath().getInt("total"));
	}

	@Test
	public void testSingleUser() {
		Response singleUser = apiCore.getRequest("/api/users/2");
		Assert.assertEquals(200, singleUser.statusCode());
		Assert.assertEquals("janet.weaver@reqres.in", singleUser.jsonPath().getString("data.email"));
	}

	@Test
	public void testSingleUserNotFound() {
		Response singleUserNotFound = apiCore.getRequest("/api/users/23");
		Assert.assertEquals(404, singleUserNotFound.statusCode());
	}

	@Test
	public void testListResource() {
		Response listUsers = apiCore.getRequest("/api/unknown");
		Assert.assertEquals(200, listUsers.statusCode());
		Assert.assertEquals(12, listUsers.jsonPath().getInt("total"));
	}
}
