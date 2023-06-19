package com.opencart;

import com.opencart.managers.DataFakeManager;
import com.opencart.managers.DriverManager;

import com.opencart.managers.ScrollManager;
import org.openqa.selenium.*;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        // one way to define a driver manager
        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.get("https://www.google.com/");

        String currentWindowName = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://andreisecuqa.host/");
        Thread.sleep(500);

        WebElement accountIcon = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        accountIcon.click();
        Thread.sleep(500);
        WebElement registerButton = driver.findElement(By.xpath("//a[normalize-space()='Register']"));
        registerButton.click();
        Thread.sleep(500);

        String firstName = DataFakeManager.getRandomName();
        System.out.println("The generated first name is: " + firstName);
        String lastName = DataFakeManager.getRandomName();
        System.out.println("The generated last name is: " + lastName);
        String email = DataFakeManager.getRandomEmail();
        System.out.println("The generated email is: " + email);
        String password = DataFakeManager.getPassword(5, 24);
        System.out.println("The generated password is: " + password);

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys(firstName);
        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys(lastName);
        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(email);
        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(password);

        WebElement privacyToggle = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        ScrollManager.scrollToElement(driver, privacyToggle);
        privacyToggle.click();
        Thread.sleep(1000);

        WebElement continueButton = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
        continueButton.click();
        Thread.sleep(1000);

        System.out.println(driver.getCurrentUrl());
        driver.close();
        Thread.sleep(1000);

        driver.switchTo().window(currentWindowName);
        driver.get("https://andreisecuqa.host/");
        driver.quit();

        System.out.println("The execution was finished.");
    }
}