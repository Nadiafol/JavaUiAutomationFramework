package com.opencart;

import com.opencart.managers.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        // one way to define a driver manager
        WebDriver driver = DriverManager.getInstance().getDriver();

        driver.get("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/4.9.1");
        Thread.sleep(2000);
        String cardCurrentWindowName = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.linkedin.com/signup/cold-join?session_redirect=https%3A%2F%2Fwww%2Elinkedin%2Ecom%2Ffeed%2F%3Ftrk%3Dguest_homepage-basic_sign-in-submit&trk=login_reg_redirect");
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(cardCurrentWindowName);
        driver.get("https://translate.google.com/?sl=auto&tl=ro&text=spare&op=translate&hl=ro");
        Thread.sleep(2000);
        driver.quit();

        System.out.println("The execution was finished.");
    }
}