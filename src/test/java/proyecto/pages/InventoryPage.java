package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage  {
    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");


    public InventoryPage(WebDriver driver) {
        super(driver);
        By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
        waitForVisible(sortDropdown);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void addToCart(String productId) {
        click(By.id("add-to-cart-" + productId));
    }

    public String getCartBadgeCount() {
        return getText(cartBadge);
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}
