package javaSDET;

public class Topic_01_String {
    public static void main(String[] args) {
        String locator = "xpath=//div[@id='content']//h1[text()='Customers']";
        String[] locatorArr = locator.split("=",2);
        System.out.println(locatorArr[0]);
        System.out.println(locatorArr[1]);
    }
}
