package com.saucedemo.certificacion.tienda.tasks;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static Target REMOVE_BUTTON(String productName) {
        // Opción 1: Más simple y generalmente funciona en SauceDemo
        return Target.the("remove button in cart for product " + productName)
                .locatedBy("//div[@class='cart_item' and .//div[@class='inventory_item_name' and text()='{0}']]//button[text()='Remove']");
    }

    public static final Target CART_ITEMS = Target.the("cart items")
            .locatedBy("//div[@class='cart_item']");
}
