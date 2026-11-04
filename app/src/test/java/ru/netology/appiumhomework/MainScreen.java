package ru.netology.appiumhomework;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class MainScreen {
    private AppiumDriver driver;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    public WebElement inputField;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public WebElement changeButton;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    public WebElement activityButton;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    public WebElement textToBeChanged;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    public WebElement newActivityText;

    public MainScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }
}