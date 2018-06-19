package com.qait.automation.Tatocautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Alltest {

    WebDriver web;
    GridGate grid ;
    
	@BeforeClass
    public void start()
    {
        System.setProperty("webdriver.chrome.driver", "/home/himanshuchaudhary/Downloads/chromedriver");
    	this.web = new ChromeDriver();
    	grid = new GridGate(this.web);
    }
    
//    @AfterClass
//    public void end()
//    {
//    	this.web.close();
//    }
    
//    @Test(priority=0)
//    public void test_grid_red_box()
//    {
//
//    	Assert.assertTrue(grid.click_on_redbox(), "Error page does not displayed");
//    	
//    }
    
    @Test
    public void test_grid_green_box()
    {

    	Assert.assertTrue(grid.click_on_greenbox(), "Wrong page displayed");
    	
    }
    
}
