package com.orangehrm;

import core.BaseTest;
import dataTest.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
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
public class Level_29_EnviromentFactory extends BaseTest {
    @Parameters({"enviromentName","serverName", "osName","osVersion","browserName", "browserVersion", "ipAddress", "port" })
    @BeforeClass
    public void beforeClass(@Optional("local") String enviromentName, @Optional("dev") String serverName, @Optional("window") String osName, @Optional("10") String osVersion,@Optional("chrome") String browserName,@Optional("latest") String browserVersion,@Optional("localhost") String ipAddress,@Optional("4444") String port) {
        propertiesConfig = PropertiesConfig.getProperties(serverName);
        driver = getBrowerDriver(enviromentName, propertiesConfig.getApplicationUrl(), osName, osVersion, browserName, browserVersion, ipAddress, port);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);
        employeeData = Employee.getEmployee();
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("employees");

        employeeID = String.valueOf(getRandomNumber());
        employeeUsername = excelConfig.getCellData("UserName",1) + getRandomNumber();

        loginPage.enterToTextboxByLabel(driver, "Username", propertiesConfig.getApplicationUserName());
        loginPage.enterToTextboxByLabel(driver, "Password", propertiesConfig.getApplicationPassword());
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);
        dashboardPage.sleepInSecond(10);

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
