package com.appium.stepdefinitions;

import bean.Login;
import bean.User;
import configuration.BaseAppium;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import utils.PropertyLoader;

public class LoginSteps {

    PropertyLoader loadproperty = new PropertyLoader();
    User user;

    @Given("^somos un usuario$")
    public void weAreUser() {
        user = new User(loadproperty.loadProperties().getProperty("loginUser"), loadproperty.loadProperties().getProperty("passwordUser"));
    }

    @And("^entramos a la app$")
    public void weEnterToApp() {
        BaseAppium baseAppium = new BaseAppium();
        try {
            baseAppium.init();
        } catch (Exception e) {
            System.out.println("Error connecting to Appium Service. Error :" + e);
        }
    }

    @When("^cuando hacemos login con usuario y pass$")
    public void weMakeLogin() {
        Login login = new Login();
        login.login(user);
    }

    @Then("^el login es success$")
    public void loginSuccess() {
        Login login = new Login();
        Assert.assertTrue(login.isUserLogged());
        login.logout();
    }

    @After
    public void close() {
        BaseAppium baseAppium = new BaseAppium();
        baseAppium.tearDown();
    }

}