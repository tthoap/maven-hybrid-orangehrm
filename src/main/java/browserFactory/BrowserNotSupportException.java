package browserFactory;

public class BrowserNotSupportException extends IllegalStateException {
    public BrowserNotSupportException(String browserName) {
        super(String.format("Browser '%s' not supported", browserName));
    }
}
