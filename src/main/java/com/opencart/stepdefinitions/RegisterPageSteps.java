package com.opencart.stepdefinitions;

import com.opencart.managers.DataFakeManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class RegisterPageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    RegisterPage registerPage = new RegisterPage(driver);

    @When("the registration form is completed with valid random data")
    public void theRegistrationFormIsCompletedWithValidRandomData() {
        String firstName = DataFakeManager.getRandomName();
        String lastName = DataFakeManager.getRandomName();
        String email = DataFakeManager.getRandomEmail();
        String password = DataFakeManager.getPassword(5, 24);
        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);


    }

    @And("the privacyToggle is enabled")
    public void thePrivacyToggleIsEnabled() {
        try {
            registerPage.switchOnThePrivacyToggle(driver);
        } catch (InterruptedException e) {
            System.out.println("Error!");
        }

    }

    @And("continueButton is clicked")
    public void continuebuttonIsClicked() throws InterruptedException {
        registerPage.clickOnTheContinueButton();
    }

    @And("the registration form is completed with the following data:")
    public void theRegistrationFormIsCompletedWithTheFollowingData(Map<String, String> userDetailsMap) {
        String firstNameValue = userDetailsMap.get("firstName");
        if (firstNameValue != null && firstNameValue.toUpperCase().equals("Random")) {
            firstNameValue = DataFakeManager.getRandomName();
        }
        String lastNameValue = userDetailsMap.get("lastName");
        if (lastNameValue != null && lastNameValue.toUpperCase().equals("Random")) {
            lastNameValue = DataFakeManager.getRandomName();
        }
        String emailValue = userDetailsMap.get("email");
        if (emailValue != null && emailValue.toUpperCase().equals("Random")) {
            emailValue = DataFakeManager.getRandomEmail();

            String passwordValue = userDetailsMap.get("email");
            if (passwordValue != null && passwordValue.toUpperCase().equals("Random")) {
                passwordValue = DataFakeManager.getPassword(7, 12);
            }
            registerPage.fillInTheRegisterForm(firstNameValue, lastNameValue, emailValue, passwordValue);

        }
    }
}
