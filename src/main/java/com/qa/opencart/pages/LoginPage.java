package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By email_TB = By.id("input-email");
	private By password_TB = By.id("input-password");
	private By login_Btn = By.xpath("//input[@value='Login']");
	private By forgotpassword_Lnk = By.linkText("Forgotten Password");
	private By register_Lnk = By.linkText("Register");
	private By logoutSuccess_msg = By.cssSelector("div#common-success h1");
	private By logoutContinue_Btn = By.linkText("Continue");
	private By logoutContinue_Btn2 = By.linkText("Continue");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	@Step("Getting Login Page title")
	public String getTitle() {
		return eleutil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}
	
	@Step("Getting Login Page URL")
	public String loginpageCurrentUrl() {
		return eleutil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}
	
	@Step("Logged in with credentials username:{0} and password{1}")
	public AccountPage doLogin(String un, String pwd)  {
		System.out.println("The Login Credentials are "+un+pwd);
		eleutil.waitForElementVisible(email_TB, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(un);
		eleutil.doSendKeys(password_TB, pwd);
		eleutil.doClick(login_Btn);
		return new AccountPage(driver);
	}
	
	@Step("Register Link Exists")
	public boolean isRegisterLinkExists() {
		return eleutil.doIsDisplayed(register_Lnk);
	}
	
	@Step("Forgot Password Link Exists")
	public boolean isForgotPasswordLinkExists() {
		return eleutil.doIsDisplayed(forgotpassword_Lnk);
	}
	
	@Step("Fetching logout success message")
	public boolean isLogoutMsgExists() {
		return eleutil.waitForElementVisible(logoutSuccess_msg, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	
	@Step("Clicking on Logout page continue button")
	public void clickOnLogoutContinue() {
		eleutil.doClick(logoutContinue_Btn);
	}
	
	@Step("Navigate to Register page")
	public RegisterPage goToRegisterPage() {
		eleutil.doClick(register_Lnk);
		return new RegisterPage(driver);
	}
	
	
	
	

}
