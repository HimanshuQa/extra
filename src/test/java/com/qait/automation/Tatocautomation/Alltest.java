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
    
    @BeforeClass
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HIM\\Desktop\\chromedriver_win32\\chromedriver.exe");
        this.web = new ChromeDriver();
        grid = new GridGate(this.web);
        frame = new FrameDungeon(this.web);
        drag = new Drag(web);

    }

//    @AfterClass
//    public void end() {
//        this.web.close();
//    }

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
        Assert.assertTrue(frame.click_without_repaint());

    }

    @Test(dependsOnMethods = {"test_without_repaint_box"})
    public void test_repaint_box() {
        
        Assert.assertTrue(frame.repaint_box());

    }

    @Test(dependsOnMethods = {"test_repaint_box"})
    public void click_proceed_without_draging()
    {
        Assert.assertTrue(drag.click_on_proceed_without_drag_drop());
    }
    @Test(dependsOnMethods = {"click_proceed_without_draging"})
    public void dragbox_to_somewhere(){

        Assert.assertTrue(drag.moving_dragbox_to_somwhere_but_not_in_dropbox());
    }
}
