package com.saucedemo.certificacion.tienda.stepdefinitions;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.saucedemo.certificacion.tienda.tasks.CartPage;
import com.saucedemo.certificacion.tienda.tasks.RemoveFromCart;
import com.saucedemo.certificacion.tienda.tasks.LoginTask;
import com.saucedemo.certificacion.tienda.tasks.AddProductTask;
import com.saucedemo.certificacion.tienda.userinterfaces.ProductsPage;
import com.saucedemo.certificacion.tienda.userinterfaces.LoginPage;

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
                AddProductTask.withName(product)
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
                                .of(product)
                                .resolveAllFor(actor)
                                .isEmpty()
                        , is(true)
                )
        );
    }
}