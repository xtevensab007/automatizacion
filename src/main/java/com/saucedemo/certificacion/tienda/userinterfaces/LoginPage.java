package com.saucedemo.certificacion.tienda.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    // ---- Campos ----
    public static final Target USERNAME = Target.the("username field")
            .located(By.id("user-name"));

    public static final Target PASSWORD = Target.the("password field")
            .located(By.id("password"));

    // ---- Botón Login ----
    public static final Target LOGIN_BUTTON = Target.the("login button")
            .located(By.id("login-button"));

    // ---- Mensaje de Error ----
    public static final Target LOGIN_ERROR = Target.the("login error message")
            .located(By.cssSelector("h3[data-test='error']"));

    // ---- Botón cerrar mensaje de error ----
    public static final Target CLOSE_ERROR_BUTTON = Target.the("error close button")
            .located(By.cssSelector("button[data-test='error-button']"));
}

