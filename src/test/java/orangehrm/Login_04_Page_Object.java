package orangehrm;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

import java.time.Duration;

public class Login_04_Page_Object extends BaseTest {


    private static final Logger log = LoggerFactory.getLogger(Login_04_Page_Object.class);

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        loginPage = new LoginPO();
    }


    @Test
    public void Login_01_Empty() {
        loginPage.enterToUsernameTextbox("Admin");
        loginPage.enterToPasswordTextbox("admin123");
        loginPage.clickToLoginButton();

        dashboardPage = new DashboardPO();
        dashboardPage.clickToPIMModule();

        employeeListPage = new EmployeeListPO();
        employeeListPage.clickToAddEmployeeButton();

        addEmployeePage = new AddEmployeePO();
        addEmployeePage.enterToFistnameTextbox("");
        addEmployeePage.enterToLastnameTextbox("");
        employeeID = addEmployeePage.getEmployeeID();

        personalDetailPage = new PersonalDetailPO();
        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }

    @Test
    public void Login_02_Invalid_Username() {
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailPO personalDetailPage;
    private String employeeID;


}
