package com.orangehrm;

import core.BaseTest;
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

@Slf4j
public class Level_22_LiveCode_EmployeeCases extends BaseTest {

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appURL) {
        driver = getBrowserDriver(browserName, appURL);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        adminUsername = "automationfc";
        adminPassword = "Beocon@123";
        employeeUsername = "Usertest" + getRandomNumber();
        employeeUsername02 = "Usertest" + getRandomNumber();
        employeeID = String.valueOf(getRandomNumber());
        employeePassword = "Beocon@123";
        employeeFirstname = "John";
        employeeLastname = "Terry";
        employeeMiddlename = "Smith";
        newEmployeeFirstname = "Johnnew";
        newEmployeeLastname = "Terrynew";
        newEmployeeMiddlename = "Smithnew";
        driverLisenceNumber = "vn" + getRandomNumber();

    }

    @Test
    public void Employee_01_CreateNewEmployee() {
        //Tạo mới (enable login info) sau đó login bằng user đã tạo
        //Tạo mới và login với user mới tạo
        //Xóa employee đi (admin)
        //Login lại với employee
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

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstname);
        addEmployeePage.enterToTextboxByName(driver, "middleName", employeeMiddlename);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastname);
        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.checkToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstname);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastname);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        //Logout
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bằng employee bằng user mới tạo
        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));

        //Vào Dashboard để verify những menu nào hiển thị
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));
        dashboardPage.sleepInSecond(2);
    }


    @Test
    public void Employee_02_DeleteEmployee() {

        //Logout employee để login lại bằng admin
        loginPage = dashboardPage.clickLogoutOnTopMenu(driver);
        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        //Vào PIM search user để delete
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        employeeListPage.enterEmployeeById(driver, employeeID);
        employeeListPage.clickToSearchButtonInMenuByText(driver, "Employee Information");

        verifyTrue(employeeListPage.isSearchedEployeeDisplayedBycolumeID(driver, "Id", employeeID));

        //Delete employee đã tìm
        employeeListPage.clickToActionIconByEmployeeID(driver, employeeID, "bi-trash");
        employeeListPage.clickYesOnPopupToDeleteEmployee(driver);
        employeeListPage.isToastMessageDisplayed(driver, "Successfully Deleted");
        employeeListPage.isToastMessageDisplayed(driver, "No Records Found");

        //Login lại bằng user đã xóa sẽ fail
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);
        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.clickToButtonByText(driver, "Login");
        loginPage.sleepInSecond(3);
        verifyEquals(loginPage.getErrorMessageByText(driver, "Invalid credentials"), "Invalid credentials");

        //Login lại bằng admin vẫn thành công
        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);
        dashboardPage.sleepInSecond(2);

    }

    @Test
    public void Employee_03_CreateNewEmployeeWithoutLoginInfo() {
        //Tạo mới nhưng ko có tạo thông tin login
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        //Add new employee wihtout login info
        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePO.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstname);
        addEmployeePage.enterToTextboxByName(driver, "middleName", employeeMiddlename);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastname);
        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstname);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastname);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);
    }



    @Test
    public void Employee_04_EditEmployeeByAdmin() {
        personalDetailPage.enterToTextboxByName(driver, "firstName", newEmployeeFirstname);
        personalDetailPage.enterToTextboxByName(driver, "firstName", newEmployeeLastname);
        personalDetailPage.enterToTextboxByName(driver, "firstName", newEmployeeMiddlename);
        personalDetailPage.enterToTextboxByLabel(driver, "Driver's License Number", driverLisenceNumber);
        personalDetailPage.selectDropdownByLabel(driver, "Nationality", "Algerian");
        personalDetailPage.selectDropdownByLabel(driver, "Marital Status", "Single");
        personalDetailPage.clickToRadioButtonByLabel(driver, "Male");
        personalDetailPage.clickToButtonByText(driver, "Save");
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        //Logout admin và login lại bằng emloyee để verify đã update thành công
        personalDetailPage.clickLogoutOnTopMenu(driver);
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);


        //To be continue..

    }

    //@Test
    public void Employee_04_CreateNewEmployeeWithDisabledLogin() {
        //Tạo mới có login info nhưng disable login sẽ login fail
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);
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

        //Add new employee with login Info
        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePO.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstname);
        addEmployeePage.enterToTextboxByName(driver, "middleName", employeeMiddlename);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastname);
        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        //Provide login info but disable login
        addEmployeePage.checkToCheckboxByLabel(driver, "Create Login Details");
        addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUsername02);
        addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);
        addEmployeePage.clickToRadioButtonByLabel(driver, "Disabled");

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        personalDetailPage.sleepInSecond(2);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstname);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeLastname);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        //Login bằng employee bằng user mới tạo sẽ fail
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);
        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername02);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.clickToButtonByText(driver, "Login");
        loginPage.sleepInSecond(3);
        verifyEquals(loginPage.getErrorMessageByText(driver, "Account disabled"), "Account disabled");
    }

    @Test
    public void Employee_02_UploadAvatar() {
        //File Type
        //Maximum size
        //Maximum demension

    }

    @Test
    public void Employee_03_PersonalDetail() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_04_ContactDetail() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_05_EmergencyDetail() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_06_AssignedDependents() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_07_Edit_View_Job() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_08_Edit_View_Salary() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_09_Edit_View_Tax() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_10_Qualification() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @Test
    public void Employee_11_SearchEmployee() {
        //Edit employee với quyền admin
        //View với quyền employee
        //Edit với quyền employee
        //View với quyền admin
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //closeBrowser();
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPage;
    private AddEmployeePO addEmployeePage;
    private PersonalDetailPO personalDetailPage;
    private String employeeID, adminUsername, adminPassword, employeeFirstname, employeeMiddlename, employeeLastname;
    private String newEmployeeFirstname, newEmployeeMiddlename, newEmployeeLastname, driverLisenceNumber;
    private String employeeUsername, employeeUsername02, employeePassword;


}
