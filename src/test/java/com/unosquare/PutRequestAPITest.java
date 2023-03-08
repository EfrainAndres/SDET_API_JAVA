package com.unosquare;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import junit.framework.Assert;

public class PutRequestAPITest {
	
	ApiCore apiCore = new ApiCore();
	@Test
	public void testUpdateUser() throws IOException, ParseException {
		Response updateUser = apiCore.putRequest("src/test/resources/Json/UpdateUser.json", "/api/users/2");
		Assert.assertEquals(200, updateUser.statusCode());
		Assert.assertEquals("Efrain", updateUser.jsonPath().getString("name"));
		Assert.assertEquals("SDET", updateUser.jsonPath().getString("job"));
	}
}
