package seleniumAutomationTestSettings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Entry {
    
    static WebDriver init() {
        // Specify the path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver_win32\\chromedriver.exe");

        // Set Chrome options to run headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the webpage
        driver.get("http://dev.trti-maha.in/");

        return driver;
    }

    static void traversNavBarLinks() {
        WebDriver driverState = init();
        // Find all <a> elements within the menu
        List<WebElement> menuLinks = driverState.findElements(By.cssSelector("nav.navbar a.nav-link, .dropdown-menu a.dropdown-item"));

        // Create a new text file
        try (FileWriter writer = new FileWriter("MenuLinks.txt")) {
            // Iterate through each menu link and write it to the text file
            for (WebElement menuLink : menuLinks) {
                // Get the href attribute of the menu link
                String href = menuLink.getAttribute("href");
                if (href != null && !href.isEmpty()) {
                    // Write the URL to the text file
                	System.out.println(href);
                    writer.write(href + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the WebDriver instance
        driverState.quit();
    }

    public static void main(String[] args) {
        traversNavBarLinks();
    }
}
