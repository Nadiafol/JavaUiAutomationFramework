package com.opencart;

import com.opencart.managers.DataFakeManager;
import com.opencart.managers.DriverManager;

import com.opencart.pageobjects.AccountCreatedPage;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.openqa.selenium.*;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        // one way to define a driver manager
        WebDriver driver = DriverManager.getInstance().getDriver();
        
        driver.get("https://andreisecuqa.host/");
        Thread.sleep(500);

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPageFromHeaderMenu();


        String firstName = DataFakeManager.getRandomName();
        String lastName = DataFakeManager.getRandomName();
        String email = DataFakeManager.getRandomEmail();
        String password = DataFakeManager.getPassword(5, 24);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnTheContinueButton();

        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.logoutFromTheAccount();

        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        driver.quit();

        System.out.println("The execution was finished.");
    }
}