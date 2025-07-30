package com.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Level_01_DRY {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @Test
    public void Login_01_Empty() {
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='username']/parent::div/following-sibling::span")).getText(), "Required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='password']/parent::div/following-sibling::span")).getText(), "Required");
    }

    @Test
    public void Login_02_Invalid_Username() {
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("123456");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-alert-content--error p")).getText(), "Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin123@@@");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-alert-content--error p")).getText(), "Invalid credentials");
    }

    @Test
    public void Login_04_Valid_User_Password() {
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-topbar-header-title h6")).getText(), "Dashboard");
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
