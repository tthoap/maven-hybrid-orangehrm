package com.orangehrm;

import core.BaseTest;
import dataTest.model.Employee;
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
import utilities.ExcelConfig;

@Slf4j
public class Level_26_Enviroment extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);
        employeeData = Employee.getEmployee();
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("employees");

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
        employeeID = String.valueOf(getRandomNumber());
        employeeUsername = excelConfig.getCellData("UserName",1) + getRandomNumber();

    }

    @Test
    public void Employee_01_CreateNewEmployee() {
        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));

        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        //Add new employee with enabled login Info
        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePO.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", excelConfig.getCellData("FirstName",2));
        addEmployeePage.enterToTextboxByName(driver, "lastName", excelConfig.getCellData("LastName",2));
        addEmployeePage.enterToTextboxByLabel(driver, "Employee Id", employeeID);

        addEmployeePage.checkToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver, "Password", excelConfig.getCellData("Password",2));
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", excelConfig.getCellData("Password",2));

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), excelConfig.getCellData("FirstName",2));
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), excelConfig.getCellData("LastName",2));
//        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

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
    private Employee employeeData;
    private ExcelConfig excelConfig;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeLastname,employeeUsername, employeePassword;


}
