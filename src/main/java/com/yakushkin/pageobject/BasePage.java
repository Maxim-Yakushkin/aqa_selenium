package com.yakushkin.pageobject;

import com.yakushkin.framework.DriverManager;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Data
public abstract class BasePage {

    private final WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getWebDriver();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public WebElement findElement(By by) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> findElements(By by) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public boolean isElementDisplayed(By by) {
        final WebElement element = findElement(by);
        return element.isDisplayed();
    }

    public boolean areElementsDisplayed(By by) {
        final List<WebElement> elements = findElements(by);
        return elements.stream()
                .allMatch(WebElement::isDisplayed);
    }

    public void clickOnElement(By by) {
        final WebElement element = findElement(by);
        element.click();
    }
}
