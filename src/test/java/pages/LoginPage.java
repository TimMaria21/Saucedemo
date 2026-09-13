package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    private final By loginBtn = By.cssSelector("#login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(User user) {
        fillLoginInput(user.getUser());
        fillPasswordInput(user.getPassword());
        driver.findElement(loginBtn).click();
    }

    public void fillLoginInput(String user) {
        driver.findElement(usernameInput).sendKeys(user);
    }

    public void fillPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public boolean isErrorVisible() {
        return driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
    }

    public String getErrorText() {
        return driver.findElement(By.cssSelector("[data-test='error']")).getText();
    }
}
