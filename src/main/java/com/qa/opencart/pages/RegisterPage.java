package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");
	private By privacypolicy_CB = By.xpath("//input[@type='checkbox']");
	private By continue_Btn = By.xpath("//input[@value='Continue']");
	private By successalert = By.cssSelector("div#content h1");
	private By logoutLnk = By.linkText("Logout");
	private By registerLnk = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	public boolean doRegister(String fname, String lname, String email, 
			String tele, String pwd, String subscribe) {
		
		eleutil.waitForElementVisible(firstname, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(fname);
		eleutil.doSendKeys(lastname, lname);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(telephone, tele);
		eleutil.doSendKeys(password, pwd);
		eleutil.doSendKeys(confirmpassword, pwd);
		
		if(subscribe.trim().equals("yes")) {
			eleutil.doClick(subscribeYes);
		}
		else {
			eleutil.doClick(subscribeNo);
		}
		
		eleutil.doClick(privacypolicy_CB);
		eleutil.doClick(continue_Btn);
		
		String success_msg = eleutil.waitForElementVisible(successalert, Constants.DEFAULT_TIMEOUT).getText();
		
		if(success_msg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			eleutil.doClick(logoutLnk);
			String logout_msg = eleutil.waitForElementVisible(successalert, Constants.DEFAULT_TIMEOUT).getText();
			if(logout_msg.contains(Constants.LOGOUT_CONFIRM_MSG)) {
				eleutil.doClick(registerLnk);
			}
			
			return true;
		}
		
		else {
			
			return false;
		}
		
		
		
	}
	
	
	

}
