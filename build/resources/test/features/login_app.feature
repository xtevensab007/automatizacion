#author : steven Alipio - john sebastian usuga
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
  Scenario: Remove a product from the cart
    Given I am logged into SauceDemo with the product "Sauce Labs Backpack" in the cart
    When I remove the product "Sauce Labs Backpack" from the cart
    Then I should not see the product "Sauce Labs Backpack" in the cart

 #fujo completo
  Scenario: Complete a purchase successfully
    Given I am logged into SauceDemo
    When I add the product "Sauce Labs Backpack" to the cart
    And I go to the cart
    And I proceed to checkout
    And I fill in checkout information with first name "John", last name "Doe", and postal code "12345"
    And I finish the purchase
    Then I should see the confirmation message