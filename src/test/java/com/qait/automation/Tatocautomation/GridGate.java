package com.qait.automation.Tatocautomation;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GridGate {

	Errorpage error ;
	FrameDungeon frame ;
	WebDriver web ;
	
	
	@FindBy(className="redbox")
	private List<WebElement> red_box;
	
	public GridGate(WebDriver web)
	{
		this.web = web;
		PageFactory.initElements(this.web, this);
		
	}
	
	public void openPage()
	{
        this.web.get("http://10.0.1.86/tatoc/basic/grid/gate");	
	}
	
	public Boolean click_on_redbox() {
		openPage();
		red_box.get(0).click();
		error = new Errorpage(this.web);
		return error.getErrorMessage().contains("The page you are looking for does not exist");
	}
	
	public Boolean click_on_greenbox()
	{
		openPage();
		web.findElement(By.className("greenbox")).click();
		frame = new FrameDungeon(this.web);

		return frame.isDisplayed().contains("Frame Dungeon");
	}
}
