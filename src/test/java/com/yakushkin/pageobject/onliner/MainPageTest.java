package com.yakushkin.pageobject.onliner;

import com.yakushkin.framework.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MainPageTest {

    private MainPage mainPage;

    @BeforeClass
    void init() {
        mainPage = new MainPage();
    }

    @AfterClass
    void closeAndClean() {
        DriverManager.closeBrowser();
    }

    @Test
    public void openMainPage() {
        mainPage.open();
    }

    @Test
    public void goToCatalogSection() {
        mainPage
                .open()
                .goToCatalogSection();
    }
}