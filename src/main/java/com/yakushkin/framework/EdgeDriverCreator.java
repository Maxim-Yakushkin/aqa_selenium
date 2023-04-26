package com.yakushkin.framework;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EdgeDriverCreator implements WebDriverCreator<RemoteWebDriver> {

    @Override
    public EdgeDriver create() {
        return new EdgeDriver();
    }
}
