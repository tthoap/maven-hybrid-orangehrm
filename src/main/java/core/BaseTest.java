package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getBrowserDriver(String browserName, String appUrl){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;

            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser is invalid!");
        }
        driver.get(appUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        //driver.manage().window().maximize();
        System.out.println("Driver in BaseTest" + driver.toString());
        return driver;
    }

    protected void closeBrowser(){
        if(!(null==driver)) {
            driver.quit();
        }
    }
    protected void closeBrowser(WebDriver driver){
        if(!(null==driver)) {
            driver.quit();
        }
    }

    protected int getRandomNumber(){
        return new Random().nextInt(9999);
    }
}
