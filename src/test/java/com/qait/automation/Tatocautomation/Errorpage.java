package com.qait.automation.Tatocautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Errorpage {
	
//	WebDriver web;
//	public Errorpage(WebDriver web)
//	{
//		this.web = web;
//	}
	
	public static String getErrorMessage(WebDriver web)
	{
		System.out.println("ASdasdsa");
		return web.findElement(By.cssSelector("body > div > div.page > span")).getText();
	}
	
}
