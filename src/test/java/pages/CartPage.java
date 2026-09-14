package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By continueShoppingButton = By.cssSelector(DATA_TEST_PATTERN.formatted("continue-shopping"));
    private final By productName = By.cssSelector(".inventory_item_name");
    private final By checkoutButton = By.cssSelector(DATA_TEST_PATTERN.formatted("checkout"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductsNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton));

        List<WebElement> allProducts = driver.findElements(productName);
        ArrayList<String> names = new ArrayList<>();

        for (WebElement product : allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
