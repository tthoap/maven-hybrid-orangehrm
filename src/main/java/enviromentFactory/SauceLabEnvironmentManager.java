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
import java.util.HashMap;

public class SauceLabEnvironmentManager implements EnvironmentFactory {
    private WebDriver driver;
    private String browserName;
    private String platformName;
    private String osName;
    private String browserVersion;

    public SauceLabEnvironmentManager(String browserName, String platformName, String osName, String browserVersion) {
        this.browserName = browserName;
        this.platformName = platformName;
        this.osName = osName;
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

        HashMap<String, String> sauceOptions = new HashMap<String, String>();
        sauceOptions.put("username", GlobalConstants.SAUCE_USERNAME);
        sauceOptions.put("accessKey", GlobalConstants.SAUCE_AUTOMATE_KEY);
        sauceOptions.put("build", "automation-fc-build");
        sauceOptions.put("name", "Run on " + platformName + " | " + browserName + " | " + browserVersion);

        capability.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCE_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
