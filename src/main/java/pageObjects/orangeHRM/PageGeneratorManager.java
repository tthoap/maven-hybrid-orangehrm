package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;
import pageObjects.orangeHRM.editNavigation.ContactDetailPO;
import pageObjects.orangeHRM.editNavigation.PersonalDetailPO;

public class PageGeneratorManager {
    public static LoginPO getLoginPage(WebDriver driver){
        return new LoginPO(driver);
    }

    public static DashboardPO getDashboardPage(WebDriver driver){
        return new DashboardPO(driver);
    }

    public static AddEmployeePO getAddEmployeePage(WebDriver driver){
        return new AddEmployeePO(driver);
    }

    public static EmployeeListPO getEmployeeListPage(WebDriver driver){
        return new EmployeeListPO(driver);
    }

    public static PersonalDetailPO getPersonalDetailPage(WebDriver driver){
        return new PersonalDetailPO(driver);
    }

    public static ContactDetailPO getContactDetailPage(WebDriver driver){
        return new ContactDetailPO(driver);
    }

}
