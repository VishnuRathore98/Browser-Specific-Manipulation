package chromedevtools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.dom.model.Rect;
import org.openqa.selenium.devtools.v118.page.Page;
import org.openqa.selenium.devtools.v118.page.model.Viewport;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FullPageScreenshotTest {
    WebDriver driver;
    DevTools devTools;
    WebDriverWait wait;
    String screenshotBase64;
    Rect contentSize;
    Path destination;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Test
    public void test() throws IOException {
//      We load the practice page containing a long text(and therefore, its content goes beyond the standard viewport).
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");

//      We wait until the paragraphs are loaded.
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(
            By.className("container"), By.tagName("p")));

//      We get the page layout metrics(to calculate the page dimensions).
        Page.GetLayoutMetricsResponse metrics = devTools.send(Page.getLayoutMetrics());
        contentSize = metrics.getContentSize();

//      We send the CDP command to make a screenshot beyond the page viewport. As a result, we obtain the screenshot as
//      a string in Base64.
        screenshotBase64 = devTools.send(
            Page.captureScreenshot(
            Optional.empty(),
            Optional.empty(),
            Optional.of(new Viewport(0,0,contentSize.getWidth(),contentSize.getHeight(),1)),
            Optional.empty(),
            Optional.of(true),
            Optional.of(false)
            ));
        destination = Paths.get("fullpage-screenshot-chrome.png");

//      We decode the Base64 content into a PNG file.
        Files.write(destination, Base64.getDecoder().decode(screenshotBase64));

//      We assert the PNG file exists at the end of the test.
        assertThat(destination).exists();
    }

    @AfterEach
    public void teardown()throws InterruptedException{
        Thread.sleep(3000);
        devTools.close();
        driver.quit();
    }

}
