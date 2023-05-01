package com.yakushkin.pageobject.onliner;

import com.yakushkin.pageobject.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.yakushkin.util.UtilWebElement.getTextFromWebElementList;
import static org.openqa.selenium.By.xpath;

public class CategoryPage extends BasePage {

    public List<WebElement> getProductCards() {
        return findElementsWithWaiting(xpath("//div[contains(@class,'schema-product__group')]"));
    }

    public List<String> getProductCardTitles() {
        return getTextFromWebElementList(findElementsWithWaiting(
                xpath("//span[contains(@data-bind,'product.full_name')]")));
    }

    public List<String> getProductPrices() {
        return getTextFromWebElementList(findElementsWithWaiting(
                xpath("//div[not(contains(@class,'schema-product_children'))]" +
                      "/div[contains(@class,'schema-product__part_2')]" +
                      "/div[contains(@class,'schema-product__part_3')]" +
                      "//span[contains(@data-bind,'root.format.minPrice')]")));
    }

    public List<String> getProductDescriptions() {
        return getTextFromWebElementList(findElementsWithWaiting(
                xpath("//span[contains(@data-bind,'product.description')]")));
    }

    public List<String> getProductRatings() {
        return getTextFromWebElementList(findElementsWithWaiting(
                xpath("//div[@class='schema-product__rating-group']")));
    }

    public List<WebElement> getProductImages() {
        return findElementsWithWaiting(
                xpath("//div[@class='schema-product__group']/div/div/div[@class='schema-product__image']"));
    }

    public List<WebElement> getProductCheckboxes() {
        return findElementsWithWaiting(
                xpath("//div[not(contains(@class,'schema-product_children'))]" +
                      "/div[contains(@class,'schema-product__part_1')]" +
                      "/div[@class='schema-product__compare']"));
    }
}
