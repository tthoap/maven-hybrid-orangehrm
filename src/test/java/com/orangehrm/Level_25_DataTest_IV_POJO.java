package com.orangehrm;

import core.BaseTest;
import core.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
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
import testdata.orangehrm.Employee_Data;

@Slf4j
public class Level_25_DataTest_IV_POJO extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL) {
        driver = getBrowserDriver(browserName, appURL);
        employee_data =  Employee_Data.getEmployee_Data();


        loginPage = PageGenerator.getPage(LoginPO.class, driver);
        employeeID = String.valueOf(getRandomNumber());
        employee_data.setFirstName("John");
        employee_data.setLastName("Doe");
        employee_data.setPassword("Automation@123");
        employee_data.setUserName("john.doe");

        loginPage.enterToTextboxByLabel(driver, "Username", GlobalConstants.ADMIN_ORANGE_USERNAME);
        loginPage.enterToTextboxByLabel(driver, "Password", GlobalConstants.ADMIN_ORANGE_PASSWORD);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));

    }

    @Test
    public void Employee_01_CreateNewEmployee() {

        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        //Add new employee with enabled login Info
        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePO.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employee_data.getFirstName());
        addEmployeePage.enterToTextboxByName(driver, "lastName", employee_data.getLastName());
        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.checkToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", employee_data.getUserName());
        addEmployeePage.enterToTextboxByLabel(driver, "Password", employee_data.getPassword());
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password",  employee_data.getPassword());

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employee_data.getFirstName());
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employee_data.getLastName());
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

    }



    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowser();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailPO personalDetailPage;
    private Employee_Data employee_data;
    private String employeeID;


}
