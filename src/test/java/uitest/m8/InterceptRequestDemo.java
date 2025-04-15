package uitest.m8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Request;
import org.openqa.selenium.devtools.v130.network.model.Response;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class InterceptRequestDemo {
    private WebDriver driver;
    private DevTools devTools;

    @Test
    public void howToGetDevToolsObject() {
        ChromeDriver chromeDriver = new ChromeDriver();
        DevTools tools1 = chromeDriver.getDevTools();
        tools1.createSession(); // Ensure a session is created
        chromeDriver.quit(); // Cleanup
    }

    @Test
    public void captureRequestTraffic() {
        driver = new ChromeDriver();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();  // No need to pass window handle

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestEvent -> {
            Request r = requestEvent.getRequest();
            System.out.printf("URL: %s, Method: %s%n", r.getUrl(), r.getMethod());
        });

        driver.get("http://127.0.0.1:8000/index.html");
    }

    @Test
    public void captureResponseTraffic(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.responseReceived(),
                responseReceived -> {
                        Response r = responseReceived.getResponse();
                        System.out.printf("Response status: %s \n", r.getStatus());
                        Assert.assertTrue(r.getStatus()<= 400);
                }
                );

        driver.get("http://127.0.0.1:8000/index.html");
    }


        @Test
        public void manipulateTraffic() {
            driver = new ChromeDriver();
            devTools = getDevTools(driver);

            // Blocking JavaScript files
            devTools.send(Network.setBlockedURLs(List.of("*.js")));

            driver.get("http://127.0.0.1:8000/index.html");

            WebElement location = new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(visibilityOfElementLocated(By.id("location")));

            Assert.assertTrue(location.getText().contains("You are visiting us from"));

            driver.quit(); // Ensure the driver is closed after execution
        }

        private static DevTools getDevTools(WebDriver driver) {
            DevTools devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
            return devTools;
        }


    @AfterMethod
    public void cleanup(){
        //devTools.send(Network.disable());
        devTools.close();
        driver.quit();
    }
}