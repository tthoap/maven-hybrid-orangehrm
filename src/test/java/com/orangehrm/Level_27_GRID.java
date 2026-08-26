package com.orangehrm;

import core.BaseTest;
import dataTest.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
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
import utilities.IEnviroment;
import utilities.PropertiesConfig;

@Slf4j
public class Level_27_GRID extends BaseTest {

    IEnviroment enviroment;
    @Parameters({"environment","browser", "osName", "ipAddress", "port"})
    @BeforeClass
    public void beforeClass(String environment, String browserName, String osName, String ipAddress, String port) {
        propertiesConfig = PropertiesConfig.getProperties(environment);
        driver = getBrowserDriver(browserName, propertiesConfig.getApplicationUrl(),osName,ipAddress,port);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);
        employeeData = Employee.getEmployee();
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("employees");

//        adminUsername = "automationfc";
//        adminPassword = "Beocon@123";
        employeeID = String.valueOf(getRandomNumber());
        employeeUsername = excelConfig.getCellData("UserName",1) + getRandomNumber();

        loginPage.enterToTextboxByLabel(driver, "Username", propertiesConfig.getApplicationUserName());
        loginPage.enterToTextboxByLabel(driver, "Password", propertiesConfig.getApplicationPassword());
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

    }

    @Test
    public void Employee_01_CreateNewEmployee() {

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
    private PropertiesConfig propertiesConfig;

}
