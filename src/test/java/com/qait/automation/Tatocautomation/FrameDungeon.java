package com.qait.automation.Tatocautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class FrameDungeon {

    Errorpage error;
    Drag drag;
    @FindBy(css = "body > div > div.page > h1")
    private WebElement Heading;

    private WebDriver web;
    
    public void initiaiseElements() {
        PageFactory.initElements(this.web, this);
    }
    
    public FrameDungeon(WebDriver web) {
        this.web = web;
    }

    public Boolean isDisplayed() {
        
        initiaiseElements();
        return Heading.getText().contains("Frame Dungeon") && web.getCurrentUrl().equals("http://10.0.1.86/tatoc/basic/frame/dungeon");
    }
    public void proceed_without_repaint(){

        initiaiseElements();
        web.switchTo().frame("main");
        Assert.assertTrue(this.web.findElement(By.id("answer")).isDisplayed());
        Assert.assertTrue(this.web.findElement(By.cssSelector("body > center > a:nth-child(9)")).isDisplayed());
        
        this.web.findElement(By.cssSelector("body > center > a:nth-child(9)")).click();
        error = new Errorpage(this.web);
        Assert.assertTrue(error.getErrorMessage().contains("The page you are looking for does not exist"));
    }
    public void repaint_box() {
        
        this.web.get("http://10.0.1.86/tatoc/basic/frame/dungeon");
        initiaiseElements();
        this.web.switchTo().frame("main");
        Assert.assertTrue(this.web.findElement(By.id("answer")).isDisplayed()); 
        String box1 = web.findElement(By.id("answer")).getCssValue("background-color");
        int n=0;
        while (n==0) {

            web.findElement(By.cssSelector("a")).click(); //click on repaint box 2
            web.switchTo().frame("child"); //goto child for box2
            Assert.assertTrue(this.web.findElement(By.id("answer")).isDisplayed()); 
            String box2 = web.findElement(By.id("answer")).getCssValue("background-color");// get second box color
            web.switchTo().parentFrame(); //goback to parent form for repainting box
            if (box1.equals(box2)) {
                web.findElements(By.cssSelector("a")).get(1).click(); //clicking on proceed
                n=1;
            }
        }
        
        drag = new Drag(this.web);
        Assert.assertTrue(drag.isDisplayed());

    }
}
