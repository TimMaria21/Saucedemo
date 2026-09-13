package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.CheckoutData;

public class CheckoutPage extends BasePage {
    private final By firstNameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("firstName"));
    private final By lastNameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("lastName"));
    private final By postalCodeInput = By.cssSelector(DATA_TEST_PATTERN.formatted("postalCode"));
    private final By continueBtn = By.cssSelector("#continue");
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutForm(CheckoutData data) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        driver.findElement(firstNameInput).sendKeys(data.getFirstName());
        driver.findElement(lastNameInput).sendKeys(data.getLastName());
        driver.findElement(postalCodeInput).sendKeys(data.getZip());
        driver.findElement(continueBtn).click();
    }

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }
}
