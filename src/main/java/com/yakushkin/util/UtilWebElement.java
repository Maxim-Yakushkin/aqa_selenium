package com.yakushkin.util;

import com.yakushkin.framework.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class UtilWebElement {

    private final WebDriver driver;

    public UtilWebElement() {
        this.driver = DriverManager.getWebDriver();
    }

    public void moveToElement(WebElement element) {
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }

    public static List<String> getTextFromWebElementList(List<WebElement> webElements) {
        return webElements.stream()
                .map(WebElement::getText)
                .toList();
    }
}
