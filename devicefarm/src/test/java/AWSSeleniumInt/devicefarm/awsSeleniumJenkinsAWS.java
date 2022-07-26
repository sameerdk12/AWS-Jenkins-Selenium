package AWSSeleniumInt.devicefarm;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

public class awsSeleniumJenkinsAWS {
	
	//Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
    ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	//WebDriver driver = new WebDriver();
    DesiredCapabilities desired_capabilities = DesiredCapabilities.chrome();
    
    @Test
    //@Parameters(value={"browser"})
    public void setUp() throws MalformedURLException {
        System.out.println("Google1 Test Started! " + "Thread Id: " +  Thread.currentThread().getId());
        getDriver().navigate().to("http://www.google.com");
        System.out.println("Google1 Test's Page title is: " + getDriver().getTitle() +" " + "Thread Id: " +  Thread.currentThread().getId());
        Assert.assertEquals(getDriver().getTitle(), "Google");
        System.out.println("Google1 Test Ended! " + "Thread Id: " +  Thread.currentThread().getId());

}
    

    public void GOOGLE1() {
        
    }
    
    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
        return driver.get();
    }
    
    @AfterTest
    public void teardown() {
    	getDriver().quit();      // tear down the driver and its parts. 
    }

}
