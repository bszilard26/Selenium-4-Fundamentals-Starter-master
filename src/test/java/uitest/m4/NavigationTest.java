package uitest.m4;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import javax.swing.plaf.basic.BasicSplitPaneUI;

import static helper.Pages.HOME;
import static helper.Pages.SAVINGS;

public class NavigationTest {

    private static final String PREFIX = "file:///" + System.getProperty("user.dir") + "\\src\\web\\";

    @Test
    public void basicNavigationTest() {
        System.setProperty("webdriver.chrome.driver","/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(PREFIX + "index.html");

        DemoHelper.pause();
        driver.get(PREFIX + "savings.html");

        DemoHelper.pause();
        driver.navigate().back();

        DemoHelper.pause();
        driver.navigate().forward();

        DemoHelper.pause();
        driver.navigate().refresh();

        driver.quit();


    }

    @Test
    public void basicNavigationTestRefactored() {
        WebDriver driver = DriverFactory.newDriver();

        driver.get(HOME);
        DemoHelper.pause();

        driver.get(SAVINGS);
        DemoHelper.pause();


        driver.quit();

    }
}
