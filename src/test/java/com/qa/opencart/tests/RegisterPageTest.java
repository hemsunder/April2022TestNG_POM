package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void goToRegisterPage() {
		regpage = loginpage.goToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getregisterdata() {
		Object[][] data = ExcelUtil.getTestdata(Constants.REGISTER_DATA);
		return data;
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "testautomation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	
	@Test(dataProvider = "getregisterdata")
	public void registerTest(String fname, String lname, String tphone, String pwd, String subscribe) {
		Assert.assertTrue(regpage.doRegister( fname,  lname,  getRandomEmail(), tphone,  pwd,  subscribe));
		
	}
	
	
	
	
	
	
	
	
	

}
