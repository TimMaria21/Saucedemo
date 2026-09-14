package tests;

import enums.ErrorMessages;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.ErrorMessages.*;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

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

    @Test(description = "Проверка авторизации", priority = 2, invocationCount = 5)
    public void correctUserTest() {
        System.out.println("correctUserTest is running in thread: " + Thread.currentThread().threadId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        boolean pageTitleVisible = productsPage.isPageTitleVisible();

        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());
    }
}
