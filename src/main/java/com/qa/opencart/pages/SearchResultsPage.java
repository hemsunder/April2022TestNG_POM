package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	WebDriver driver;
	ElementUtil eleutil;
	
	private By resultcount = By.cssSelector("div.product-layout.product-grid");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public int getResultsCount() {
		return eleutil.waitForElementsVisible(resultcount, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	
	public ProductInfoPage doSelectProduct(String productName) {
		By productToSelect = By.linkText(productName);
		eleutil.waitForElementVisible(productToSelect, Constants.DEFAULT_ELEMENT_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}
	
	
	
	

}
