package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage{
    private final By successMessage = By.cssSelector("[data-test='complete-header']");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        waitForVisible(backHomeButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public InventoryPage backHome() {
        click(backHomeButton);
        return new InventoryPage(driver);
    }
}
