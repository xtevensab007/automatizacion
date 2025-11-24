#language: en
Feature: Access to the SauceDemo store
  As a client
  I want to log into SauceDemo
  So I can buy products from the catalog

# login exitoso
  Scenario: Login successfully into SauceDemo
    Given I am on the SauceDemo login page
    When I type my username "standard_user" and my password "secret_sauce"
    Then I can see the products page


  #login malo
  Scenario: Login failed with invalid credentials
    Given I am on the SauceDemo login page
    When I type an incorrect username "wrong_user" and password "1234"
    Then I can see an error message indicating that the login was unsuccessful


  #añadir al carrito
  Scenario: Add a product to the cart
    Given I am logged into SauceDemo
    When I add the product "Sauce Labs Backpack" to the cart
    Then I can see the product in the cart

    #quitar productos
  #quitar productos
  Scenario: Remove a product from the cart
    Given I am logged into SauceDemo with the product "Sauce Labs Backpack" in the cart
    When I remove the product "Sauce Labs Backpack" from the cart
    Then I should not see the product "Sauce Labs Backpack" in the cart