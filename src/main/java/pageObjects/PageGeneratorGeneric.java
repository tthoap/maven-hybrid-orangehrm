package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PageGeneratorGeneric {
    public static <T extends BasePage> T getPage(Class<T> pageClass, WebDriver driver){
        try {
            //Lấy contructor nhận WebDriver
            Constructor<T> constructor = pageClass.getConstructor(WebDriver.class);
            //tạo instance mới của pageClass
            return constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Can not initiate Page bject class: " + pageClass.getSimpleName(), e);
        }
    }
}
