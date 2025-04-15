package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Map;

public class DriverFactory {

    public static WebDriver newChromeDriver(){
        System.setProperty("webdriver.chrome.driver","/opt/homebrew/bin/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public static WebDriver newEdgeDriver(){

        return applyCommonSetup(new EdgeDriver());
    }

    public static WebDriver newFirefoxDriver(){

        return applyCommonSetup(new FirefoxDriver());
    }

    public static WebDriver newDevice(String deviceName){
        Map<String, String> mobileEmulation = Map.of("deviceName", "iPhone 14 Pro Max");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        return new ChromeDriver(options);

    }

    public static WebDriver applyCommonSetup(WebDriver driver){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

}
