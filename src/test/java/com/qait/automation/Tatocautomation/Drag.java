/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.Tatocautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author HIM
 */
public class Drag {
    
    Errorpage error;
    Popup popup;
    
    @FindBy(css = "body > div > div.page > h1")
    private WebElement heading;
    
    @FindBy(id = "dropbox")
    private WebElement dropbox;
    
    @FindBy(id = "dragbox")
    private WebElement dragbox;
    
    @FindBy(css = "body > div > div.page > a")
    private WebElement proceed;
    
    private WebDriver web;
       
    public Boolean isDisplayed() {
        PageFactory.initElements(this.web, this);
        return heading.getText().contains("Drag Around") && web.getCurrentUrl().equals("http://10.0.1.86/tatoc/basic/drag");
    }
    public Drag(WebDriver web){
        this.web = web;
    }
    
    public void initiaiseElements() {
        PageFactory.initElements(this.web, this);
    }

    public Boolean click_on_proceed_without_drag_drop(){

        initiaiseElements();
        Assert.assertEquals(dropbox.isDisplayed(), true);
        Assert.assertEquals(dragbox.isDisplayed(), true);
        
        proceed.click();
        error = new Errorpage(this.web);
        return error.getErrorMessage().contains("The page you are looking for does not exist");
    }
    
    public Boolean moving_dragbox_to_somwhere_but_not_in_dropbox()
    {
        this.web.navigate().back();
        initiaiseElements();
        Assert.assertEquals(dropbox.isDisplayed(), true);
        Assert.assertEquals(dragbox.isDisplayed(), true);
        
        Actions actions = new Actions(this.web);
        actions.clickAndHold(dragbox).moveByOffset(130, 0).release().perform();
        proceed.click();
        error = new Errorpage(this.web);
        return error.getErrorMessage().contains("The page you are looking for does not exist");
   
    }
    
    public Boolean dragbox_in_dropbox(){

    	this.web.navigate().back();
        initiaiseElements();
        Assert.assertEquals(dropbox.isDisplayed(), true);
        Assert.assertEquals(dragbox.isDisplayed(), true);
        
        Actions actions = new Actions(this.web);
        actions.dragAndDrop(dragbox, dropbox).build().perform();
        proceed.click();
        popup = new Popup(this.web);
        return popup.isDisplayed();

    }

}
