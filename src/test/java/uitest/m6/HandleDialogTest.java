package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class HandleDialogTest {

    @Test
    void dismissAlertTest() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        // Corrected declarations using 'var'
        var first = driver.findElement(By.id("firstName"));
        var last = driver.findElement(By.id("lastName"));

        first.sendKeys("Szilard");
        last.sendKeys("Balogh");

        DemoHelper.pause();
        driver.findElement(By.id("clear")).click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        Assert.assertEquals(first.getAttribute("value"), "Szilard");
        Assert.assertEquals(last.getAttribute("value"), "Balogh");

        driver.quit();
    }
}