package browserFactory;

import core.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        if(!GlobalConstants.OS_NAME.toLowerCase().startsWith("mac")){
            throw new BrowserNotSupportException("Safari is not supported on: " + GlobalConstants.OS_NAME);
        }
        SafariOptions safariOptions = new SafariOptions();
        return new SafariDriver(safariOptions);
    }
}
