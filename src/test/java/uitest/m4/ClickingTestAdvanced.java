package uitest.m4;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class ClickingTestAdvanced {
    @Test
    public void moreClicking(){
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        WebElement checkbox = driver.findElement(By.id("heard-about"));
        Actions actions = new Actions(driver);

        actions.doubleClick(checkbox).perform();
        actions.contextClick(checkbox).perform();

        DemoHelper.pause();
        driver.quit();

    }

}
