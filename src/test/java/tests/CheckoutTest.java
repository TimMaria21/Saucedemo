package tests;

import org.testng.annotations.Test;
import user.CheckoutData;
import user.CheckoutDataFactory;

import java.util.ArrayList;
import java.util.List;

import static enums.TitleNaming.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class CheckoutTest extends BaseTest {

    @Test()
            public void testCheckoutWithValidData(){
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
        cartPage.clickCheckout();
        assertEquals(productsPage.getPageTitle(), YOUR_INFORMATION.getDisplayName());

        CheckoutData data = CheckoutDataFactory.withValidData();
        checkoutPage.fillCheckoutForm(data);

        ArrayList<String> formValues = checkoutPage.getFormValues();

        assertTrue(
                formValues.stream().noneMatch(String::isEmpty),
                "Одно или несколько полей checkout пустые: " + formValues
        );

        checkoutPage.clickContinue();

        assertEquals(checkoutPage.getPageTitle(), CHECKOUT_OVERVIEW.getDisplayName());
    }
}
