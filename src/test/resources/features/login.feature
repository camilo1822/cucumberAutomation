Feature: Hacer login
As a user
Quiero hacer login en la aplicacion

Background:
    Given somos un usuario
    And entramos a la app

Scenario: Hacer login
    When cuando hacemos login con usuario y pass
    Then el login es success