package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import user.CheckoutData;
import user.CheckoutDataFactory;

import java.util.ArrayList;
import java.util.List;

import static enums.TitleNaming.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

@Epic("SauceDemo: Авторизация и покупки")
@Feature("Оформление заказа")
@Owner("Mariya Timofeeva, mariya.timofeeva1@yandex.ru")
public class CheckoutTest extends BaseTest {

    @Story("Оформление заказа с валидными данными покупателя")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemo")
    @Description("Проверяем, что при корректном оформлении заказа с валидными данными заказ успешно создаётся")
    @Test()
    public void testCheckoutWithValidData() {
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
