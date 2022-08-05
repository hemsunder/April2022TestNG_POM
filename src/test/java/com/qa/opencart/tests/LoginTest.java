package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-100: This Epic is about to Login Page Functionalities")
@Story("Login-101: design Login page with various features")
public class LoginTest extends BaseTest {
	
	@Description("This is Login Page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPagetitleTest() {
		String title = loginpage.getTitle();
		System.out.println("The title of the page is "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("This is Login Page URL test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void loginPageurltest() {
		String acturl = loginpage.loginpageCurrentUrl();
		//String urlFraction = eleutil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
		System.out.println("The URL is "+acturl);
		Assert.assertTrue(acturl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	
	@Description("Register Link availability test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void isRegisterLinkExists() {
		Assert.assertTrue(loginpage.isRegisterLinkExists());
	}
	
	@Description("Forgot password Link availability test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void isForgotpwdLinkExists() {
		Assert.assertTrue(loginpage.isForgotPasswordLinkExists());
	}
	
	
	@Description("Open cart application Login test: Logged in with credentials: username{0} and password{1}")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest()  {
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

}
