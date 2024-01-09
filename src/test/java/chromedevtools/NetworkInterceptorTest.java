package chromedevtools;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NetworkInterceptorTest {
    WebDriver driver;
    Path img;
    byte[] bytes;

    @BeforeAll
    public void setup() throws URISyntaxException, IOException {
        driver = new ChromeDriver();

        // We load a local image stored as a test resource in the java project.
        img = Paths.get(ClassLoader.getSystemResource("tools.png").toURI());

        bytes = Files.readAllBytes(img);

    }

    @Test
    public void test(){

        // We create a network interceptor instance, creating a route for all the requests ending in .png, and stubbing
        // this request with a new response, in this case sending the content of the previous picture.
        NetworkInterceptor interceptor = new NetworkInterceptor(driver, Route.matching(new Predicate<HttpRequest>() {
                    @Override
                    public boolean test(HttpRequest req) {
                        return req.getUri().endsWith(".png");
                    }
                }).to(new Supplier<HttpHandler>() {
                                    @Override
                                    public HttpHandler get() {
                                        return new HttpHandler() {
                                            @Override
                                            public HttpResponse execute(HttpRequest req) throws UncheckedIOException {
                                                return new HttpResponse().setContent(Contents.bytes(bytes));
                                            }
                                        };
                                    }
                                }
                )
        );

            // We open the practice site.
            driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

            // If the interception works as expected, the image on the page should have a width higher than the original
            // logo.
            int width = Integer.parseInt(driver.findElement(By.tagName("img")).getAttribute("width"));
            assertThat(width).isGreaterThan(80);
    }

    @AfterAll
    public void teardown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }
}