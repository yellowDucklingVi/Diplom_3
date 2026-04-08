package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {
    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By errorMessage = By.xpath("//p[text()='Некорректный пароль']");

    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Ввести имя")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
    }

    @Step("Ввести email")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Проверить отображение ошибки пароля")
    public boolean isErrorMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

    @Step("Зарегистрировать пользователя")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
}