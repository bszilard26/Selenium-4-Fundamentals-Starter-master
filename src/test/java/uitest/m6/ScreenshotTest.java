package uitest.m6;

import helper.DriverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static helper.Pages.HOME;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ScreenshotTest {

    @Test
    public void fullPageScreenshot() {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            Path destination = Paths.get("failure-screenshot.png");

            Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Test
    public void elementScreenshot() throws IOException {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(HOME);
        WebElement form = driver.findElement(By.tagName("form"));
        File screenshot = form.getScreenshotAs(OutputType.FILE);

        Path destination = Paths.get("failure-element.png");
        Files.move(screenshot.toPath(),destination,REPLACE_EXISTING);

        driver.quit();
    }
}