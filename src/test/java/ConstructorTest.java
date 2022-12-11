import page.MainPage;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;


public class ConstructorTest {
    private String url = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void start() {
        // для Yandex Browser
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("src/main/resources/yandexdriver");
//        WebDriverRunner.setWebDriver(new ChromeDriver(options));
        Configuration.browserSize = "1000x1000";

    }

    @Test
    @DisplayName("check transitions to buns")
    public void checkTransitionsToBuns() {
        MainPage mainPage = open(url, MainPage.class);
        mainPage.checkBuns();
    }

    @Test
    @DisplayName("check transitions to souse")
    public void checkTransitionsToSouse() {
        MainPage mainPage = open(url, MainPage.class);
        mainPage.checkSouse();
    }

    @Test
    @DisplayName("check transitions to filling")
    public void checkTransitionsToFilling() {
        MainPage mainPage = open(url, MainPage.class);
        mainPage.checkFilling();
    }
    @After
    public void end () {
        webdriver().driver().close();
    }
}