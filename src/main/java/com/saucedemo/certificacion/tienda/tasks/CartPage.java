package com.saucedemo.certificacion.tienda.tasks;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static Target REMOVE_BUTTON(String productName) {

        String formattedName = productName.toLowerCase().replace(" ", "-");
        return Target.the("remove button in cart for product " + productName)
                .locatedBy("//*[@id='remove-sauce-labs-backpack']");
    }

    public static final Target CART_ITEMS = Target.the("cart items")
            .locatedBy("//div[@class='cart_item']");

    public static final Target CHECKOUT_BUTTON = Target.the("checkout button")
            .locatedBy("#checkout");
}
