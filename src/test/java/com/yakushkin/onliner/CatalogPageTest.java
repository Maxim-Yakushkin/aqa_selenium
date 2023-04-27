package com.yakushkin.onliner;

import com.yakushkin.framework.DriverManager;
import com.yakushkin.pageobject.onliner.CatalogPage;
import com.yakushkin.pageobject.onliner.MainPage;
import com.yakushkin.util.UtilElement;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class CatalogPageTest {

    private MainPage mainPage;
    private CatalogPage catalogPage;

    @BeforeClass
    void initWebDriver() {
        mainPage = new MainPage();
        catalogPage = new CatalogPage();
    }

    @AfterClass
    void closeAndClean() {
        DriverManager.closeBrowser();
    }

    @Test
    void checkThatClassifierMenuContainsNecessaryClassifiers() {
        final List<String> expectedClassifierTitles = Arrays.asList("Электроника", "Компьютеры и сети",
                "Бытовая техника", "На каждый день", "Стройка и ремонт",
                "Дом и сад", "Авто и мото", "Красота и спорт", "Детям и мамам");

        final List<WebElement> allCatalogNavigationClassifiers = mainPage
                .open()
                .goToCatalogSection()
                .getAllCatalogNavigationClassifiers();
        final List<String> actualClassifierTitles = allCatalogNavigationClassifiers.stream()
                .map(WebElement::getText)
                .toList();

        assertThat(actualClassifierTitles).containsAll(expectedClassifierTitles);
        assertThat(catalogPage.areCatalogNavigationClassifiersDisplayed()).isTrue();
    }

    @Test
    void checkThatComputersAndNetworksClassifierContainsNecessaryPointsInVerticalMenu() {
        final List<String> expectedVerticalMenuPointTitles = Arrays.asList("Ноутбуки, компьютеры, мониторы", "Комплектующие");

        final List<WebElement> allVerticalMenuPoints = mainPage
                .open()
                .goToCatalogSection()
                .clickOnComputersAndNetworksClassifier()
                .moveToAccessoriesAsideTitle()
                .getComputerAndNetworksVerticalMenuPoints();

        final List<String> actualVerticalMenuPointTitles = allVerticalMenuPoints.stream()
                .map(WebElement::getText)
                .toList();

        assertThat(catalogPage.isComputerAndNetworksVerticalMenuDisplayed()).isTrue();
        assertThat(actualVerticalMenuPointTitles).containsAll(expectedVerticalMenuPointTitles);
    }

    @Test
    void checkThatAllCategoriesInClassifierContainsNecessaryFiledValues() {
        final List<WebElement> categories = mainPage
                .open()
                .goToCatalogSection()
                .clickOnComputersAndNetworksClassifier()
                .moveToAccessoriesAsideTitle()
                .getCategoriesForAccessories();

        final List<String> categoryTitles = categories.stream()
                .map(categoryLink -> categoryLink.findElement(className("catalog-navigation-list__dropdown-title")))
                .map(WebElement::getText)
                .peek(System.out::println)
                .toList();
        final List<String> categoryCountOfGoods = categories.stream()
                .map(categoryLink -> categoryLink.findElement(className("catalog-navigation-list__dropdown-description")).getText())
                .map(description -> description.split(" ")[0])
                .peek(System.out::println)
                .toList();
        final List<String> categoryStartPrice = categories.stream()
                .map(categoryLink -> categoryLink.findElement(className("catalog-navigation-list__dropdown-description")).getText())
                .map(description -> description.split(" ")[2])
                .peek(System.out::println)
                .toList();

        assertThat(categoryTitles).hasSize(categories.size());
        assertThat(categoryCountOfGoods).hasSize(categories.size());
        assertThat(categoryStartPrice).hasSize(categories.size());
    }

    @Test
    void checkPreviewStructureForEachTradeItemOnPageWithListOfGoods() {
        final UtilElement utilElement = new UtilElement();
        final List<WebElement> productCards = mainPage
                .open()
                .goToCatalogSection()
                .clickOnElectronicsClassifier()
                .moveToAudioEquipmentAsideTitle()
                .clickOnHeadPhoneCategory()
                .getProductCards();

        final List<String> productCardTitles = utilElement.findElements(xpath("//span[contains(@data-bind,'product.full_name')]")).stream()
                .map(WebElement::getText)
                .toList();
        final List<String> productPrices = utilElement.findElements(xpath("//div[not(contains(@class,'schema-product_children'))]" +
                                                                          "/div[contains(@class,'schema-product__part_2')]" +
                                                                          "/div[contains(@class,'schema-product__part_3')]" +
                                                                          "//span[contains(@data-bind,'root.format.minPrice')]")).stream()
                .map(WebElement::getText)
                .toList();
        final List<String> productDescriptions = utilElement.findElements(xpath("//span[contains(@data-bind,'product.description')]")).stream()
                .map(WebElement::getText)
                .toList();
        final List<String> productRatings = utilElement.findElements(xpath("//span[contains(@data-bind,'product.reviews.rating')]")).stream()
                .map(WebElement::getText)
                .toList();
        final List<WebElement> productImages = utilElement.findElements(xpath("//div[@class='schema-product__group']/div/div/div[@class='schema-product__image']"));
        final List<WebElement> productCheckBoxes = utilElement.findElements(xpath("//div[not(contains(@class,'schema-product_children'))]" +
                                                                                  "/div[contains(@class,'schema-product__part_1')]" +
                                                                                  "/div[@class='schema-product__compare']"));

        assertThat(productCardTitles).hasSize(productCards.size());
        assertThat(productPrices).hasSize(productCards.size());
        assertThat(productDescriptions).hasSize(productCards.size());
        assertThat(productRatings).hasSize(productCards.size());
        assertThat(productCheckBoxes).hasSize(productCards.size());
        assertThat(productImages).hasSize(productCards.size());
    }
}
