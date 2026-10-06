package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeHeadlessBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        EdgeOptions edgeHlOptions = new EdgeOptions();
        edgeHlOptions.addArguments("--headless");
        edgeHlOptions.addArguments("window-size=1920,1080");
        return new EdgeDriver(edgeHlOptions);
    }
}
