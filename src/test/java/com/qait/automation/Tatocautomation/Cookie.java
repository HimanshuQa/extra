package com.qait.automation.Tatocautomation;

import org.apache.http.util.Asserts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Cookie {


	Errorpage error;
    Endpage end;
	
    @FindBy(css = "body > div > div.page > h1")
    private WebElement heading;
    
    @FindBy(css = "body > div > div.page > a:nth-child(8)")
    private WebElement generate;
    
    @FindBy(css = "body > div > div.page > a:nth-child(10)")
    private WebElement proceed;
    
    @FindBy(css = "#token")
    private WebElement token;
    
    private WebDriver web;
       
    
    
    public Cookie(WebDriver web){
        this.web = web;
    }
    
    public void initiaiseElements() {
        PageFactory.initElements(this.web, this);
    }
    public Boolean isDisplayed() {
    	initiaiseElements();
    	return heading.getText().contains("Cookie Handling") && web.getCurrentUrl().equals("http://10.0.1.86/tatoc/basic/cookie");
    }
    
    public Boolean proceed_without_generating_token(){
    	
    	initiaiseElements();
    	Assert.assertEquals(proceed.isDisplayed(), true);
        Assert.assertEquals(generate.isDisplayed(), true);
        
        proceed.click();
        error = new Errorpage(this.web);
        return error.getErrorMessage().contains("The page you are looking for does not exist");
        
    }
    
    public Boolean proceed_with_generating_token_but_not_adding_cookie(){
    	
    	web.get("http://10.0.1.86/tatoc/basic/cookie");
    	initiaiseElements();
    	Assert.assertEquals(proceed.isDisplayed(), true);
        Assert.assertEquals(generate.isDisplayed(), true);
        generate.click();
        try {
            String value = token.getText().split("Token: ")[1];			
            Assert.assertFalse(value.isEmpty());
        } catch (Exception e) {
			Assert.assertTrue(false,"token not generated");
		}
        
        proceed.click();
        error = new Errorpage(this.web);
        return error.getErrorMessage().contains("The page you are looking for does not exist");
    }
    
    public Boolean proceed_with_generating_token_adding_cookie(){
    	
    	web.get("http://10.0.1.86/tatoc/basic/cookie");
    	initiaiseElements();
    	Assert.assertEquals(proceed.isDisplayed(), true);
        Assert.assertEquals(generate.isDisplayed(), true);
        generate.click();
        try {
            String value = token.getText().split("Token: ")[1];			
            Assert.assertFalse(value.isEmpty());
        } catch (Exception e) {
			Assert.assertTrue(false,"token not generated");
		}
        proceed.click();
        end = new Endpage(web);
        return end.isDisplayed();
    }
}
