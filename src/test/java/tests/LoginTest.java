package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user ", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test (dataProvider = "loginData", priority = 1)
    public void incorrectDataLoginTest(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, errorMsg);
    }

    @Test (description = "Проверка авторизации", priority =  2, invocationCount = 5)
    public void correctUserTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        boolean pageTitleVisible = productsPage.isPageTitleVisible();

        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), "Products");
    }
}
