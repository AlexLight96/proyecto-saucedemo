package proyecto.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proyecto.pages.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseTest {
    @Test
    @DisplayName("TC05 E2E Compra completa de dos productos")
    void completePurchaseEndToEnd() {

        new LoginPage(driver).open().login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);


        inventoryPage.addToCart("sauce-labs-backpack");
        inventoryPage.addToCart("sauce-labs-bike-light");


        CartPage cartPage = inventoryPage.goToCart();
        assertEquals(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"),
                cartPage.getProductNames());
        double pricesSum = cartPage.getPricesSum();

        CheckoutOverviewPage overviewPage = cartPage.goToCheckout()
                .fillInformation("Alejandro", "Ortiz", "10101");

        assertEquals(pricesSum, overviewPage.getItemTotal(), 0.001);
        assertEquals(overviewPage.getItemTotal() + overviewPage.getTax(),
                overviewPage.getTotal(), 0.001);

        CheckoutCompletePage completePage = overviewPage.finish();
        assertEquals("Thank you for your order!", completePage.getSuccessMessage());

        InventoryPage homePage = completePage.backHome();
        assertEquals("Products", homePage.getPageTitle());
    }
}
