package uitest.m5;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static helper.Pages.SAVINGS;

public class ByXpathText {
    WebDriver driver;

    @Test

    public void xPath_1() {
        driver = DriverFactory.newDriver();
        driver.get(SAVINGS);


        var dropdown = driver.findElement(By.xpath("//*[@id=\"period\"]"));
        dropdown.click();

        DemoHelper.pause();
    }

    @Test
    public void xPath_2() {
        driver = DriverFactory.newDriver();
        driver.get(SAVINGS);

        var dropdown = driver.findElement(By.xpath("//*[@id=\"period\"]"));
        System.out.println(dropdown.getText());
        dropdown.click();
        DemoHelper.pause();

        var oneyear = driver.findElement(By.xpath("//*[@id=\"period\"]/option[2]"));
        System.out.println(oneyear.getText());
        oneyear.click();


        DemoHelper.pause();

    }

    @AfterMethod
    public void cleanup(){

    driver.quit();
    
    }
}



