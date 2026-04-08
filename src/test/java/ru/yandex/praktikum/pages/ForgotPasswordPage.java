package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage extends BasePage {
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final WebDriverWait wait;

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}