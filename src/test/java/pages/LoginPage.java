package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginBtn = By.cssSelector("#login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем сответсвующий браузер")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Авторизация под кредами пользователя")
    public void login(User user) {
        fillLoginInput(user.getUser());
        fillPasswordInput(user.getPassword());
        driver.findElement(loginBtn).click();
    }

    @Step("Заполняем поле логина {user}")
    public void fillLoginInput(String user) {
        driver.findElement(usernameInput).sendKeys(user);
    }

    @Step("Заполняем поле пароля {password}")
    public void fillPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Проверяем, что сообщение об ошибке отображается")
    public boolean isErrorVisible() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Проверяем текст сообщения об ошибке")
    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
