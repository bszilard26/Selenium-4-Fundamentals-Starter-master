package uitest.m6;

import helper.DemoHelper;
import helper.DriverFactory;
import helper.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static helper.Pages.LOANS;
import static java.nio.file.Files.createTempFile;

public class UploadFileTest {

    @Test
    void uploadFile() throws IOException {
        WebDriver driver = DriverFactory.newDriver();
        driver.get(LOANS);

        WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));

        Path path = createTempFile("file",".txt");
        String fileName = path.toFile().getAbsolutePath().toString();
        System.out.println(fileName);

        fileInput.sendKeys(fileName);

        DemoHelper.pause();
        driver.quit();
        path.toFile().deleteOnExit();

        driver.quit();
    }
}
