package com.globant.automation.cyf2020.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.globant.automation.cyf2020.infrastructure.BasePage;
import com.sun.jdi.Type;

public class GoogleHome extends BasePage {

	public GoogleHome(WebDriver driver) {
		super(driver);
		driver.get("http://google.com");
	}
	
	@FindBy (name= "q")
	private WebElement inputBuscar;
	
	
	@FindBy (name= "btnK")
	private WebElement inputBuscar1;
	
	public GoogleSearchPage googleSearch (String gWord) {
		type (inputBuscar, gWord);
		click (inputBuscar1);
		return getNextPage(GoogleSearchPage.class);
	}
	
	
	

}
