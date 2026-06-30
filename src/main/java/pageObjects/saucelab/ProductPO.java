package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPO extends BasePage {
    private WebDriver driver;

    public ProductPO(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy(String sortCretia) {
        waitElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN, sortCretia);
    }

    public String getSortSelectedItemText(){
        waitElementVisible(driver, ProductPageUI.SORT_DROPDOWN);
        return getSelectedItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN) ;
    }

    public boolean isProductNameSortByAscending() {
        List<WebElement> productName = getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        //Khai báo mảng danh sách A
        ArrayList<String>  productList = new ArrayList<String>();


        //Dùng vòng lặp lấy product nám text lưu vào danhs sách A
        System.out.println("SORT TÊN TĂNG DẦN");
        for (WebElement product : productName) {
            System.out.println(product.getText());
            productList.add(product.getText());
        }
        //Khai báo mảng danh sách B lấy dữ liệu từ A qua
        ArrayList<String>  sortedList = new ArrayList<String>();
        for(String product : productList){
            sortedList.add(product);
        }

        //Sort Ascending danh sách B
        Collections.sort(sortedList);
        return productList.equals(sortedList);
    }

    public boolean isProductNameSortByDescending() {
        List<WebElement> productName = getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        //Khai báo mảng danh sách A
        ArrayList<String>  productList = new ArrayList<String>();


        //Dùng vòng lặp lấy product nám text lưu vào danhs sách A
        System.out.println("SORT TÊN GIẢM DẦN");
        for (WebElement product : productName) {
            System.out.println(product.getText());
            productList.add(product.getText());
        }
        //Khai báo mảng danh sách B lấy dữ liệu từ A qua
        ArrayList<String>  sortedList = new ArrayList<String>();
        for(String product : productList){
            sortedList.add(product);
        }

        //Sort Ascending danh sách B
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortByAscending() {
        List<WebElement> productPrice = getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        //Khai báo mảng danh sách A
        ArrayList<Float>  productList = new ArrayList<Float>();


        //Dùng vòng lặp lấy product price text lưu vào danhs sách A
        System.out.println("SORT TĂNG DẦN");
        for (WebElement product : productPrice) {
            System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$", "")));
        }
        //Khai báo mảng danh sách B lấy dữ liệu từ A qua
        ArrayList<Float>  sortedList = new ArrayList<Float>();
        for(Float product : productList){
            sortedList.add(product);
        }

        //Sort Ascending danh sách B
        Collections.sort(sortedList);
        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortByDescending() {
        List<WebElement> productPrice = getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        //Khai báo mảng danh sách A
        ArrayList<Float>  productList = new ArrayList<Float>();


        //Dùng vòng lặp lấy product price text lưu vào danhs sách A
        System.out.println("SORT GIẢM DẦN");
        for (WebElement product : productPrice) {
            System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$", "")));
        }
        //Khai báo mảng danh sách B lấy dữ liệu từ A qua
        ArrayList<Float>  sortedList = new ArrayList<Float>();
        for(Float product : productList){
            sortedList.add(product);
        }

        //Sort Ascending danh sách B
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        return productList.equals(sortedList);
    }
}
