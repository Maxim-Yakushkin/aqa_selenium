package com.yakushkin.onliner.framework;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriverCreator<T extends RemoteWebDriver> {

    T create();
}
