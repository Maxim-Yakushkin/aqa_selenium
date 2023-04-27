package com.yakushkin.pageobject.onliner;

import com.yakushkin.pageobject.BasePage;

import static org.openqa.selenium.By.xpath;

public class MainPage extends BasePage {
    private static final String MAIN_NAVIGATION_SECTION_XPATH_PATTERN = "//ul[@class='b-main-navigation']" +
                                                                        "//span[text()='%s']/parent::*";
    final String pageUrl = "https://onliner.by";

    public MainPage open() {
        navigateTo(pageUrl);
        return new MainPage();
    }

    public CatalogPage goToCatalogSection() {
        clickOnElement(xpath(String.format(MAIN_NAVIGATION_SECTION_XPATH_PATTERN, "Каталог")));
        return new CatalogPage();
    }

}
