package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxHeadlessBrowserManager implements BrowserFactory {
    @Override
    public WebDriver getDriver() {
        FirefoxOptions FFheadOptions = new FirefoxOptions();
        FFheadOptions.addArguments("-headless");
        FFheadOptions.addArguments("window-size=1920,1080");
        return new FirefoxDriver(FFheadOptions);
    }
}
