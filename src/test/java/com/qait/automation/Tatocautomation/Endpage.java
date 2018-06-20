package com.qait.automation.Tatocautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Endpage {
	
	@FindBy(css = "body > div > div.page > h1")
    private WebElement heading;
    
    
    private WebDriver web;
       
    
    
    public Endpage(WebDriver web){
        this.web = web;
    }
    
    public void initiaiseElements() {
        PageFactory.initElements(this.web, this);
    }
    
    public Boolean isDisplayed() {
    	initiaiseElements();
    	System.out.println(web.getCurrentUrl() + heading.getText());
    	return heading.getText().contains("End") && web.getCurrentUrl().equals("http://10.0.1.86/tatoc/end");
    }
}
