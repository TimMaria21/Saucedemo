package tests;

import enums.ErrorMessages;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.ErrorMessages.*;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

@Epic("Saucedemo: Авторизация и покупки")
@Feature("Авторизация пользователя")
@Owner("Mariya Timofeeva, mariya.timofeeva1@yandex.ru")
public class LoginTest extends BaseTest {
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {withLockedPermission(), LOCKED_USER},
                {emptyUsernamePermission(), EMPTY_USERNAME},
                {emptyPasswordPermission(), EMPTY_PASSWORD},
                {notMatchPermission(), INVALID_CREDENTIALS}
        };
    }

    @Story("Вход с некорректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Saucedemo")
    @Description("Проверяем, что при неверных данных появляется корректное сообщение об ошибке")
    @Test(dataProvider = "loginData", priority = 1)
    public void incorrectDataLoginTest(User user, ErrorMessages errorMsg) {
        System.out.println("incorrectDataLoginTest is running in thread: " + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(user);

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, errorMsg.getMessage());
    }

    @Story("Вход с корректными данными")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemo")
    @Description("Проверяем, что при корректной авторизации попадаем на страницу товаров")
    @Test(priority = 2, invocationCount = 1)
    public void correctUserTest() {
        System.out.println("correctUserTest is running in thread: " + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        boolean pageTitleVisible = productsPage.isPageTitleVisible();

        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());
    }
}
