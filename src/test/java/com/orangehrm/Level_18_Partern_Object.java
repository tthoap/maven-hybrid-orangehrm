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

public class Level_18_Partern_Object extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL){
        driver = getBrowserDriver(browserName, appURL);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
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
        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class,driver);
        personalDetailPage.isToastMessageDisplayed(driver,"Successfully Updated");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"),employeeFirstname);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"),employeeLastname);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"),employeeID);

        personalDetailPage.selectDropdownByLabel(driver,"Nationality","Chadian");
        personalDetailPage.selectDropdownByLabel(driver,"Marital Status","Single");
        personalDetailPage.clickToRadioButtonByLabel(driver,"Male");
        personalDetailPage.clickToRadioButtonByLabel(driver,"Female");

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


}
