package enviromentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class LambdaEnviromentManager implements EnvironmentFactory{
    private WebDriver driver;
    private String browserName;
    private String platformName;
    private String browserVersion;

    public LambdaEnviromentManager(String browserName, String platformName, String browserVersion) {
        this.browserName = browserName;
        this.platformName = platformName;
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver createDriver() {
        MutableCapabilities capability = null;
        browserName = browserName.toLowerCase();

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setCapability("platformName", platformName);
                fOptions.setCapability("browserVersion", browserVersion);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setCapability("platformName", platformName);
                cOptions.setCapability("browserVersion", browserVersion);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setCapability("platformName", platformName);
                eOptions.setCapability("browserVersion", browserVersion);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setCapability("platformName", platformName);
                sOptions.setCapability("browserVersion", browserVersion);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        HashMap<String, Object> lambdaOptions = new HashMap<String, Object>();
        lambdaOptions.put("username", GlobalConstants.LAMBDA_USERNAME);
        lambdaOptions.put("accessKey", GlobalConstants.LAMBDA_AUTOMATE_KEY);
        lambdaOptions.put("build", "Build Test");
        lambdaOptions.put("project", "OrangeHRM");
        lambdaOptions.put("selenium_version", "4.13.0");
        lambdaOptions.put("w3c", true);
        lambdaOptions.put("name", "Run on " + platformName + " | " + browserName + " | " + browserVersion);

        capability.setCapability("LT:Options", lambdaOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.LAMBDA_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
