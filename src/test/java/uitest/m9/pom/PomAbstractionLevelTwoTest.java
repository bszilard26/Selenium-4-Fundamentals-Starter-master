package uitest.m9.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Savings;

import static factory.DriverFactory.newChromeDriver;

public class PomAbstractionLevelTwoTest {

    WebDriver driver;

    @BeforeMethod
    public void initDriver(){
        driver = newChromeDriver();
    }

    @Test
    public void savingsTest(){
        var page = Savings.savingsPage(driver);
        page.goTo();
        page.enterDeposit("500");
        page.selectTimePeriod(Savings.Period.ONE_YEAR);

        WebElement message = page.resultMessage();
        Assert.assertTrue(message.isDisplayed());
        Assert.assertEquals(message.getText(),"After 1 Year you will earn $25.00 on your deposit");

    }

    @AfterMethod
    public void closeDriver(){driver.quit();}
}
