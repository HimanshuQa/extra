package com.qait.automation.Tatocautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class tatoc {

	WebDriver web;
	
	public tatoc(){
		System.setProperty("webdriver.chrome.driver","/home/himanshuchaudhary/Downloads/chromedriver");
		this.web = new ChromeDriver();
		web.get("http://10.0.1.86/tatoc/basic/grid/gate");
		
		
	}
	
//	@BeforeMethod
//    public void setpath(){
//		System.setProperty("webdriver.chrome.driver","/home/himanshuchaudhary/Downloads/chromedriver");
//
//    }

	@Test
	public  void greenBox(){
		Assert.assertEquals(this.web.findElement(By.className("greenbox")).isDisplayed(), true);
		this.web.findElement(By.className("greenbox")).click();
		String expectedUrl = "http://10.0.1.86/tatoc/basic/frame/dungeon";
		
		Assert.assertEquals(expectedUrl, web.getCurrentUrl(),"Didn't navigate to correct webpage");		
																									
	}
	
	@Test(dependsOnMethods = {"greenBox"})
	public  void frameDungeon(){
		
		web.switchTo().frame("main");
		Assert.assertEquals(this.web.findElement(By.id("answer")).isDisplayed(), true); //check whether frame is displayed or not

		String box1 = web.findElement(By.id("answer")).getAttribute("class");// get first box color
	    
	    while(true){
	            
	            web.findElement(By.cssSelector("a")).click(); //click on repaint box 2
	            web.switchTo().frame("child"); //goto child for box2
	            String box2 = web.findElement(By.id("answer")).getAttribute("class");// get second box color
	            web.switchTo().parentFrame(); //goback to parent form for repainting box
	            if(box1.equals(box2))
	            {
	                web.findElements(By.cssSelector("a")).get(1).click(); //clicking on proceed
	                break;
	            }
	        }
	    
	    String expectedUrl = "http://10.0.1.86/tatoc/basic/drag"; //url of next page
		Assert.assertEquals(expectedUrl, web.getCurrentUrl(),"Didn't navigate to correct webpage");		

		
	}
	
	@Test(dependsOnMethods = {"frameDungeon"})
	public  void drag(){
		Actions actions = new Actions(web);

	    web.get("http://10.0.1.86/tatoc/basic/drag");
		Assert.assertEquals(this.web.findElement(By.id("dropbox")).isDisplayed(), true); //check whether dropbox is displayed or not
		Assert.assertEquals(this.web.findElement(By.id("dragbox")).isDisplayed(), true); //check whether dragbox is displayed or not

	    
	    WebElement drop = web.findElement(By.id("dropbox")); // get dropbox
	    WebElement drag = web.findElement(By.id("dragbox")); // get dragbox
	    actions.dragAndDrop(drag, drop); //drag and drop in box
	    actions.build().perform();
	    web.findElement(By.cssSelector("a")).click();
	    


		
	}
	
		

}
