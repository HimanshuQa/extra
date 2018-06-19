package com.qait.automation.Tatocautomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Errorpage {
	

	@FindBy(css="body > div > div.page > span")
	private WebElement error_message;
	
	WebDriver web;
	
	public Errorpage(WebDriver web){
		
		this.web = web;
		PageFactory.initElements(this.web, this);
	
	}
	
	public  String getErrorMessage()
	{
	
		return error_message.getText();
	}
	
}
