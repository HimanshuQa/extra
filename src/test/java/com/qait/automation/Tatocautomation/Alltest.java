package com.qait.automation.Tatocautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Alltest {

    WebDriver web;
    GreenBox green;
    
	@BeforeClass
    public void start()
    {
        System.setProperty("webdriver.chrome.driver", "/home/himanshuchaudhary/Downloads/chromedriver");
    	this.web = new ChromeDriver();
        web.get("http://10.0.1.86/tatoc/basic/grid/gate");	
    }
    
    @AfterClass
    public void end()
    {
    	this.web.close();
    }
    
    @Test
    public void testGreenBox() throws InterruptedException
    {
		System.out.println("ASdasdsa");

    	green.click_on_redbox(this.web);
    	
    }
    
}
