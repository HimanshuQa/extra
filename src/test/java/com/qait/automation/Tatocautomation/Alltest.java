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
    GridGate grid;
    FrameDungeon frame;
    Drag drag;
    Popup popup;
    Cookie cookie;
    @BeforeClass
    public void start() {
        System.setProperty("webdriver.chrome.driver", "/home/himanshuchaudhary/Downloads/chromedriver");
        this.web = new ChromeDriver();
        grid = new GridGate(this.web);
        frame = new FrameDungeon(this.web);
        drag = new Drag(this.web);
        popup = new Popup(this.web);
        cookie = new Cookie(this.web);
    }

    @AfterClass
    public void end() {
        this.web.close();
    }

    @Test()
    public void test_grid_red_box() {

        Assert.assertTrue(grid.click_on_redbox(), "Error page does not displayed");

    }

    @Test(dependsOnMethods = {"test_grid_red_box"})
    public void test_grid_green_box() {

        Assert.assertTrue(grid.click_on_greenbox(), "Wrong page displayed");

    }
    
    @Test(dependsOnMethods = {"test_grid_green_box"})
    public void test_without_repaint_box() {
        Assert.assertTrue(frame.proceed_without_repaint());

    }

    @Test(dependsOnMethods = {"test_without_repaint_box"})
    public void test_repaint_box() {
        
        Assert.assertTrue(frame.repaint_box());

    }

    @Test(dependsOnMethods = {"test_repaint_box"})
    public void test_click_proceed_without_draging()
    {
        Assert.assertTrue(drag.click_on_proceed_without_drag_drop());
    }
    
    @Test(dependsOnMethods = {"test_click_proceed_without_draging"})
    public void test_dragbox_to_somewhere(){

        Assert.assertTrue(drag.moving_dragbox_to_somwhere_but_not_in_dropbox());
    }
    
    @Test(dependsOnMethods = {"test_dragbox_to_somewhere"})
    public void test_dragbox_in_dropbox(){

        Assert.assertTrue(drag.dragbox_in_dropbox());
    }
    
    @Test(dependsOnMethods = {"test_dragbox_in_dropbox"})
    public void test_proceed_without_launching_window(){
    	
        Assert.assertTrue(popup.proceed_without_launching_window());

    }
    
    @Test(dependsOnMethods = {"test_proceed_without_launching_window"})
    public void test_leave_popup_window_textfield_empty_and_proceed(){
    	
        Assert.assertTrue(popup.leave_popup_window_textfield_empty_and_proceed());

    }
    
    @Test(dependsOnMethods = {"test_leave_popup_window_textfield_empty_and_proceed"})
    public void test_launch_window_enter_text_proceed(){
    	
        Assert.assertTrue(popup.launch_window_enter_text_proceed());

    }    
    
    @Test(dependsOnMethods = {"test_launch_window_enter_text_proceed"})
    public void test_proceed_without_generating_token(){
    	
        Assert.assertTrue(cookie.proceed_without_generating_token());

    }    
    
    @Test(dependsOnMethods = {"test_proceed_without_generating_token"})
    public void test_proceed_with_generating_token_but_not_adding_cookie(){
    	
        Assert.assertTrue(cookie.proceed_with_generating_token_but_not_adding_cookie());

    }    
    
    @Test(dependsOnMethods = {"test_proceed_with_generating_token_but_not_adding_cookie"})
    public void test_proceed_with_generating_token_adding_cookie(){
    	
        Assert.assertTrue(cookie.proceed_with_generating_token_adding_cookie());

    }    
}
