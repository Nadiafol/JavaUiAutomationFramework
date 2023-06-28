package com.opencart.managers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
    private static String webDriverType = "Chrome";
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        switch (webDriverType.toUpperCase()) {
            case "CHROME":
                //WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("The Chrome driver was initiated.");
                break;
            case "FIREFOX":

                driver = new FirefoxDriver();
                System.out.println("The Firefox driver was initiated.");
                break;
            case "EDGE":

                driver = new EdgeDriver();
                System.out.println("The Edge driver was initiated.");
                break;
            default:
                System.out.println("There is not defined such a driver:" + webDriverType);
        }
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            DriverManager.getInstance();
        }
        return driver;
    }
    public void quiteTheDriver (){
        driver.quit();
        driver = null;
       instance = null;
        System.out.println("The driver is quite and instance reset.");

    }
}
