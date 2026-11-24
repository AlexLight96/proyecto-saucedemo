package proyecto.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest{

    @Test
    @DisplayName("TC01 Login exitoso con usuario estándar")
    void successfulLogin() {
        new LoginPage(driver).open().login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertEquals("Products", inventoryPage.getPageTitle());
    }

    @Test
    @DisplayName("TC02 Usuario bloqueado no puede iniciar sesión")
    void lockedOutUserCannotLogin() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertEquals("Epic sadface: Sorry, this user has been locked out.",
                loginPage.getErrorMessage());
    }

    @Test
    @DisplayName("TC03 Login con contraseña incorrecta")
    void loginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.login("standard_user", "wrong_password");

        assertEquals("Epic sadface: Username and password do not match any user in this service",
                loginPage.getErrorMessage());
    }



}
