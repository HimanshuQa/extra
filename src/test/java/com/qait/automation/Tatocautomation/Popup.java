package com.qait.automation.Tatocautomation;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Popup {

	Errorpage error;
    Cookie cookie;
	
    @FindBy(css = "body > div > div.page > h1")
    private WebElement heading;
    
    @FindBy(css = "body > div > div.page > a:nth-child(4)")
    private WebElement launch_popup;
    
    @FindBy(css = "body > div > div.page > a:nth-child(6)")
    private WebElement proceed;
    
    private WebDriver web;
       
    public Popup(WebDriver web){
        this.web = web;
    }
    
    public void initiaiseElements() {
        PageFactory.initElements(this.web, this);
    }
    public Boolean isDisplayed() {
    	initiaiseElements();
        return heading.getText().contains("Popup Windows") && web.getCurrentUrl().equals("http://10.0.1.86/tatoc/basic/windows");
    }
    
    public Boolean proceed_without_launching_window(){
    	initiaiseElements();
    	Assert.assertEquals(proceed.isDisplayed(), true);
        Assert.assertEquals(launch_popup.isDisplayed(), true);
        
        proceed.click();
        error = new Errorpage(this.web);
        return error.getErrorMessage().contains("The page you are looking for does not exist");
  
    }
    
    public Boolean leave_popup_window_textfield_empty_and_proceed() {
    	web.get("http://10.0.1.86/tatoc/basic/windows");
    	initiaiseElements();
    	Assert.assertEquals(proceed.isDisplayed(), true);
        Assert.assertEquals(launch_popup.isDisplayed(), true);
        
        String parentWindowHandler = web.getWindowHandle(); // Store your parent window
        launch_popup.click();
        
        String subWindowHandler = null;

        Set<String> handles = web.getWindowHandles(); // get all window handles
        System.out.println(handles);

        Iterator<String> iterator = handles.iterator(); //creating iterator to get popup window
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        web.switchTo().window(subWindowHandler); // switch to popup window
        web.findElement(By.id("name")).sendKeys("");// writing in area
        web.findElement(By.id("submit")).click();// clicking on submit
        web.switchTo().window(parentWindowHandler);  // switch back to parent window
    	initiaiseElements();

        proceed.click(); //click on proceed
        error = new Errorpage(this.web);
        return error.getErrorMessage().contains("The page you are looking for does not exist");
  
	}
    
    public Boolean launch_window_enter_text_proceed(){
    	
    	web.get("http://10.0.1.86/tatoc/basic/windows");
    	initiaiseElements();
    	Assert.assertEquals(proceed.isDisplayed(), true);
        Assert.assertEquals(launch_popup.isDisplayed(), true);
        
        String parentWindowHandler = web.getWindowHandle(); // Store your parent window
        launch_popup.click();
        
        String subWindowHandler = null;

        Set<String> handles = web.getWindowHandles(); // get all window handles
        System.out.println(handles);

        Iterator<String> iterator = handles.iterator(); //creating iterator to get popup window
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        web.switchTo().window(subWindowHandler); // switch to popup window
        web.findElement(By.id("name")).sendKeys("qainfotech");// writing in area
        web.findElement(By.id("submit")).click();// clicking on submit
        web.switchTo().window(parentWindowHandler);  // switch back to parent window
    	initiaiseElements();
        proceed.click(); //click on proceed
        
        cookie = new Cookie(web);
        return cookie.isDisplayed();
    }
}
