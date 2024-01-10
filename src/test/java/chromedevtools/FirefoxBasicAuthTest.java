package chromedevtools;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirefoxBasicAuthTest {
    WebDriver driver;
    WebElement body;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
    }
    @Test
    public void test(){
        driver.get("https://guest:guest@jigsaw.w3.org/HTTP/Basic/");
        body = driver.findElement(By.tagName("body"));
        assertThat(body.getText()).contains("Your browser made it!");
    }
    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }
}
