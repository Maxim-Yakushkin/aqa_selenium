package com.yakushkin.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static java.time.Duration.ofSeconds;

public class DriverManager {

    private static final ThreadLocal<RemoteWebDriver> DRIVER = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        if (DRIVER.get() == null) {
            setWebDriver();
        }

        return DRIVER.get();
    }

    public static void setWebDriver() {
        setDriverOptions(Driver.getWebDriverByName(System.getProperty("webDriverName"))
                .getWebDriverCreator()
                .create());
    }

    private static void setDriverOptions(RemoteWebDriver webDriver) {
        DRIVER.set(webDriver);
        DRIVER.get().manage().window().maximize();
        DRIVER.get().manage().timeouts().implicitlyWait(ofSeconds(15));
        DRIVER.get().manage().timeouts().pageLoadTimeout(ofSeconds(15));
    }

    public static void closeBrowser() {
        DRIVER.get().close();
        DRIVER.remove();
    }
}
