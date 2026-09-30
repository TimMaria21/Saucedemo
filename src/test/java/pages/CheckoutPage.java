package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.CheckoutData;

import java.util.ArrayList;

public class CheckoutPage extends BasePage {
    private final By firstNameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("firstName"));
    private final By lastNameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("lastName"));
    private final By postalCodeInput = By.cssSelector(DATA_TEST_PATTERN.formatted("postalCode"));
    private final By continueBtn = By.cssSelector("#continue");
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить форму оформления заказа данными покупателя")
    public void fillCheckoutForm(CheckoutData data) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        driver.findElement(firstNameInput).sendKeys(data.getFirstName());
        driver.findElement(lastNameInput).sendKeys(data.getLastName());
        driver.findElement(postalCodeInput).sendKeys(data.getZip());
    }

    @Step("Нажать кнопку 'Continue' для перехода к следующему шагу оформления")
    public void clickContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    @Step("Получить введённые значения полей формы (имя, фамилия, индекс)")
    public ArrayList<String> getFormValues() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));

        ArrayList<String> values = new ArrayList<>();
        values.add(driver.findElement(firstNameInput).getAttribute("value"));
        values.add(driver.findElement(lastNameInput).getAttribute("value"));
        values.add(driver.findElement(postalCodeInput).getAttribute("value"));
        return values;
    }

    @Step("Получить текст заголовка страницы оформления заказа")
    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }
}
