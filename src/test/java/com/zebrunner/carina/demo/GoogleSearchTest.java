package com.zebrunner.carina.demo;

import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.demo.gui.pages.common.HomePageBase;
import com.zebrunner.carina.utils.R;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class GoogleSearchTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(GoogleSearchTest.class);

    @Test
    public void openGoogleSearchSelenoid() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setCapability("selenoid:options", Map.of("enableVideo", true));
        RemoteWebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(R.CONFIG.get("selenium_url")), browserOptions);
            driver.get(R.CONFIG.get("url"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs("Google"));

            logger.info("Title: {}", driver.getTitle());
        } catch (MalformedURLException e) {
            logger.error("Malformed URL Exception", e);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for title to change", e);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void openGoogleSearchWithFirefox() {
        FirefoxOptions browserOptions = new FirefoxOptions();

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(R.CONFIG.get("selenium_url")), browserOptions);
            driver.get(R.CONFIG.get("url"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs("Google"));

            logger.info("Title: {}", driver.getTitle());
        } catch (MalformedURLException e) {
            logger.error("Malformed URL Exception", e);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for title to change", e);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    public void googleSearchWithGGR() {
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertFalse(homePage.isPageOpened(), "Home page is opened");
    }
    }


