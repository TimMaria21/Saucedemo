package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

@Epic("Saucedemo: Авторизация и покупки")
@Feature("Работа с корзиной")
public class CartTest extends BaseTest {
    SoftAssert soft = new SoftAssert();

    @Story("Товары отображаются в корзине после добавления")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemo")
    @Owner("Mariya Timofeeva, mariya.timofeeva1@yandex.ru")
    @Description("Проверяем, что после добавления 3 товаров они все отображаются в корзине")
    @Test()
    public void checkGoodsInCart() {
        List<String> goodsList =
                List.of("Sauce Labs Fleece Jacket",
                        "Sauce Labs Onesie",
                        "Test.allTheThings() T-Shirt (Red)");

        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodsList) {
            productsPage.addGoodsToCart(goodsName);
        }
        productsPage.switchToCart();

        soft.assertFalse(cartPage.getProductsNames().isEmpty());
        soft.assertEquals(cartPage.getProductsNames().size(), 3);
        soft.assertTrue(cartPage.getProductsNames().contains("Sauce Labs Fleece Jacket"));
        soft.assertTrue(cartPage.getProductsNames().contains("Sauce Labs Onesie"));
        soft.assertTrue(cartPage.getProductsNames().contains("Test.allTheThings() T-Shirt (Red)"));

        soft.assertAll();
    }
}
