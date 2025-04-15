package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static helper.Pages.HOME;
import static helper.Pages.SAVINGS;

public class StorageAndCookiesTest {

    @Test
    public void storageTest() {
        WebDriver driver = new DriverFactory().newDriver();

        try {
            driver.get(HOME);

            // Locate elements
            WebElement first = driver.findElement(By.id("firstName"));
            WebElement last = driver.findElement(By.id("lastName"));
            WebElement save = driver.findElement(By.id("save"));

            // Perform actions
            first.sendKeys("Peter");
            last.sendKeys("Pan");
            save.click();

            // Check session storage
            if (driver instanceof WebStorage) {
                WebStorage webStorage = (WebStorage) driver;
                final SessionStorage storage = webStorage.getSessionStorage(); // Declare as final
                storage.keySet()
                        .forEach(key -> System.out.println(key + "=" + storage.getItem(key))); // Lambda uses final variable
                // Clear storage before verification
                storage.clear();
            } else {
                System.out.println("WebStorage is not supported by this driver.");
            }

            DemoHelper.pause();

            // Navigate to SAVINGS page and back
            driver.get(SAVINGS);
            driver.navigate().back();

            DemoHelper.pause();

            // Re-locate elements to verify saved input
            WebElement firstAfter = driver.findElement(By.id("firstName"));
            WebElement lastAfter = driver.findElement(By.id("lastName"));

            // Assert values
            Assert.assertEquals(firstAfter.getAttribute("value"), "Peter", "First name does not match");
            Assert.assertEquals(lastAfter.getAttribute("value"), "Pan", "Last name does not match");

            driver.navigate().refresh();

            DemoHelper.pause();

            // Verify inputs are cleared
            WebElement firstAfter2 = driver.findElement(By.id("firstName"));
            WebElement lastAfter2 = driver.findElement(By.id("lastName"));
            Assert.assertEquals(firstAfter2.getAttribute("value"), "");
            Assert.assertEquals(lastAfter2.getAttribute("value"), ""); // Fixed variable name

        } finally {
            // Ensure the driver quits after execution
            driver.quit();
        }
    }
}