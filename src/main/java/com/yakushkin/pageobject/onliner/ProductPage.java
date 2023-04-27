package com.yakushkin.pageobject.onliner;

import com.yakushkin.pageobject.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class ProductPage extends BasePage {
    public List<WebElement> getProductCards() {
        return findElements(xpath("//div[contains(@class,'schema-product__group')]"));
    }
}
