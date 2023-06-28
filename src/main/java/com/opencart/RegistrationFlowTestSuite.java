package com.opencart;

import com.opencart.managers.DataFakeManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
   static int counter = 0;



    @BeforeEach

    public void executeTheCodeBeforeEachTestFromThisClass(){
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number"+counter+ "started!");

    }

    @Test
    @DisplayName("The url contains success keyword after registration with valid data.")
    public void registrationFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();
        String firstName = DataFakeManager.getRandomName();
        String lastName = DataFakeManager.getRandomName();
        String email = DataFakeManager.getRandomEmail();
        String password = DataFakeManager.getPassword(5, 24);
       registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnTheContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());
        boolean urlContainsTheCorrectKeywords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The url " + driver.getCurrentUrl() + "contains success keyword";
        Assertions.assertTrue(urlContainsTheCorrectKeywords, errorMessage);

    }

    @Test
    @DisplayName("The url contains the register keyword when the privacy policy is not accepted.")
    public void registrationFlowIsBlockedByPrivacyPolicyToggleThatIsNotAccepted() throws InterruptedException {
        homePage.navigateToRegisterPageFromHeaderMenu();
        String firstName = DataFakeManager.getRandomName();
        String lastName = DataFakeManager.getRandomName();
        String email = DataFakeManager.getRandomEmail();
        String password = DataFakeManager.getPassword(5, 24);
        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        // Do not enable the Privacy Toggle
        //registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnTheContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeywords = driver.getCurrentUrl().contains("/index.php?route=account/success&language");
        String errorMessage = "The url " + driver.getCurrentUrl() + "does not contains success keyword";
        Assertions.assertFalse(urlContainsTheCorrectKeywords, errorMessage);

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("route=account/register&language=en-gb");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The url belongs to register page");

    }

    @AfterEach
    public void executeTheMethodAfterEachTestCase(){
        DriverManager.getInstance().quiteTheDriver();
        System.out.println("The test number"+counter+ "finished!");
    }

}
