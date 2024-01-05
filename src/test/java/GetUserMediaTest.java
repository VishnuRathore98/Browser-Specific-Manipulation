// Test setup granting synthetic user media in Chrome.

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetUserMediaTest {
    WebDriver driver;
    ChromeOptions options;

    @BeforeAll
    public void setup(){
        options = new ChromeOptions();

//      Argument to allow accessing user media(audio and video).
        options.addArguments("--use-fake-ui-for-media-stream");

//      Argument to fake user media using a synthetic video(green spinner) and audio(a beep per second).
        options.addArguments("--use-fake-device-for-media-stream");

        driver = new ChromeDriver(options);
    }

    @Test
    public void test(){
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/get-user-media.html");
        driver.findElement(By.id("start")).click();
    }

    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(5000);
        driver.quit();
    }
}