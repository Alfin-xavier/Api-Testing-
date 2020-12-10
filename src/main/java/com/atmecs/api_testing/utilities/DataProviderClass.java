package com.atmecs.api_testing.utilities;

import org.testng.annotations.DataProvider;

import com.atmecs.api_testing.constants.Constants;

public class DataProviderClass 
{
	@DataProvider(name = "createUser")
	public Object[][] readData()
	{
		JsonFileReader jsonReader = new JsonFileReader(Constants.JSON_DATA_FILE);
		
		Object object = jsonReader.parse();
		
		Object[][] data = new Object[1][1];
		
		data[0][0] = object;
		
		return data;
	}
	
}
