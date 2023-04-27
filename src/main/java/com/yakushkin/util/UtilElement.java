package com.yakushkin.util;

import com.yakushkin.framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UtilElement {

    private final WebDriver driver;

    public UtilElement() {
        this.driver = DriverManager.getWebDriver();
    }

    public WebElement findElement(By by) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<WebElement> findElements(By by) {
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
}
