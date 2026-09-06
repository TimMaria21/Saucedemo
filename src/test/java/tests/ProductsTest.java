package tests;

import org.testng.annotations.Test;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest  extends BaseTest {
    List<String> goodsList =
            List.of("Sauce Labs Fleece Jacket",
                    "Sauce Labs Onesie",
                    "Test.allTheThings() T-Shirt (Red)");

    @Test()
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), "Products");

        for (String goodsName : goodsList) {
            productsPage.addGoodsToCart(goodsName);
        }
        productsPage.addGoodsToCart(0);

        assertTrue(productsPage.hasItemsInCart());
        assertEquals(productsPage.getCartItemsCount(), "4");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
