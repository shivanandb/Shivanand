package com.DoNotOpen;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TrialProgram {
	String APIKey = "bf882b0f-e520-4c81-9673-d14b20f294a9";
	@Test
	public void LaunchPage() {

		
		System.out.println("This is a trial page");
		
	}
@Test

public void TestCompany() {
	String URL="https://api.hubapi.com/companies/v2/companies?hapikey="+APIKey;
	String body="{  \"properties\": [    {      \"name\": \"name\",      \"value\": \"A company name\"    },    {      \"name\": \"description\",      \"value\": \"A company description\"    } ]}";
given().contentType("application/json").body(body).when().post(URL).then().statusCode(200);

}
	/*
@Test
public void DeleteCompany() {
	
	RestAssured.baseURI = "";
	String URL="https://api.hubapi.com/companies/v2/companies/10444744?hapikey="+APIKey;
	String body="{    \"companyId\": 10444744,     \"deleted\": true}";
	given().contentType("application/json").body(body).when().post(URL).then().statusCode(200);
}*/

}
