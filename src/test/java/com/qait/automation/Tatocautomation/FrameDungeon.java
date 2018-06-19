package com.qait.automation.Tatocautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FrameDungeon {
	
	@FindBy(css="body > div > div.page > h1")
	private WebElement Heading;
	
	private WebDriver web;
	public FrameDungeon(WebDriver web)
	{
		
		this.web = web;
	}
	
	public String isDisplayed()
	{
		PageFactory.initElements(this.web, this);
		return Heading.getText();
	}

}
