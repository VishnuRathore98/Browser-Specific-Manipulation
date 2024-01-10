package chromedevtools;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BasicAuthTest {
    WebDriver driver;
    WebElement body;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();

//      We cast the driver object to HasAuthentication and register the credentials(username and password)
        ((HasAuthentication) driver).register(()-> new UsernameAndPassword("guest","guest"));
    }
    @Test
    public void test(){
//      We open a website protected with basic authentication.
        driver.get("https://jigsaw.w3.org/HTTP/Basic/");
        body = driver.findElement(By.tagName("body"));

//      We verify the page content is available.
        assertThat(body.getText()).contains("Your browser made it!");
    }
    @AfterAll
    public void teardown()throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }
}
