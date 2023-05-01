package com.yakushkin.pageobject.onliner;

import com.yakushkin.framework.DriverManager;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.yakushkin.helper.MessageHelper.ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE;
import static com.yakushkin.helper.MessageHelper.LIST_OF_CLASSIFIER_TITLES_DOESNT_CONTAIN_EXPECTED_ELEMENT_MESSAGE;
import static com.yakushkin.helper.MessageHelper.VERTICAL_MENU_DOESNT_CONTAIN_EXPECTED_ELEMENT_MESSAGE;
import static com.yakushkin.pageobject.onliner.CatalogPage.getCategoryTitles;
import static com.yakushkin.pageobject.onliner.CatalogPage.getPartOfCategoryDescription;
import static com.yakushkin.util.UtilWebElement.getTextFromWebElementList;
import static org.assertj.core.api.Assertions.assertThat;

public class CatalogPageTest {

    private CatalogPage catalogPage;

    @BeforeClass
    void initWebDriver() {
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

        final List<WebElement> allCatalogNavigationClassifiers = catalogPage
                .open()
                .getAllCatalogNavigationClassifiers();
        final List<String> actualClassifierTitles = getTextFromWebElementList(allCatalogNavigationClassifiers);

        catalogPage.areCatalogNavigationClassifiersDisplayed();
        assertThat(actualClassifierTitles)
                .as(LIST_OF_CLASSIFIER_TITLES_DOESNT_CONTAIN_EXPECTED_ELEMENT_MESSAGE)
                .containsAll(expectedClassifierTitles);
    }

    @Test
    void checkThatComputersAndNetworksClassifierContainsNecessaryPointsInVerticalMenu() {
        final List<String> expectedVerticalMenuPointTitles = Arrays.asList("Ноутбуки, компьютеры, мониторы", "Комплектующие");

        final List<WebElement> allVerticalMenuPoints = catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .moveToAccessoriesAsideTitle()
                .getComputerAndNetworksVerticalMenuPoints();

        final List<String> actualVerticalMenuPointTitles = getTextFromWebElementList(allVerticalMenuPoints);

        assertThat(catalogPage.isComputerAndNetworksVerticalMenuDisplayed()).isTrue();
        assertThat(actualVerticalMenuPointTitles)
                .as(VERTICAL_MENU_DOESNT_CONTAIN_EXPECTED_ELEMENT_MESSAGE)
                .containsAll(expectedVerticalMenuPointTitles);
    }

    @Test
    void checkThatAllCategoriesInClassifierContainsNecessaryFiledValues() {
        final int indexCountOfItemsInCategoryDescription = 0;
        final int indexMinPriceOfItemInCategoryDescription = 2;

        final List<WebElement> categories = catalogPage
                .open()
                .clickOnComputersAndNetworksClassifier()
                .moveToAccessoriesAsideTitle()
                .getCategoriesForAccessories();

        final List<String> categoryTitles = getCategoryTitles(categories);
        final List<String> categoryCountOfGoods = getPartOfCategoryDescription(indexCountOfItemsInCategoryDescription, categories);
        final List<String> categoryStartPrice = getPartOfCategoryDescription(indexMinPriceOfItemInCategoryDescription, categories);

        assertThat(categoryTitles).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(categories.size());
        assertThat(categoryCountOfGoods).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(categories.size());
        assertThat(categoryStartPrice).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(categories.size());
    }

    @Test
    void checkPreviewStructureForEachTradeItemOnPageWithListOfGoods() {
        final CategoryPage categoryPage = catalogPage
                .open()
                .clickOnElectronicsClassifier()
                .moveToAudioEquipmentAsideTitle()
                .clickOnHeadPhoneCategory();

        final List<WebElement> productCards = categoryPage.getProductCards();
        final List<String> productCardTitles = categoryPage.getProductCardTitles();
        final List<String> productPrices = categoryPage.getProductPrices();
        final List<String> productDescriptions = categoryPage.getProductDescriptions();
        final List<String> productRatings = categoryPage.getProductRatings();
        final List<WebElement> productImages = categoryPage.getProductImages();
        final List<WebElement> productCheckboxes = categoryPage.getProductCheckboxes();

        assertThat(productCardTitles).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(productCards.size());
        assertThat(productPrices).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(productCards.size());
        assertThat(productDescriptions).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(productCards.size());
        assertThat(productRatings).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(productCards.size());
        assertThat(productImages).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(productCards.size());
        assertThat(productCheckboxes).as(ACTUAL_AND_EXPECTED_SIZE_DOESNT_MATH_MESSAGE).hasSize(productCards.size());
    }
}
