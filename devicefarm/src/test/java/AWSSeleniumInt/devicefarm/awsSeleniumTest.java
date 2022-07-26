package AWSSeleniumInt.devicefarm;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;


public class awsSeleniumTest {
	
	//Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
    ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	//WebDriver driver = new WebDriver();
    DesiredCapabilities desired_capabilities = DesiredCapabilities.chrome();

	/** Set up the test suite. 
	 * @throws MalformedURLException */
    @Test
    //@Parameters(value={"browser"})
    public void setUp() throws MalformedURLException {
    	String projectARN = "arn:aws:devicefarm:us-west-2:774320013474:testgrid-project:7d5d5b30-60b7-4604-a707-f75cc108afd0";
        DeviceFarmClient client  = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
        CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder().expiresInSeconds(300).projectArn(projectARN).build();
        CreateTestGridUrlResponse response = client.createTestGridUrl(request);
        URL testGridUrl = new URL(response.url());
        driver.set(new RemoteWebDriver(testGridUrl, desired_capabilities.chrome()));
        //WebDriver driver = new RemoteWebDriver(testGridUrl, desired_capabilities);
        //driver.set(new RemoteWebDriver(testGridUrl, desired_capabilities));
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