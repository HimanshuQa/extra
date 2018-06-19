package com.qait.automation.Tatocautomation;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GreenBox {

	static Errorpage eror;
	
	public static Boolean click_on_redbox(WebDriver web) throws InterruptedException{
		System.out.println("dddddddddd");

		web.findElement(By.cssSelector("body > div > div.page > table > tbody > tr:nth-child(1) > td:nth-child(1) > div")).click();
		Thread.sleep(1000);
		return eror.getErrorMessage(web).contains("The page you are looking for does not exist");
	}
	
//	public Boolean click_on_green_box(WebDriver web)
//	{
//		
//		return 
//	}
}
