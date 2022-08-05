package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic:200 - This Epic is about Account Page functionalities")
@Story("Account Page-201: Design account page with various features")
public class AccountPageTest extends BaseTest{
	
	
	@BeforeClass
	public void doLogin() {
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Description("validate Account Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void validateAccountPageTitle() {
		Assert.assertEquals(accpage.accountpagetitle(),Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Description("validate Account Page URLFraction Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2, enabled=false)
	public void validateAccountPageURLFraction() {
		//Assert.assertTrue(accpage.accountpageurlcontains(Constants.ACCOUNT_PAGE_URL_FRACTION));
	}
	
	@Description("Logout Link Existstance Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void isLogoutLinkExists() {
		Assert.assertTrue(accpage.isLogoutLinkExists());
	}
	
	@Description("Search TB Existstance Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void isSearchTBExists() {
		Assert.assertTrue(accpage.isSearchTBExists());
	}
	
	@Description("Account page Section Headers Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5)
	public void accountpageSectionHeadersTest() {
		List<String> sectionheadersList = accpage.getAccountPageSectionHeadersText();
		Assert.assertEquals(sectionheadersList, Constants.ACCOUNT_PAGE_SECTION_HEADERS_LIST);
	}
	
	@Description("Logout Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=6)
	public void doLogoutTest() {
		accpage.doLogout();
		loginpage.clickOnLogoutContinue();
	}
	
	@DataProvider
	public Object[][] supplySearchData() {
		return new Object[][] {
			{"MacBook"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	@Test(dataProvider = "supplySearchData", priority=7)
	public void doSearchTest(String prName) {
		Assert.assertTrue(accpage.doSearch(prName).getResultsCount()>0);
	}
	
	
	@DataProvider
	public Object[][] supplyDataToSelect() {
		
		return new Object[][] { 
			{ "Macbook", "MacBook Pro" }, 
			{ "Macbook", "MacBook Air" }, 
			{ "iMac", "iMac" }, 
			{ "Apple", "Apple Cinema 30\"" }, 
			{ "Samsung", "Samsung SyncMaster 941BW" } };
	}
	
	
	@Test(dataProvider = "supplyDataToSelect", priority=8, enabled=false)
	public void seletProductTest(String searchhKey, String ProductName) {
		String productHeaderName = accpage.doSearch(searchhKey).doSelectProduct(ProductName).getProductHeaderName();
		Assert.assertEquals(productHeaderName, ProductName);
	}
	
	@Test(priority=10)
	public void enterQuantityTest() {
		accpage.doSearch("MacBook").doSelectProduct("MacBook Pro").enterQuantity("2");
	}
	
	
	@Test(priority=9, enabled=false)
	public void metaDataTest() {
		prinfo = accpage.doSearch("MacBook").doSelectProduct("MacBook Pro");
		Map<String, String> metaData = prinfo.getProductMetaData();
		softassert.assertEquals(metaData.get("Brand"), "Apple");
		softassert.assertEquals(metaData.get("Product Code"), "Product 18");
		softassert.assertEquals(metaData.get("Reward Points"), "800");
		softassert.assertEquals(metaData.get("Availability"), "In Stock");
		softassert.assertEquals(metaData.get("price"), "$2,000.00");
		softassert.assertEquals(metaData.get("tax"), "Ex Tax: $2,000.00");
		
	}
	
	
	
	

}
