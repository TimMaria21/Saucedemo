package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {
    public static final String ADD_TO_CART_PATTERN = "//*[text()='%s']" +
            " /ancestor::div[@class='inventory_item']//child::button[text()='Add to cart']";
    private final By pageTitle = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));
    private final By cartItems = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));
    private final By cartLink = By.cssSelector(DATA_TEST_PATTERN.formatted("shopping-cart-link"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что заголовок страницы товаров отображается")
    public boolean isPageTitleVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Получить текст заголовка страницы товаров")
    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return driver.findElement(pageTitle).getText();
    }

    @Step("Добавить в корзину товар: {goodsName}")
    public void addGoodsToCart(String goodsName) {
        By addToCartBtn = By.xpath(ADD_TO_CART_PATTERN.formatted(goodsName));
        driver.findElement(addToCartBtn).click();
    }

    @Step("Добавить в корзину товар по индексу: {goodIndex}")
    public void addGoodsToCart(int goodIndex) {
        By addToCartBtn = By.xpath("//button[text()='Add to cart']");
        driver.findElements(addToCartBtn).get(goodIndex).click();
    }

    @Step("Проверить, что в корзине есть товары")
    public boolean hasItemsInCart() {
        return driver.findElement(cartItems).isDisplayed();
    }

    @Step("Получить количество товаров в корзине")
    public String getCartItemsCount() {
        return driver.findElement(cartItems).getText();
    }

    @Step("Получить цвет счётчика корзины")
    public String checkCounterColor() {
        return driver.findElement(cartItems).getCssValue("background-color");
    }

    @Step("Перейти в корзину")
    public void switchToCart() {
        driver.findElement(cartLink).click();
    }
}
