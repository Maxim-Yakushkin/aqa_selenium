package com.yakushkin.onliner;

import com.yakushkin.onliner.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.yakushkin.onliner.framework.Driver.EDGE;
import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.xpath;

public class CatalogPageTest {

    private WebDriver driver;
    private Wait<WebDriver> wait;

    @BeforeClass
    void initWebDriver() {
        System.setProperty("webDriverName", EDGE.getDriverName());
        driver = DriverManager.getWebDriver();
        wait = new WebDriverWait(driver, ofSeconds(30));
    }

    @AfterClass
    void closeAndClean() {
        DriverManager.closeBrowser();
        System.clearProperty("webdriver.edge");
    }

    @Test
    void checkThatClassifierBarContainsNecessaryClassifiers() {
        final List<String> expectedClassifierTitles = Arrays.asList("Электроника", "Компьютеры и сети",
                "Бытовая техника", "На каждый день", "Стройка и ремонт",
                "Дом и сад", "Авто и мото", "Красота и спорт", "Детям и мамам");

        driver.get("https://onliner.by");
        final WebElement catalogLink = driver.findElement(
                xpath("//ul[@class='b-main-navigation']//span[text()='Каталог']/parent::*"));
        catalogLink.click();
        final List<String> actualClassifierTitles = driver.findElements(
                        xpath("//li[@class='catalog-navigation-classifier__item ']")).stream()
                .map(WebElement::getText)
                .toList();

        assertThat(actualClassifierTitles).containsAll(expectedClassifierTitles);
    }

    @Test
    void checkThatComputersAndNetworksClassifierContainsNecessaryPointsInVerticalMenu() {
        final List<String> expectedVerticalMenuPointTitles = Arrays.asList("Ноутбуки, компьютеры, мониторы", "Комплектующие");

        driver.get("https://onliner.by");
        WebElement catalogLink = findElement(xpath("//ul[@class='b-main-navigation']//span[text()='Каталог']/parent::*"));
        catalogLink.click();

        final WebElement computersAndNetworksClassifier = driver.findElement(
                xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'Компьютеры')]"));
        computersAndNetworksClassifier.click();

        final WebElement element = findElement(xpath("//div[@class='catalog-navigation-list__aside-title' and " +
                                                     "normalize-space(text())='Комплектующие']"));
        new Actions(driver)
                .moveToElement(element)
                .perform();

        final WebElement verticalMenu = findElement(
                xpath("//div[contains(@class,'catalog-navigation-list__aside_active')]" +
                      "/div[@class='catalog-navigation-list__aside-list']"));

        final List<String> actualVerticalMenuPointTitles = findElements(
                xpath("" +
                      "//div[contains(@class,'aside catalog-navigation-list__aside_active')]" +
                      "//div[contains(@class,'catalog-navigation-list__aside-list')]" +
                      "//div[contains(@class,'title')]")).stream()
                .map(WebElement::getText)
                .toList();

        assertThat(verticalMenu.isDisplayed()).isTrue();
        assertThat(actualVerticalMenuPointTitles).containsAll(expectedVerticalMenuPointTitles);
    }

    @Test
    void checkThatAllCategoriesInClassifierContainsNecessaryFiledValues() {
        driver.get("https://onliner.by");
        WebElement catalogLink = findElement(xpath("//ul[@class='b-main-navigation']//span[text()='Каталог']/parent::*"));
        catalogLink.click();

        final WebElement computersAndNetworksClassifier = driver.findElement(
                xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'Компьютеры')]"));
        computersAndNetworksClassifier.click();

        final WebElement element = findElement(xpath("//div[@class='catalog-navigation-list__aside-title' and " +
                                                     "normalize-space(text())='Комплектующие']"));
        new Actions(driver)
                .moveToElement(element)
                .perform();

        List<WebElement> categories = findElements(xpath("//div[normalize-space(text())='Комплектующие']/parent::*//a/span"));
        List<String> categoryTitles = categories.stream()
                .map(categoryLink -> categoryLink.findElement(By.className("catalog-navigation-list__dropdown-title")))
                .map(WebElement::getText)
                .toList();
        List<String> categoryCountOfGoods = categories.stream()
                .map(categoryLink -> categoryLink.findElement(By.className("catalog-navigation-list__dropdown-description")).getText())
                .map(description -> description.split(" ")[0])
                .toList();
        List<String> categoryStartPrice = categories.stream()
                .map(categoryLink -> categoryLink.findElement(By.className("catalog-navigation-list__dropdown-description")).getText())
                .map(description -> description.split(" ")[2])
                .toList();

        assertThat(categoryTitles).hasSize(categories.size());
        assertThat(categoryCountOfGoods).hasSize(categories.size());
        assertThat(categoryStartPrice).hasSize(categories.size());
    }

    @Test
    void checkPreviewStructureForEachTradeItemOnPageWithListOfGoods() throws InterruptedException {
        driver.get("https://onliner.by");
        WebElement catalogLink = findElement(xpath("//ul[@class='b-main-navigation']//span[text()='Каталог']/parent::*"));
        catalogLink.click();

        final WebElement computersAndNetworksClassifier = driver.findElement(
                xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(),'Электроника')]"));
        computersAndNetworksClassifier.click();

        final WebElement element = findElement(xpath("//div[@class='catalog-navigation-list__aside-title' and " +
                                                     "normalize-space(text())='Аудиотехника']"));
        new Actions(driver)
                .moveToElement(element)
                .perform();

        WebElement headPhoneCategory = findElement(xpath("//div[contains(text(),'Аудиотехника')]/parent::*//span[contains(text(),'Наушники')]"));
        headPhoneCategory.click();

        List<WebElement> productCards = findElements(xpath("//div[contains(@class,'schema-product__group')]"));
        List<String> productTitles = productCards.stream()
                .map(productCard -> productCard.findElement(xpath("//span[contains(@data-bind,'product.full_name')]")))
                .map(WebElement::getText)
                .toList();
        List<String> productPrices = productCards.stream()
                .map(productCard -> productCard.findElement(xpath("//span[contains(@data-bind,'data.prices')]")).getText())
                .toList();
        List<String> productDescriptions = productCards.stream()
                .map(productCard -> productCard.findElement(xpath("//span[contains(@data-bind,'product.description')]")).getText())
                .toList();
        List<String> productRatings = productCards.stream()
                .map(productCard -> productCard.findElement(xpath("//span[contains(@data-bind,'product.reviews.rating')]")).getText())
                .toList();
        List<WebElement> productCheckBoxes = productCards.stream()
                .map(productCard -> productCard.findElement(xpath("//div[contains(@data-bind,'schema-product__compare')]")))
                .toList();
        productCheckBoxes.forEach(System.out::println);
    }

    private WebElement findElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private List<WebElement> findElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
