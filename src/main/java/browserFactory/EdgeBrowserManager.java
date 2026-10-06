package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        //Config for Capabilities here
        return new EdgeDriver(edgeOptions);
    }
}
