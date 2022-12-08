import Page.LoginPage;
import Page.MainPage;
import User.FakerUser;
import Page.RegistrationPage;
import User.User;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import User.UserClient;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    private FakerUser fakerUser;
    private User user;
   String  url = "https://stellarburgers.nomoreparties.site/register";
   @Before
   public void start(){// для Yandex Browser
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("src/main/resources/yandexdriver");
//        WebDriverRunner.setWebDriver(new ChromeDriver(options));
       Configuration.browserSize = "1000x1000";
   }
    @Test
    @DisplayName("success registration with pass 7 char")
    public void successRegistrationWithPass7Char(){
        fakerUser = FakerUser.getRandom(7);
        RegistrationPage registerPage = open( url, RegistrationPage.class);
        registerPage.registerUser(fakerUser.getName(), fakerUser.getEmail(),fakerUser.getPassword());
        LoginPage loginPage =page(LoginPage.class);
        loginPage.login(fakerUser.getEmail(), fakerUser.getPassword());
        MainPage mainPage = page(MainPage.class);
        mainPage.checkLogin();
    }
    @Test
    @DisplayName("success registration with pass 6 char")
    public void successRegistrationWithPass6Char(){
        fakerUser = FakerUser.getRandom(6);
        RegistrationPage registerPage = open( url, RegistrationPage.class);
        registerPage.registerUser(fakerUser.getName(), fakerUser.getEmail(),fakerUser.getPassword());
        LoginPage loginPage =page(LoginPage.class);
        loginPage.login(fakerUser.getEmail(), fakerUser.getPassword());
        MainPage mainPage = page(MainPage.class);
        mainPage.checkLogin();
    }
    @Test
    @DisplayName("error registration with pass 5 char")
    public void errorRegistrationWithPass5Char(){
        fakerUser = FakerUser.getRandom(5);
        RegistrationPage registerPage = open( url, RegistrationPage.class);
        registerPage.registerUser(fakerUser.getName(), fakerUser.getEmail(),fakerUser.getPassword());
        registerPage.checkErr();
    }
    @After
    public void end(){
       webdriver().driver().close();
       UserClient userClient = new UserClient();
       String accessToken =  userClient.loginUser(user.from(fakerUser)).then().extract().path("accessToken");
       userClient.delete(accessToken);
    }
}
