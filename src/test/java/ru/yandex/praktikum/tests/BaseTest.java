package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.api.User;
import ru.yandex.praktikum.api.UserClient;
import ru.yandex.praktikum.api.UserGenerator;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest {
    protected WebDriver driver;
    protected User user;
    protected String accessToken;
    protected UserClient userClient;

    private final String browserName;

    public BaseTest(String browserName) {
        this.browserName = browserName;
    }

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "/Users/jordan/ChromeDriver/chromedriver-mac-arm64/chromedriver");
            options.setBinary("/Users/jordan/Downloads/chrome-mac-arm64/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing");
            driver = new ChromeDriver(options);
        } else if (browserName.equals("yandex")) {
            System.setProperty("webdriver.chrome.driver", "/Users/jordan/yandexdriver");
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    protected void createUserViaApi() {
        user = UserGenerator.getRandomUser();
        accessToken = userClient.createUser(user).jsonPath().getString("accessToken");
    }
}