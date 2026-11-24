package proyecto.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proyecto.pages.CartPage;
import proyecto.pages.InventoryPage;
import proyecto.pages.LoginPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends BaseTest{

    @Test
    @DisplayName("TC04 Agregar productos al carrito")

    void addProductsToCart() {
        new LoginPage(driver).open().login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addToCart("sauce-labs-backpack");
        inventoryPage.addToCart("sauce-labs-bike-light");

        assertEquals("2", inventoryPage.getCartBadgeCount());

        CartPage cartPage = inventoryPage.goToCart();
        assertEquals(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"),
                cartPage.getProductNames());
    }

}
