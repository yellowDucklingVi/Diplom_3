package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pages.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
@DisplayName("Вход в систему")
public class LoginTest extends BaseTest {

    private final LoginMethod method;

    public enum LoginMethod {
        MAIN_PAGE_BUTTON,
        PERSONAL_ACCOUNT_BUTTON,
        REGISTER_PAGE_LINK,
        FORGOT_PASSWORD_PAGE_LINK
    }

    public LoginTest(String browserName, LoginMethod method) {
        super(browserName);
        this.method = method;
    }

    @Parameterized.Parameters(name = "Браузер: {0}, Способ входа: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome", LoginMethod.MAIN_PAGE_BUTTON},
                {"chrome", LoginMethod.PERSONAL_ACCOUNT_BUTTON},
                {"chrome", LoginMethod.REGISTER_PAGE_LINK},
                {"chrome", LoginMethod.FORGOT_PASSWORD_PAGE_LINK},
                {"yandex", LoginMethod.MAIN_PAGE_BUTTON},
                {"yandex", LoginMethod.PERSONAL_ACCOUNT_BUTTON},
                {"yandex", LoginMethod.REGISTER_PAGE_LINK},
                {"yandex", LoginMethod.FORGOT_PASSWORD_PAGE_LINK}
        });
    }

    @Before
    public void createUser() {
        createUserViaApi();
    }

    @Test
    @DisplayName("Вход через разные кнопки")
    @Description("Проверка возможности входа в систему через различные точки входа")
    public void loginTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        switch (method) {
            case MAIN_PAGE_BUTTON:
                mainPage.clickLoginButton();
                break;
            case PERSONAL_ACCOUNT_BUTTON:
                mainPage.clickPersonalAccount();
                break;
            case REGISTER_PAGE_LINK:
                mainPage.clickLoginButton();
                new LoginPage(driver).clickRegisterLink();
                new RegisterPage(driver).clickLoginLink();
                break;
            case FORGOT_PASSWORD_PAGE_LINK:
                mainPage.clickLoginButton();
                new LoginPage(driver).clickForgotPasswordLink();
                new ForgotPasswordPage(driver).clickLoginLink();
                break;
        }

        LoginPage finalLoginPage = new LoginPage(driver);
        finalLoginPage.login(user.getEmail(), user.getPassword());

        MainPage mainPageAfterLogin = new MainPage(driver);
        mainPageAfterLogin.waitForPageLoad();
        assertTrue(mainPageAfterLogin.isOrderButtonDisplayed());
    }
}