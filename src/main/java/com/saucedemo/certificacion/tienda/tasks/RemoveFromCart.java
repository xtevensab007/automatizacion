package com.saucedemo.certificacion.tienda.tasks;

import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RemoveFromCart implements Task {

    private final String product;

    public RemoveFromCart(String product) {
        this.product = product;
    }

    public static RemoveFromCart theProduct(String product) {
        return instrumented(RemoveFromCart.class, product);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(

                // Navegación al carrito
                WaitUntil.the(ProductsPage.CART_ICON, isClickable()).forNoMoreThan(5).seconds(),
                Click.on(ProductsPage.CART_ICON),

                // Espera y click en el botón Remove
                WaitUntil.the(CartPage.REMOVE_BUTTON(product), isClickable()).forNoMoreThan(2).seconds(),
                Click.on(CartPage.REMOVE_BUTTON(product))


        );
    }
}

