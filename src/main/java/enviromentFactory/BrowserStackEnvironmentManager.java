package enviromentFactory;

import core.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackEnvironmentManager implements EnvironmentFactory {
    private WebDriver driver;
    private String browserName, osName, browserVer, osVer;

    public BrowserStackEnvironmentManager(String browserName, String osName, String browserVer, String osVer) {
        this.browserName = browserName;
        this.osName = osName;
        this.browserVer = browserVer;
        this.osVer = osVer;
    }

    @Override
    public WebDriver createDriver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();

        capabilities.setCapability("browserName", browserName);
        bstackOptions.put("os", osName);
        bstackOptions.put("osVersion", osVer);
        bstackOptions.put("browserVersion", browserVer);
        bstackOptions.put("userName", GlobalConstants.BROWSERSTACK_USERNAME);
        bstackOptions.put("accessKey", GlobalConstants.BROWSERSTACK_AUTOMATE_KEY);
        bstackOptions.put("seleniumVersion", "4.45.0");
        capabilities.setCapability("bstack:options", bstackOptions);


        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSERSTACK_URL),capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
