package com.yakushkin.pageobject.onliner;

import com.yakushkin.pageobject.BasePage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.yakushkin.url.OnlinerBaseUrl.CATALOG_PAGE_URL;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class CatalogPage extends BasePage {

    private static final String CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH = "//li[@class='catalog-navigation-classifier__item ']";
    private static final String CATALOG_CLASSIFIER_XPATH_PATTERN = "//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'%s')]";
    private static final String COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN = "//div[@class='catalog-navigation-list__aside-title' and normalize-space(text())='%s']";
    private static final String ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN = "//div[normalize-space(text())='%s']/parent::*//a/span";
    private static final String CATEGORY_XPATH_PATTERN = "//div[contains(text(),'%s')]/parent::*//span[contains(text(),'%s')]";

    @Override
    public CatalogPage open() {
        navigateTo(CATALOG_PAGE_URL.getUrl());
        return this;
    }

    public List<WebElement> getAllCatalogNavigationClassifiers() {
        return findElementsWithWaiting(xpath(CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH));
    }

    public void areCatalogNavigationClassifiersDisplayed() {
        areElementsDisplayed(xpath(CATALOG_NAVIGATION_CLASSIFIER_ITEM_XPATH));
    }

    public CatalogPage clickOnComputersAndNetworksClassifier() {
        final WebElement computersAndNetworksClassifier = findElementWithWaiting(xpath(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Компьютеры")));
        computersAndNetworksClassifier.click();
        return this;
    }


    public CatalogPage clickOnElectronicsClassifier() {
        final WebElement computersAndNetworksClassifier = findElementWithWaiting(xpath(String.format(CATALOG_CLASSIFIER_XPATH_PATTERN, "Электроника")));
        computersAndNetworksClassifier.click();
        return this;
    }

    public CategoryPage clickOnHeadPhoneCategory() {
        final WebElement headPhoneCategory = findElementWithWaiting(xpath(String.format(CATEGORY_XPATH_PATTERN, "Аудиотехника", "Наушники")));
        headPhoneCategory.click();
        return new CategoryPage();
    }

    public CatalogPage moveToAccessoriesAsideTitle() {
        final WebElement element = findElementWithWaiting(xpath(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие")));
        getUtilWebElement().moveToElement(element);
        return this;
    }

    public CatalogPage moveToAudioEquipmentAsideTitle() {
        final WebElement element = findElementWithWaiting(xpath(String.format(COMPUTER_AND_NETWORKS_VERTICAL_MENU_XPATH_PATTERN, "Аудиотехника")));
        getUtilWebElement().moveToElement(element);
        return this;
    }

    public List<WebElement> getCategoriesForAccessories() {
        return findElementsWithWaiting(xpath(String.format(ALL_CATEGORIES_BY_POINT_FROM_VERTICAL_MENU_XPATH_PATTERN, "Комплектующие")));
    }

    public static List<String> getCategoryTitles(List<WebElement> categories) {
        return categories.stream()
                .map(categoryLink -> categoryLink.findElement(className("catalog-navigation-list__dropdown-title")))
                .map(WebElement::getText)
                .toList();
    }

    public static List<String> getPartOfCategoryDescription(int indexOfDescriptionPart, List<WebElement> categories) {
        return categories.stream()
                .map(categoryLink -> categoryLink.findElement(className("catalog-navigation-list__dropdown-description")).getText())
                .map(description -> description.split(" ")[indexOfDescriptionPart])
                .toList();
    }

    public boolean isComputerAndNetworksVerticalMenuDisplayed() {
        return isElementDisplayed(xpath("//div[contains(@class,'catalog-navigation-list__aside_active')]" +
                                        "/div[@class='catalog-navigation-list__aside-list']"));
    }

    public List<WebElement> getComputerAndNetworksVerticalMenuPoints() {
        return findElementsWithWaiting(xpath("" +
                                             "//div[contains(@class,'aside catalog-navigation-list__aside_active')]" +
                                             "//div[contains(@class,'catalog-navigation-list__aside-list')]" +
                                             "//div[contains(@class,'title')]"));
    }
}
