package uitest.m9;

import base.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wait.WaitingUtils;

import static factory.DriverFactory.newChromeDriver;
import static helper.Pages.LOANS;
import static org.testng.Assert.assertEquals;

public class RefactorSetupCleanup2 extends BaseTestClass {

    @Test
    public void refactoredTestDemo(){

        driver.get(LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");

        var message = WaitingUtils.waitUntilVisible(driver,By.id("result"),6);
        //assertEquals(message,getText(),"You will pay us back 1000");
        assertEquals(message.getText(),"You will pays us back 1000");
        driver.quit();

    }

    @Test
    public void refactoredTestDemoDuplicate(){
        driver.get(LOANS);
        driver.findElement(By.id("borrow")).sendKeys("500");

        var message = WaitingUtils.waitUntilVisible(driver,By.id("result"),6);
        //assertEquals(message,getText(),"You will pay us back 1000");
        assertEquals(message.getText(),"You will pays us back 1000");
        driver.quit();

    }
}
