package com.yakushkin.pageobject.onliner;

import com.yakushkin.pageobject.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class CatalogPage extends BasePage {

    private static final String CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH = "//li[@class='catalog-navigation-classifier__item ']";
    private static final String CATALOG_CLASSIFIER_XPATH_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'%s')]";
    private static final String COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN = "//div[@class='catalog-navigation-list__aside-title' and normalize-space(text())='%s']";
    private static final String ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN = "//div[normalize-space(text())='%s']/parent::*//a/span";
    private static final String CATEGORY_XPATH_PATTERN = "//div[contains(text(),'%s')]/parent::*//span[contains(text(),'%s')]";

    public List<WebElement> getAllCatalogNavigationClassifiers() {
        return findElements(xpath(CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH));
    }

    public boolean areCatalogNavigationClassifiersDisplayed() {
        return areElementsDisplayed(xpath(CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH));
    }

    public CatalogPage clickOnComputersAndNetworksClassifier() {
        final WebElement computersAndNetworksClassifier = findElement(xpath(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Компьютеры")));
        computersAndNetworksClassifier.click();
        return this;
    }


    public CatalogPage clickOnElectronicsClassifier() {
        final WebElement computersAndNetworksClassifier = findElement(xpath(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Электроника")));
        computersAndNetworksClassifier.click();
        return this;
    }

    public ProductPage clickOnHeadPhoneCategory() {
        final WebElement headPhoneCategory = findElement(xpath(String.format(CATEGORY_XPATH_PATTERN,"Аудиотехника","Наушники")));
        headPhoneCategory.click();
        return new ProductPage();
    }

    public CatalogPage moveToAccessoriesAsideTitle() {
        final WebElement element = findElement(xpath(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие")));
        new Actions(getDriver())
                .moveToElement(element)
                .perform();
        return this;
    }

    public CatalogPage moveToAudioEquipmentAsideTitle() {
        final WebElement element = findElement(xpath(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Аудиотехника")));
        new Actions(getDriver())
                .moveToElement(element)
                .perform();
        return this;
    }

    public List<WebElement> getCategoriesForAccessories() {
        return findElements(xpath(String.format(ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие")));
    }

    public boolean isComputerAndNetworksVerticalMenuDisplayed() {
        return isElementDisplayed(xpath("//div[contains(@class,'catalog-navigation-list__aside_active')]" +
                                        "/div[@class='catalog-navigation-list__aside-list']"));
    }

    public List<WebElement> getComputerAndNetworksVerticalMenuPoints() {
        return findElements(xpath("" +
                                  "//div[contains(@class,'aside catalog-navigation-list__aside_active')]" +
                                  "//div[contains(@class,'catalog-navigation-list__aside-list')]" +
                                  "//div[contains(@class,'title')]"));
    }
}
