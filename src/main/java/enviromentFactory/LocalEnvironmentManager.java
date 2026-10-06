package enviromentFactory;

import browserFactory.*;
import org.openqa.selenium.WebDriver;

public class LocalEnvironmentManager implements EnvironmentFactory {
    private WebDriver driver;
    private String browserName;

    public LocalEnvironmentManager(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public WebDriver createDriver() {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList){
            case FIREFOX:
                driver = new FirefoxBrowserManager().getDriver();
                break;
            case CHROME:
                driver = new ChromeBrowserManager().getDriver();
                break;
            case EDGE:
                driver = new EdgeBrowserManager().getDriver();
                break;
            case SAFARI:
                driver = new SafariBrowserManager().getDriver();
                break;
            case HEADLESS_CHROME:
                driver = new ChromeHeadlessBrowserManager().getDriver();
                break;
            case HEADLESS_FIREFOX:
                driver = new FirefoxHeadlessBrowserManager().getDriver();
                break;
            case HEADLESS_EDGE:
                driver = new EdgeHeadlessBrowserManager().getDriver();
                break;
            case IE:
                driver = new IEBrowserManager().getDriver();
                break;
            default:
                throw new RuntimeException("Browser is invalid!");
        }

        return driver;
    }
}
