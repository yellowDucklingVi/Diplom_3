package ru.yandex.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {
    private final By loginButton = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");
    private final By personalAccountButton = By.xpath("//*[@id=\"root\"]/div/header/nav/a");
    private final By orderButton = By.xpath("//button[contains(text(),'Оформить заказ')]");
    private final By constructorHeader = By.xpath("//*[@id=\"root\"]/div/main/section[1]/h1");
    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");
    private final String activeTabClass = "tab_tab_type_current__2BEPc";

    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get("https://stellarburgers.education-services.ru/");
        waitForPageLoad();
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажать 'Личный кабинет'")
    public void clickPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Проверить отображение кнопки 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }

    @Step("Нажать вкладку 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Нажать вкладку 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Нажать вкладку 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Проверить активность вкладки 'Булки'")
    public boolean isBunsTabActive() {
        return wait.until(ExpectedConditions.attributeContains(bunsTab, "class", activeTabClass));
    }

    @Step("Проверить активность вкладки 'Соусы'")
    public boolean isSaucesTabActive() {
        return wait.until(ExpectedConditions.attributeContains(saucesTab, "class", activeTabClass));
    }

    @Step("Проверить активность вкладки 'Начинки'")
    public boolean isFillingsTabActive() {
        return wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", activeTabClass));
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(constructorHeader));
    }
}