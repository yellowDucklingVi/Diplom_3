package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

@DisplayName("Конструктор бургеров")
public class ConstructorTest extends BaseTest {

    public ConstructorTest(String browserName) {
        super(browserName);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("При клике на вкладку 'Соусы' она становится активной")
    public void switchToSauces() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();
        assertTrue(mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("При клике на вкладку 'Начинки' она становится активной")
    public void switchToFillings() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickFillingsTab();
        assertTrue(mainPage.isFillingsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("При клике на вкладку 'Булки' она становится активной")
    public void switchToBuns() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsTabActive());
    }
}