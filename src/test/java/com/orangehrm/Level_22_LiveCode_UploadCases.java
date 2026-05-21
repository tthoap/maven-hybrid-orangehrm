package com.orangehrm;

import core.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
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
public class Level_22_LiveCode_UploadCases extends BaseTest {

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
    public void Employee_01_CreateNewEmployeeThenUpdateAvatar() {
        //Tạo mới (enable login info)
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
    }

    @Test
    public void Employee_02_UpdateEployeeAvatar() {
        personalDetailPage.clickToProfileImage(driver);
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        //Get size của hình trước khi upload
        Dimension oldProfileImageFile = personalDetailPage.getProfileNatureImageSize();

        //Invalid - File Type
        personalDetailPage.uploadMultipleFiles(driver,"zip.zip");
        verifyEquals(personalDetailPage.getErrorMessage(driver), "File type not allowed");
        personalDetailPage.sleepInSecond(2);
        personalDetailPage.uploadMultipleFiles(driver,"pdf.pdf");
        verifyEquals(personalDetailPage.getErrorMessage(driver), "File type not allowed");
        personalDetailPage.sleepInSecond(2);
        personalDetailPage.uploadMultipleFiles(driver,"txt.txt");
        verifyEquals(personalDetailPage.getErrorMessage(driver), "File type not allowed");
        personalDetailPage.sleepInSecond(2);
        personalDetailPage.uploadMultipleFiles(driver,"DOC.docx");
        verifyEquals(personalDetailPage.getErrorMessage(driver), "File type not allowed");
        personalDetailPage.sleepInSecond(2);

        //Maximum size file
        personalDetailPage.uploadMultipleFiles(driver,"2Mb.jpg");
        verifyEquals(personalDetailPage.getErrorMessage(driver), "Attachment Size Exceeded");
        personalDetailPage.sleepInSecond(2);

        //Valid file Types
        personalDetailPage.uploadMultipleFiles(driver,"gif.gif");
        personalDetailPage.clickToButtonByText(driver, "Save");
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        //Lấy kích thước sau khi upload thành công
        Dimension newProfileImageFile = personalDetailPage.getProfileNatureImageSize();
        verifyNotEquals(oldProfileImageFile, newProfileImageFile);

        personalDetailPage.uploadMultipleFiles(driver,"30Kb.png");
        personalDetailPage.clickToButtonByText(driver, "Save");
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));

        Dimension new1ProfileImageFile = personalDetailPage.getProfileNatureImageSize();
        verifyNotEquals( newProfileImageFile, new1ProfileImageFile);

        //Maximum demension (3375x6000)
        personalDetailPage.uploadMultipleFiles(driver,"740Kb.jpg");
        personalDetailPage.clickToButtonByText(driver, "Save");
        personalDetailPage.isToastMessageDisplayed(driver, "Successfully Updated");
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        Dimension new2ProfileImageFile = personalDetailPage.getProfileNatureImageSize();
        verifyNotEquals(new1ProfileImageFile, new2ProfileImageFile);

        //Verify upload thành công


    }


    @Test
    public void Employee_02_UploadAvatar() {
        //File Type



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
        closeBrowser();
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
