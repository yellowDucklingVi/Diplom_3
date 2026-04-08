package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.praktikum.pages.LoginPage;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

@DisplayName("Регистрация пользователя")
public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка регистрации нового пользователя через форму регистрации")
    public void successfulRegistration() {
        user = ru.yandex.praktikum.api.UserGenerator.getRandomUser();

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        driver.get("https://stellarburgers.education-services.ru/login");

        LoginPage loginPageAfterRegister = new LoginPage(driver);
        loginPageAfterRegister.login(user.getEmail(), user.getPassword());

        MainPage mainPageAfterLogin = new MainPage(driver);
        mainPageAfterLogin.waitForPageLoad();
        assertTrue(mainPageAfterLogin.isOrderButtonDisplayed());

        accessToken = userClient.loginUser(user).jsonPath().getString("accessToken");
    }

    @Test
    @DisplayName("Ошибка для короткого пароля")
    @Description("Проверка, что при вводе пароля менее 6 символов отображается сообщение об ошибке")
    public void shortPasswordError() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Тест", "test@test.ru", "12345");

        assertTrue(registerPage.isErrorMessageDisplayed());
    }
}