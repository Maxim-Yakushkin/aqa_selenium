package com.yakushkin.pageobject.onliner;

import com.yakushkin.pageobject.BasePage;

import static com.yakushkin.url.OnlinerBaseUrl.MAIN_PAGE_URL;
import static org.openqa.selenium.By.xpath;

public class MainPage extends BasePage {
    private static final String MAIN_NAVIGATION_SECTION_XPATH_PATTERN = "//ul[@class='b-main-navigation']" +
                                                                        "//span[text()='%s']/parent::*";

    @Override
    public MainPage open() {
        navigateTo(MAIN_PAGE_URL.getUrl());
        return this;
    }

    public CatalogPage goToCatalogSection() {
        clickOnElement(xpath(String.format(MAIN_NAVIGATION_SECTION_XPATH_PATTERN, "Каталог")));
        return new CatalogPage();
    }

}
