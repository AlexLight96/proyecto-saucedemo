package proyecto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage{

    private final By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By productPrices = By.cssSelector("[data-test='inventory-item-price']");
    private final By checkoutButton = By.id("checkout");


    public CartPage(WebDriver driver) {
        super(driver);
        waitForVisible(checkoutButton);
    }
    public List<String> getProductNames() {
        return wait.ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    List<String> names = d.findElements(productNames).stream()
                            .map(WebElement::getText)
                            .toList();
                    return names.isEmpty() ? null : names;
                });
    }

    public double getPricesSum() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices))
                .stream()
                .mapToDouble(price -> parseAmount(price.getText()))
                .sum();
    }

    public CheckoutInformationPage goToCheckout() {
        click(checkoutButton);
        return new CheckoutInformationPage(driver);
    }

}
