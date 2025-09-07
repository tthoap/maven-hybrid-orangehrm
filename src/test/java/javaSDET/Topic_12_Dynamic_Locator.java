package javaSDET;

import org.testng.annotations.Test;

public class Topic_12_Dynamic_Locator {

//    public static void main(String[] args) {
//        System.out.println(String.format("//a[text()='%s']", "Contact Details"));
//        System.out.println(String.format("//a[text()='%s']", "Salary"));
//        System.out.println(String.format("//a[text()='%s']", "Job"));
//    }

    @Test
    public void testDynamicLocator() {
        String contactDetailPage = "//a[text()='Contact Details']";
        String jobPage = "//a[text()='Job']";
        String salaryPage = "//a[text()='Salary']";
        openPage(contactDetailPage);
        openPage(jobPage);
        openPage(salaryPage);

        String dynamicPageOneParam = "//a[text()='%s']";
        String dynamicPageTwoParam = "//div[@class='orangehrm-%s-employee-navigation']//a[text()='%s']";
        String dynamicPageThreeParam = "//td[@data-key='%s' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='407124']";

        openPageByName(dynamicPageOneParam, "Contact Details");
        openPageByName(dynamicPageOneParam, "Job");
        openPageByName(dynamicPageOneParam, "Salary");

        openPageByName(dynamicPageTwoParam, "edit", "Salary");
        openPageByName(dynamicPageThreeParam, "females", "384187","Afghanistan" );


    }

    private void openPage(String pageLocator) {
        System.out.println("CLick to Page: " + pageLocator);
    }
//
//    public void openPageByName(String pageLocator, String pageName){
//       pageLocator = String.format(pageLocator, pageName);
//        System.out.println("Click to Page: " + pageLocator);
//    }
//    public void openPageByNameTwoParam(String pageLocator, String pageFunction, String pageName){
//        System.out.println("Click to Page: " + String.format(pageLocator, pageFunction, pageName));
//    }
//    public void openPageByNameThreeParam(String pageLocator, String firstParam, String secondParam, String thirdParam){
//        System.out.println("Click to Page: " + String.format(pageLocator, firstParam, secondParam, thirdParam));
//    }
    public void openPageByName(String pageLocator, String... restParams){
        System.out.println("Click to Page: " + String.format(pageLocator, (Object[]) restParams));
    }

}
