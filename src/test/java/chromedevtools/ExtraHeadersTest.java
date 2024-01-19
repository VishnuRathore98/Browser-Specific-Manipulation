package chromedevtools;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.Headers;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExtraHeadersTest {
    WebDriver driver;
    DevTools devTools;
    String userName;
    String password;
    Map<String, Object> headers = new HashMap<>();
    String basicAuth;
    String bodyText;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void test(){
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        userName = "guest";
        password = "guest";
//      We encode the username and password in Base64.
        basicAuth = "Basic " + new String(Base64.getEncoder().encode(String.format("%s:%s", userName, password).getBytes()));
        headers.put("Authorization", basicAuth);
//      We create the authorization header.
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

//      We open the web page protected with basic authentication.
        driver.get("https://jigsaw.w3.org/HTTP/Basic/");
        bodyText = driver.findElement(By.tagName("body")).getText();
//      We verify that the page is correctly displayed.
        assertThat(bodyText).contains("Your browser made it!");
    }

    @AfterAll
    public void teardown()throws InterruptedException{
//        Thread.sleep(3000);
//        driver.quit();
    }
}
