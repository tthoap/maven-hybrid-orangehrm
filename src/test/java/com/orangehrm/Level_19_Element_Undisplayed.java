package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPO;

public class Level_19_Element_Undisplayed extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
        employeeUsername = "Usertest" + getRandomNumber();
        employeePassword = "Beocon@123";
        employeeFirstname = "John";
        employeeLastname = "Terry";
        employeeMiddlename = "Smith";
    }


    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToTextboxByLabel(driver,"Username",adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Trường hợp 1: Element có hiển thị trên UI và có trong DOM/HTML (Displayed/Visible)
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Maintenance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Buzz"));

        dashboardPage.clickToModuleByTextInMenuItem(driver,"PIM");
        employeeListPage =  PageGenerator.getPage(EmployeeListPO.class,driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));


        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage =  PageGenerator.getPage(AddEmployeePO.class,driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver,"firstName",employeeFirstname);
        addEmployeePage.enterToTextboxByName(driver,"middleName",employeeMiddlename);
        addEmployeePage.enterToTextboxByName(driver,"lastName",employeeLastname);
        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");


        addEmployeePage.checkToCheckboxByLabel(driver,"Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver,"Username", employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver,"Password", employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver,"Confirm Password", employeePassword);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class,driver);
        personalDetailPage.isToastMessageDisplayed(driver,"Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"),employeeFirstname);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"),employeeLastname);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"),employeeID);

        //Logout
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bằng employee bằng user mới tạo
        loginPage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));

        //Vào Dashboard để verify những menu nào hiển thị

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Buzz"));

        //Case 3: Element không hiển thị trên UI và không có trong DOM/HTML (Displayed/Visible)
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver,"Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver,"PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver,"Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver,"Maintenance"));
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
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeMiddlename, employeeLastname;
    private String employeeUsername, employeePassword;


}
