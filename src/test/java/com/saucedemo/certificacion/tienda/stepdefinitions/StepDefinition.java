package com.saucedemo.certificacion.tienda.stepdefinitions;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.saucedemo.certificacion.tienda.tasks.CartPage;
import com.saucedemo.certificacion.tienda.tasks.RemoveFromCart;
import com.saucedemo.certificacion.tienda.tasks.LoginTask;
import com.saucedemo.certificacion.tienda.tasks.AddProductTask;
import com.saucedemo.certificacion.tienda.tasks.ProductsPage;
import com.saucedemo.certificacion.tienda.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import com.saucedemo.certificacion.tienda.userinterfaces.CheckoutPage;
import net.serenitybdd.screenplay.actions.Enter;



import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class StepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        OnStage.theActorCalled("User").attemptsTo(
                net.serenitybdd.screenplay.actions.Open.url("https://www.saucedemo.com/")
        );
    }

    @Given("I am logged into SauceDemo")
    public void iAmLoggedIntoSauceDemo() {
        OnStage.theActorCalled("User").attemptsTo(
                net.serenitybdd.screenplay.actions.Open.url("https://www.saucedemo.com/"),
                LoginTask.withCredentials("standard_user", "secret_sauce")
        );
    }

    @Given("I am logged into SauceDemo with the product {string} in the cart")
    public void iAmLoggedIntoSauceDemoWithProductInCart(String product) {

        iAmLoggedIntoSauceDemo();


        theActorInTheSpotlight().attemptsTo(
                RemoveFromCart.theProduct(product)
        );
    }

    @When("I type my username {string} and my password {string}")
    public void iTypeMyUsernameAndPassword(String username, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                LoginTask.withCredentials(username, password)
        );
    }

    @When("I type an incorrect username {string} and password {string}")
    public void iTypeIncorrectCredentials(String username, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                LoginTask.withCredentials(username, password)
        );
    }

    @When("I add the product {string} to the cart")
    public void iAddAProductToTheCart(String product) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddProductTask.withName(product)
        );
    }

    @When("I remove the product {string} from the cart")
    public void iRemoveTheProductFromTheCart(String product) {
        theActorInTheSpotlight().attemptsTo(
                RemoveFromCart.theProduct(product)
        );
    }

    @When("I go to the cart")
    public void iGoToTheCart() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(ProductsPage.CART_ICON)
        );
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(CartPage.CHECKOUT_BUTTON)
        );
    }

    @When("I fill in checkout information with first name {string}, last name {string}, and postal code {string}")
    public void iFillCheckoutInformation(String firstName, String lastName, String postalCode) {
        theActorInTheSpotlight().attemptsTo(
                Enter.theValue(firstName).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(lastName).into(CheckoutPage.LAST_NAME),
                Enter.theValue(postalCode).into(CheckoutPage.POSTAL_CODE),
                Click.on(CheckoutPage.CONTINUE_BUTTON)
        );
    }

    @When("I finish the purchase")
    public void iFinishThePurchase() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }

    @Then("I can see the products page")
    public void iCanSeeTheProductsPage() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Title is visible",
                        actor -> ProductsPage.TITLE.resolveFor(actor).isVisible(), is(true))
        );
    }

    @Then("I can see an error message indicating that the login was unsuccessful")
    public void iCanSeeTheErrorMessage() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Error message is visible",
                        actor -> LoginPage.LOGIN_ERROR.resolveFor(actor).isVisible(),
                        is(true))
        );
    }
    @Then("I can see the product in the cart")
    public void iCanSeeTheProductInTheCart() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("Cart badge is visible",
                        actor -> ProductsPage.CART_ICON.resolveFor(actor).isVisible(),
                        is(true))
        );
    }


    @Then("I should not see the product {string} in the cart")
    public void iShouldNotSeeTheProductInTheCart(String product) {
        theActorInTheSpotlight().should(
                seeThat("Product removed",
                        actor -> CartPage.REMOVE_BUTTON(product)
                                .resolveAllFor(actor)
                                .isEmpty(),

                        is(true)
                )
        );
    }
    @Then("I should see the confirmation message")
    public void iShouldSeeTheConfirmationMessage() {
        theActorInTheSpotlight().should(
                seeThat("Confirmation message is visible",
                        actor -> CheckoutPage.CONFIRMATION_MESSAGE.resolveFor(actor).isVisible(),
                        is(true))
        );
    }
}