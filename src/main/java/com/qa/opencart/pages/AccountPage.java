package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	WebDriver driver;
	ElementUtil eleutil;
	
	private By logout_lnk = By.linkText("Logout");
	private By sectionheaders = By.cssSelector("div#account-account h2");
	private By search_tb = By.xpath("//input[@name='search']");
	private By searchIcon = By.cssSelector("div#search button");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public String accountpagetitle() {
		return eleutil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}
	
	public boolean accountpageurlcontains() {
		String urlfraction = eleutil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
		return urlfraction.contains(Constants.ACCOUNT_PAGE_URL_FRACTION);
	}
	
	public boolean isLogoutLinkExists() {
		return eleutil.isElementDisplayed(logout_lnk);
	}
	
	public boolean isSearchTBExists() {
		return eleutil.isElementDisplayed(search_tb);
	}
	
	public List<String> getAccountPageSectionHeadersText() {
		return eleutil.getElementsTextList(sectionheaders);
	}

	public LoginPage doLogout() {
		eleutil.doClick(logout_lnk);
		return new LoginPage(driver);	
	}
	
	
	public SearchResultsPage doSearch(String prodName) {
		eleutil.doSendKeys(search_tb, prodName);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
	

	
	
	

}
