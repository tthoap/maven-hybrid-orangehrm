package enviromentFactory;

import org.openqa.selenium.WebDriver;

public interface EnvironmentFactory {
    public abstract WebDriver createDriver();
}
