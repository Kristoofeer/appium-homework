package ru.netology.appiumhomework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    private AppiumDriver driver;
    private MainScreen mainScreen;

    @BeforeEach
    public void setUp() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 6");
        capabilities.setCapability("app", "C:\\Users\\Kristofer\\Desktop\\sample\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
        capabilities.setCapability("automationName", "UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
        mainScreen = new MainScreen(driver);

        // ВАЖНО: Ждем 5 секунд, чтобы приложение точно загрузилось
        Thread.sleep(5000);
    }

    @Test
    public void testEmptyString() {
        // Тест 1: Попытка установки пустой строки

        // Ждем до 15 секунд, пока поле ввода не появится на экране
        org.openqa.selenium.support.ui.WebDriverWait wait =
                new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(mainScreen.inputField));

        String initialText = mainScreen.textToBeChanged.getText();

        mainScreen.inputField.sendKeys("   "); // Отправляем пробелы (пустую строку)
        mainScreen.changeButton.click();

        String afterText = mainScreen.textToBeChanged.getText();
        Assertions.assertEquals(initialText, afterText, "Текст не должен был измениться!");
    }

    @Test
    public void testOpenNewActivity() throws Exception {
        // Тест 2: Открытие текста в новой Activity
        String newText = "Привет, новая активити!";
        String path = "C:\\Users\\Kristofer\\Desktop\\appium-homework\\page_source.xml";

        // 1. Вводим текст
        mainScreen.inputField.sendKeys(newText);

        // 2. Нажимаем на кнопку
        mainScreen.activityButton.click();

        // 3. Ждем 3 секунды
        Thread.sleep(3000);

        // 4. Сохраняем код страницы в файл
        java.nio.file.Files.write(java.nio.file.Paths.get(path), driver.getPageSource().getBytes());

        // 5. Пробуем найти текст
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