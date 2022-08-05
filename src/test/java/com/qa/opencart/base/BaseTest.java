package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	
	protected LoginPage loginpage;
	protected AccountPage accpage;
	protected ProductInfoPage prinfo;
	protected RegisterPage regpage;
	
	public SoftAssert softassert;
	
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_properties();
		driver = df.init_driver(prop.getProperty("browser"));
		loginpage = new LoginPage(driver);
		softassert = new SoftAssert();
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
	

}
