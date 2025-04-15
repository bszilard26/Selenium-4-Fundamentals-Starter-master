package uitest.m7;

import helper.DemoHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static helper.Pages.HOME;

public class HandleAlertsByDefault {

    @Test
    public void handleAlertsByDefault(){
        ChromeOptions options = new ChromeOptions();
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        WebDriver driver = new ChromeDriver(options);
        driver.get(HOME);

        driver.findElement(By.id("clear")).click();
        driver.findElement(By.id("register")).click();
        DemoHelper.pause();
        driver.quit();
    }

}
