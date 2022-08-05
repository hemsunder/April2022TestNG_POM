package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	WebDriver driver;
	ElementUtil eleutil;
	Map<String, String> prMetaData;
	
	private By productHeaderTitle = By.cssSelector("div#content h1");
	private By imagesCount = By.cssSelector("ul.thumbnails li");
	private By quantity_TB = By.id("input-quantity");
	private By productMetaData = By.xpath("(//div[@id='content']//ul)[3]/li");
	private By productpriceData = By.xpath("(//div[@id='content']//ul)[4]/li");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public String getProductHeaderName() {
		return eleutil.waitForElementVisible(productHeaderTitle, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
	
	
	public int getImagesCount() {
		return eleutil.waitForElementsVisible(imagesCount, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	
	public void enterQuantity(String qty) {
		eleutil.doSendKeys(quantity_TB, qty);
	}
	
	public Map<String, String> getProductMetaData() {
		prMetaData = new HashMap<String, String>();
		getProductMetaDatalogic();
		getProductPricelogic();
		prMetaData.forEach((k,v) -> System.out.println(k+" : "+v));
		return prMetaData;
	}
	
	
	private void getProductMetaDatalogic() {
		List<WebElement> prMetaList = eleutil.getElements(productMetaData);
		
		for(WebElement e:prMetaList) {
			String[] data = e.getText().split(":");
			String metakey = data[0].trim();
			String metavalue = data[1].trim();
			prMetaData.put(metakey, metavalue);
		}
	}
	
	
	private void getProductPricelogic() {
		List<WebElement> prPriceList = eleutil.getElements(productpriceData);
		
		String priceTag = prPriceList.get(0).getText().trim();
		String taxTag = prPriceList.get(1).getText().trim();
		prMetaData.put("price", priceTag);
		prMetaData.put("tax", taxTag);
		
	}
	
}
