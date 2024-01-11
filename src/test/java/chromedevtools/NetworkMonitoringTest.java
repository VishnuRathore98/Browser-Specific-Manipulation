package chromedevtools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.Headers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NetworkMonitoringTest {
    WebDriver driver;
    DevTools devTools;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void test(){
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

//      We create a listener for HTTP requests and log the captured data in the console.
        devTools.addListener(Network.requestWillBeSent(),request -> {
            System.out.println("Request Id: "+request.getRequestId());
            System.out.println("Request Method: "+request.getRequest().getMethod());
            System.out.println("Request URL: "+request.getRequest().getUrl());
//            System.out.println("Request Headers: "+request.getRequest().getHeaders());
            logHeaders(request.getRequest().getHeaders());
        });

//      We create a listener for HTTP response and log the captured data in the console.
        devTools.addListener(Network.responseReceived(),response ->{
            System.out.println("Response Id: "+response.getRequestId());
            System.out.println("Response URL: "+response.getResponse().getUrl());
            System.out.println("Response Status: "+response.getResponse().getStatus());
//            System.out.println("Response Headers: "+response.getResponse().getHeaders());
            logHeaders(response.getResponse().getHeaders());
        });
        driver.get("https://bonigarcia.dev/selenium-webdriver-java");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

//  We print the headers in tabular format.
    void logHeaders(Headers headers){
        System.out.println("Headers: ");
        headers.toJson().forEach((k,v) -> System.out.println(k+" : "+v));
    }

    @AfterEach
    public void teardown()throws InterruptedException{
        Thread.sleep(3000);
        devTools.close();
        driver.quit();
    }
}
