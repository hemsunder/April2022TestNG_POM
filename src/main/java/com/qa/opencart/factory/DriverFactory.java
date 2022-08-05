package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager opManager;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method will initialize the driver with given browser name
	 * @param browsername
	 * @return it returns WebDriver
	 */
	
	public WebDriver init_driver(String browsername) {
		
		opManager = new OptionsManager(prop);
		System.out.println("Browser name is "+browsername);
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(opManager.getChromeOptions());
			tldriver.set(new ChromeDriver(opManager.getChromeOptions()));
		}
		else if(browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver(opManager.getEdgeOptions());
			tldriver.set(new EdgeDriver(opManager.getEdgeOptions()));
		}
		else {
			System.out.println("Please give the correct browser name");
		}
		
		getdriver().manage().deleteAllCookies();
		getdriver().manage().window().maximize();
		getdriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		
		return getdriver();
	}
	
	public static WebDriver getdriver() {
		return tldriver.get();
	}
	
	
	public Properties init_properties() {
		
		prop = new Properties();
		FileInputStream fis = null;
		
		String envname = System.getProperty("env");
		System.out.println("Environment name is "+envname);
		
		if(envname==null) {
			System.out.println("As env is given as null, Execution is going to happen in qa environment");
			try {
				fis = new FileInputStream(".//src//test//resources//config//config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		else {
			try {
			switch (envname.toLowerCase()) {
			case "qa":
				System.out.println("Execution is going to happen in qa environment");
				fis = new FileInputStream(".//src//test//resources//config//qa.config.properties");
				break;
			case "reg":
				System.out.println("Execution is going to happen in regression environment");
				fis = new FileInputStream(".//src//test//resources//config//reg.config.properties");
				break;
			case "stage":
				System.out.println("Execution is going to happen in stage environment");
				fis = new FileInputStream(".//src//test//resources//config//stage.config.properties");
				break;
			case "prod":
				System.out.println("Execution is going to happen in prod environment");
				fis = new FileInputStream(".//src//test//resources//config//prod.config.properties");
				break;
			case "preprod":
				System.out.println("Execution is going to happen in qa environment");
				fis = new FileInputStream(".//src//test//resources//config//config.properties");
				break;

			default:
				break;
			}
			}
			
			catch(Exception e) {
				
			}
		}
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
	public String getScreenshot() {
		File src = ((TakesScreenshot)getdriver()).getScreenshotAs(OutputType.FILE);
		String path = "./"+"screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	

}
