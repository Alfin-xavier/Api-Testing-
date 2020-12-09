package com.atmecs.api_testing.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.atmecs.api_testing.utilities.DataProviderClass;
import com.atmecs.api_testing.utilities.Logging;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateRecord 
{
	@Test(dataProvider = "createUser", dataProviderClass =DataProviderClass.class )
	public void createRecord(Object requestBody) throws MalformedURLException
	{
		Logging log = new Logging();
		
		String url = "https://sample-3a82.restdb.io/rest/api-testing ";
		
		String accessToken = "b35c5b2a12e7cb38d9913ecdd8734006969f2";

		RequestSpecification request = RestAssured.given();

		log.info("Providing access and inserting records to the db !!"+"\n");
		Response response = request.auth().preemptive().oauth2(accessToken)
				.header("x-apikey", accessToken)
				.body(requestBody.toString())
				.contentType(ContentType.JSON)
				.post(new URL(url)).then().extract().response();

		int statusCode = response.getStatusCode();
		String responseBody = response.getBody().asString();
		
		log.info("Getting status code: "+ statusCode);
		log.info("Verifying status code !!");
		Assert.assertEquals(statusCode, 201);
		log.info("Verified status code !!"+"\n");
		
		log.info("Getting response body: "+ responseBody+"\n");

		JsonPath jsonPath = response.jsonPath();

		String id = jsonPath.getString("_id");
		log.info("Getting record id:"+id+"\n");

		String createdAt = jsonPath.getString("_created");
		log.info("Getting created time: " + createdAt+"\n");
			  		 
	}
}
