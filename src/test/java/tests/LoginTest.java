package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void lockedUserTest() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void loginWithEmptyUsername() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, "Epic sadface: Username is required");

    }

    @Test
    public void loginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, "Epic sadface: Password is required");
    }

    @Test
    public void invalidLoginCredentials() {
        loginPage.open();
        loginPage.login("standard_user ", "secret_sauce");

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible);
        assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void correctUserTest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        boolean pageTitleVisible = productsPage.isPageTitleVisible();
        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), "Products");
    }
}
