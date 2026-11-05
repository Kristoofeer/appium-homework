package ru.netology.appiumhomework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    private AppiumDriver driver;
    private MainScreen mainScreen;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 6");
        capabilities.setCapability("automationName", "UiAutomator2");

        // вместо пути к apk
        capabilities.setCapability("appPackage", "ru.netology.testing.uiautomator");
        capabilities.setCapability("appActivity", "ru.netology.testing.uiautomator.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        mainScreen = new MainScreen(driver);
    }

    @Test
    public void testEmptyString() {
        String initialText = mainScreen.textToBeChanged.getText();

        mainScreen.inputField.sendKeys("   "); // Отправляем пробелы
        mainScreen.changeButton.click();

        String afterText = mainScreen.textToBeChanged.getText();
        Assertions.assertEquals(initialText, afterText, "Текст не должен был измениться!");
    }

    @Test
    public void testOpenNewActivity() {
        String newText = "Привет, новая активити!";

        mainScreen.inputField.sendKeys(newText);
        mainScreen.activityButton.click();

        String actualText = mainScreen.newActivityText.getText();
        Assertions.assertEquals(newText, actualText, "Текст на новой Activity не совпадает!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}