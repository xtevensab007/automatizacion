package com.saucedemo.certificacion.tienda.tasks;

import net.serenitybdd.screenplay.targets.Target;

public class ProductsPage {

    public static final Target TITLE = Target.the("products title")
            .locatedBy("//span[@class='title']");

    public static final Target ADD_TO_CART_BUTTON = Target.the("add to cart button")
            .locatedBy("//div[@class='inventory_item'][.//div[text()='{0}']]//button[contains(text(),'Add to cart')]");


    public static final Target CART_ICON = Target.the("cart link icon")
            .locatedBy(".shopping_cart_link");



}
