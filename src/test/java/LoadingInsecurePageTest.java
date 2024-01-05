import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoadingInsecurePageTest {
    WebDriver driver;
    ChromeOptions options;
    String bgColor;
    Color red;

    @BeforeAll
    public void setup(){
        options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);
    }
    @Test
    public void test(){
        driver.get("https://www.self-signed.badssl.com/");

        bgColor = driver.findElement(By.tagName("body")).getCssValue("background-color");

        red = new Color(255,0,0,1);

        assertThat(Color.fromString(bgColor)).isEqualTo(red);
    }
    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(5000);
        driver.quit();
    }

}
