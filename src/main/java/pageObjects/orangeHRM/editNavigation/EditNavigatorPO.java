package pageObjects.orangeHRM.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangeHRM.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPO extends BasePage {
    WebDriver driver;

    public EditNavigatorPO(WebDriver driver) {
        this.driver = driver;
    }

    //9 Page nằm trong Edit employee
    public JobPO openJobPage() {
        waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
        clickToElement(driver,EditNavigatorPageUI.JOB_LINK);
        return PageGenerator.getPage(JobPO.class, driver);
    }

    public ContactDetailPO openContactDetailPage() {
        waitElementClickable(driver,EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver,EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        return PageGenerator.getPage(ContactDetailPO.class, driver);
    }

    public PersonalDetailPO openPersonalPage() {
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        return PageGenerator.getPage(PersonalDetailPO.class, driver);
    }

    public DependentsPO openDependentsPage() {
        waitElementClickable(driver, EditNavigatorPageUI.DEPENDENTS_LINK);
        clickToElement(driver, EditNavigatorPageUI.DEPENDENTS_LINK);
        return PageGenerator.getPage(DependentsPO.class, driver);
    }


}
