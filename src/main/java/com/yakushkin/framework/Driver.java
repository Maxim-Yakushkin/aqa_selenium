package com.yakushkin.framework;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Arrays;

public enum Driver {

    EDGE("edge", new EdgeDriverCreator()),
    CHROME("chrome", new ChromeDriverCreator());

    private final String driverName;
    private final WebDriverCreator<RemoteWebDriver> webDriverCreator;

    Driver(String driverName, WebDriverCreator<RemoteWebDriver> webDriverCreator) {
        this.driverName = driverName;
        this.webDriverCreator = webDriverCreator;
    }

    public static Driver getWebDriverByName(String driverName) {
        return Arrays.stream(Driver.values())
                .filter(driver -> driver.driverName.equals(driverName))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Driver '" + driverName + "' is not specified in Driver enum"));
    }

    public String getDriverName() {
        return driverName;
    }

    public WebDriverCreator<RemoteWebDriver> getWebDriverCreator() {
        return webDriverCreator;
    }
}
