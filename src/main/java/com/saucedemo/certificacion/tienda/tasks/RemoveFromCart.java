package com.saucedemo.certificacion.tienda.tasks;

import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import com.saucedemo.certificacion.tienda.userinterfaces.ProductsPage;
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
                // 1️⃣ Esperar a que el ícono del carrito sea clickable (5 segundos)
                WaitUntil.the(ProductsPage.CART_ICON, isClickable()).forNoMoreThan(5).seconds(),
                Click.on(ProductsPage.CART_ICON), // Navegación al carrito

                // 2️⃣ Esperar a que el botón de Remove aparezca en la página del carrito
                WaitUntil.the(CartPage.REMOVE_BUTTON(product).of(product), isClickable()).forNoMoreThan(5).seconds(),
                // 3️⃣ Hacer clic en Remove dentro del carrito
                Click.on(CartPage.REMOVE_BUTTON(product).of(product))
        );
    }
}
