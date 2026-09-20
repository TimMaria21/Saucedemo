package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("SauceDemo: Авторизация и покупки")
@Feature("Работа с товарами")
public class ProductsTest extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Fleece Jacket",
                    "Sauce Labs Onesie",
                    "Test.allTheThings() T-Shirt (Red)");

    @Story("Добавление товаров в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemo")
    @Owner("Mariya Timofeeva, mariya.timofeeva1@yandex.ru")
    @Description("Проверяем, что после добавления 3 товаров по имени и 1 по индексу счётчик корзины показывает 4, а его цвет — красный")
    @Test()
    public void checkGoodsAdded() {
        System.out.println("checkGoodsAdded is running in thread: " + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodsList) {
            productsPage.addGoodsToCart(goodsName);
        }
        productsPage.addGoodsToCart(0);

        assertTrue(productsPage.hasItemsInCart());
        assertEquals(productsPage.getCartItemsCount(), "4");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
