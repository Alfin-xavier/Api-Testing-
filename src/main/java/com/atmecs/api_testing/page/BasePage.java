package com.atmecs.api_testing.page;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

public class BasePage 
{
	public WebDriver driver;
	@BeforeTest
	public void initBrowser() throws MalformedURLException
	{
		String gridUrl = "http://192.168.163.1:4445/wd/hub";
		Capabilities cabs = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL(gridUrl), cabs);

	}
}
