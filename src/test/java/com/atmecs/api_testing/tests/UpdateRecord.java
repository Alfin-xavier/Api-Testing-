package com.atmecs.api_testing.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.api_testing.page.BasePage;
import com.atmecs.api_testing.utilities.DataProviderClass;
import com.atmecs.api_testing.utilities.Logging;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateRecord extends BasePage
{
	@Test(dataProvider = "createUser", dataProviderClass = DataProviderClass.class )
	public void updateUser(Object requestBody) throws MalformedURLException
	{
		Logging log = new Logging();
		
		String url = "https://sample-3a82.restdb.io/rest/api-testing/5fd20508dcebc06500017378";
		
		String accessToken = "b35c5b2a12e7cb38d9913ecdd8734006969f2";
		
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");

		RequestSpecification request = RestAssured.given().headers(headers);

		log.info("Providing access and update record !!"+"\n");
		Response response = request.auth().preemptive().oauth2(accessToken)
				.header("x-apikey", accessToken)
				.body(requestBody.toString())
				.contentType(ContentType.JSON)
				.put(new URL(url)).then().extract().response();

		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();

		log.info("Getting status code: "+ statusCode);
		
		log.info("Verifying status code !!");
		Assert.assertEquals(statusCode, 200);
		log.info("Verified status code !!"+"\n");
		
		log.info("Getting response body: "+ responseBody+"\n");

		JsonPath jsonPath = response.jsonPath();

		String firstname = jsonPath.getString("firstname");
		log.info("FirstName:" + firstname);
		
		String lastname = jsonPath.getString("lastname");
		log.info("LastName:" + lastname);
		
		String designation = jsonPath.getString("designation");
		log.info("Designation:" + designation);
		
		String mail = jsonPath.getString("mail");
		log.info("Mail:" + mail);
		
		JSONObject jsonObject = (JSONObject) requestBody;
		Assert.assertEquals(firstname, jsonObject.get("firstname").toString());

		String updatedAt = jsonPath.getString("_created");
		log.info("Getting Updated time: " + updatedAt);

	}
}
