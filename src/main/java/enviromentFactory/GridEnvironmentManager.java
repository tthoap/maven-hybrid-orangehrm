package enviromentFactory;

import browserFactory.BrowserList;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class GridEnvironmentManager implements EnvironmentFactory {
    private WebDriver driver;
    private String browserName;
    private String osName;
    private String ipAddress;
    private String port;

    public GridEnvironmentManager(String browserName, String osName, String ipAddress, String port) {
        this.browserName = browserName;
        this.osName = osName;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public WebDriver createDriver() {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        Capabilities capability = null;
        Platform  platform = null;
        if (osName.toLowerCase().contains("windows")) {
            platform = Platform.WINDOWS;
        } else if (osName.toLowerCase().contains("mac")) {
            platform = Platform.MAC;
        } else if (osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("ubuntu")) {
            platform = Platform.LINUX;
        } else {
            platform = Platform.ANY;
        }

        switch (browserList){
            case FIREFOX:
                capability = new FirefoxOptions();
                break;
            case CHROME:
                capability = new ChromeOptions();
                break;
            case EDGE:
                capability = new EdgeOptions();
                break;
            case SAFARI:
                capability = new SafariOptions();
                break;
            default:
                throw new RuntimeException("Browser is invalid!");
        }
        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, port)),capability);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
