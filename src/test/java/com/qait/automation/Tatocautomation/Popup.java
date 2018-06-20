package com.qait.automation.Tatocautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Popup {

	Errorpage error;
    
    @FindBy(css = "body > div > div.page > h1")
    private WebElement heading;
    
    @FindBy(css = "body > div > div.page > span")
    private WebElement launch_popup;
    
    @FindBy(css = "body > div > div.page > a:nth-child(6)")
    private WebElement proceed;
    
    private WebDriver web;
       
    public Boolean isDisplayed() {
        PageFactory.initElements(this.web, this);
        return heading.getText().contains("Popup Windows") && web.getCurrentUrl().equals("http://10.0.1.86/tatoc/basic/windows");
    }
    
    public Popup(WebDriver web){
        this.web = web;
    }
    
    public void initiaiseElements() {
        PageFactory.initElements(this.web, this);
    }
}
