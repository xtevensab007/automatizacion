package com.saucedemo.certificacion.tienda.tasks;

import com.saucedemo.certificacion.tienda.userinterfaces.ProductsPage;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class AddProductTask {

    public static Task withName(String product) {
        return Task.where("{0} adds the product " + product,
                Click.on(ProductsPage.ADD_TO_CART_BUTTON.of(product))
        );
    }
}


