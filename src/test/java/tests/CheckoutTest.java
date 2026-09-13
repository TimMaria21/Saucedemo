package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import user.CheckoutDataFactory;

import java.util.List;

import static enums.TitleNaming.*;
import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

public class CheckoutTest extends BaseTest {

    @Test()
            public void testCheckoutWithValidData(){
        List<String> goodsList =
                List.of("Sauce Labs Fleece Jacket",
                        "Sauce Labs Onesie",
                        "Test.allTheThings() T-Shirt (Red)");
        System.out.println("checkGoodsInCart is running in thread: " + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        Assert.assertTrue(productsPage.isPageTitleVisible());
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());

        for (String goodsName : goodsList) {
            productsPage.addGoodsToCart(goodsName);
        }
        productsPage.switchToCart();
        cartPage.clickCheckout();
        assertEquals(productsPage.getPageTitle(), YOUR_INFORMATION.getDisplayName());

        checkoutPage.fillCheckoutForm(CheckoutDataFactory.withValidData());
        assertEquals(checkoutPage.getPageTitle(), CHECKOUT_OVERVIEW.getDisplayName());
    }
}
