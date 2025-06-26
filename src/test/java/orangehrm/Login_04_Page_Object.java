package orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Login_04_Page_Object extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        loginPage = new LoginPO(driver);

        adminUsername = "Admin";
        adminPassword = "admin123";
        employeeFirstname = "John";
        employeeLastname = "Terry";
    }


    @Test
    public void Login_01_Empty() {
        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);
        loginPage.clickToLoginButton();

        dashboardPage = new DashboardPO(driver);
        dashboardPage.clickToPIMModule();

        employeeListPage = new EmployeeListPO(driver);
        employeeListPage.clickToAddEmployeeButton();

        addEmployeePage = new AddEmployeePO(driver);
        addEmployeePage.enterToFistnameTextbox(employeeFirstname);
        addEmployeePage.enterToLastnameTextbox(employeeLastname);
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clicktoSaveButton();

        personalDetailPage = new PersonalDetailPO(driver);
        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(),employeeFirstname);
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(),employeeLastname);
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
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname;


}
