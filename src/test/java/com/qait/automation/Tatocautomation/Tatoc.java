package com.qait.automation.Tatocautomation;


import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class Tatoc {

    WebDriver web;

//    public Tatoc() {
//        System.setProperty("webdriver.chrome.driver", "/home/himanshuchaudhary/Downloads/chromedriver");
//        this.web = new ChromeDriver();
//        web.get("http://10.0.1.86/tatoc/basic/grid/gate");
//    }
    
    
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
    public void greenBox() {
        Assert.assertEquals(this.web.findElement(By.className("greenbox")).isDisplayed(), true);
        this.web.findElement(By.className("greenbox")).click();
        String expectedUrl = "http://10.0.1.86/tatoc/basic/frame/dungeon";

        Assert.assertEquals(expectedUrl, web.getCurrentUrl(), "Didn't navigate to correct webpage");

    }

    @Test(dependsOnMethods = {"greenBox"})
    public void frameDungeon() {

        web.switchTo().frame("main");
        Assert.assertEquals(this.web.findElement(By.id("answer")).isDisplayed(), true); //check whether frame is displayed or not

        String box1 = web.findElement(By.id("answer")).getAttribute("class");// get first box color
        int n=0;
        while (n==0) {

            web.findElement(By.cssSelector("a")).click(); //click on repaint box 2
            web.switchTo().frame("child"); //goto child for box2
            String box2 = web.findElement(By.id("answer")).getAttribute("class");// get second box color
            web.switchTo().parentFrame(); //goback to parent form for repainting box
            if (box1.equals(box2)) {
                web.findElements(By.cssSelector("a")).get(1).click(); //clicking on proceed
                n=1;
            }
        }

        String expectedUrl = "http://10.0.1.86/tatoc/basic/drag"; //url of next page
        Assert.assertEquals(expectedUrl, web.getCurrentUrl(), "Didn't navigate to correct webpage");

    }

    @Test(dependsOnMethods = {"frameDungeon"})
    public void drag() {
        Actions actions = new Actions(web);

        //web.get("http://10.0.1.86/tatoc/basic/drag");
        Assert.assertEquals(this.web.findElement(By.id("dropbox")).isDisplayed(), true); //check whether dropbox is displayed or not
        Assert.assertEquals(this.web.findElement(By.id("dragbox")).isDisplayed(), true); //check whether dragbox is displayed or not

        WebElement drop = web.findElement(By.id("dropbox")); // get dropbox
        WebElement drag = web.findElement(By.id("dragbox")); // get dragbox
        actions.dragAndDrop(drag, drop); //drag and drop in box
        actions.build().perform();
        web.findElement(By.cssSelector("a")).click();
        String expectedUrl = "http://10.0.1.86/tatoc/basic/windows"; //url of next page
        Assert.assertEquals(expectedUrl, web.getCurrentUrl(), "Didn't navigate to correct webpage");

    }

    @Test(dependsOnMethods = {"drag"})
    public void popup() {

        String parentWindowHandler = web.getWindowHandle(); // Store your parent window

        web.findElement(By.cssSelector("a")).click(); //click on popup window
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
        web.findElements(By.cssSelector("a")).get(1).click(); //click on proceed
        String expectedUrl = "http://10.0.1.86/tatoc/basic/cookie"; //url of next page
        Assert.assertEquals(expectedUrl, web.getCurrentUrl(), "Didn't navigate to correct webpage");

    }

    @Test(dependsOnMethods = {"popup"})
    public void cookie() {

        int n = 1;
        //loop to check if token is generated or not
        while (n > 0) {
            try {
                web.findElement(By.cssSelector("a")).click(); //click on generate token
                String value = web.findElement(By.id("token")).getText().split("Token: ")[1];  // get tokenn value
                n = 0; // break the loop
            } catch (Exception e) {
                System.out.println(e);
                web.navigate().refresh(); //refresh page if generate token doesnot give result
            }
        }

        String value = web.findElement(By.id("token")).getText().split("Token: ")[1]; // get tokenn value
        web.manage().addCookie(new Cookie("Token", value, "/")); //add cookie
        web.findElements(By.cssSelector("a")).get(1).click(); //click on proceed
        String expectedUrl = "http://10.0.1.86/tatoc/end"; //url of next page
        Assert.assertEquals(expectedUrl, web.getCurrentUrl(), "Didn't navigate to correct webpage");

    }

}