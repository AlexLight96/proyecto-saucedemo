package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage{

    private final By itemTotalLabel = By.cssSelector("[data-test='subtotal-label']");
    private final By taxLabel = By.cssSelector("[data-test='tax-label']");
    private final By totalLabel = By.cssSelector("[data-test='total-label']");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        waitForVisible(finishButton);
    }

    public double getItemTotal() {
        return parseAmount(getText(itemTotalLabel));
    }

    public double getTax() {
        return parseAmount(getText(taxLabel));
    }

    public double getTotal() {
        return parseAmount(getText(totalLabel));
    }

    public CheckoutCompletePage finish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}
