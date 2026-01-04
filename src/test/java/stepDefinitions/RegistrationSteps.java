package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationSteps {

    //Sätts här så alla metoderna kommer åt den - public static så att WebDriver tillhör objektet och inte kastas
    public static WebDriver driver;

    //Ser till att scrolla då att element kan hittas som inte syns direkt
    public void scrollAndClick(By locator) {
        WebElement element = driver.findElement(locator);
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }


    @Given("that I am on the registration page")
    public void openRegistrationSite() {
        driver = new ChromeDriver();
        driver.get("file:///C:/Users/malaj/Desktop/MVT%202025%20-%202027/04%20-%20Testautomatisering%20och%20programmering/INL%C3%84MNING%202/Register.html");
    }

    @When("I fill in all required registration fields correctly")
    public void fillInRequiredFieldsCorrectly() {

        //MEMBER DETAILS
        driver.findElement(By.id("dp")).sendKeys("01/04/1991");
        driver.findElement(By.id("member_firstname")).sendKeys("Mala");
        driver.findElement(By.id("member_lastname")).sendKeys("Jallow Nyström");
        driver.findElement(By.id("member_emailaddress")).sendKeys("mala.jallow@live.se");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("mala.jallow@live.se");

        //CHOOSE YOUR PASSWORD
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("MyPassWord!");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("MyPassWord!");

        //WHICH OF THESE BEST DESCRIBE YOUR ROLE/S IN BASKETBALL?
        scrollAndClick(By.cssSelector("label[for='signup_basketballrole_19']"));


        //ACCOUNT INFORMATION
        scrollAndClick(By.cssSelector("label[for='sign_up_25']"));
        scrollAndClick(By.cssSelector("label[for='sign_up_26']"));


        //COMMUNICATION PREFERENCES
        scrollAndClick(By.cssSelector("label[for='sign_up_27']"));
        scrollAndClick(By.cssSelector("label[for='sign_up_28']"));

        //CODE OF ETHICS AND CONDUCT (APPLIES TO ALL MEMBERS)
        //
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"))
        );


    }



    @And("I submit the registration form")
    public void klickToSubmitRegistration() {
        //CONFIRM AND JOIN (Knappen för att gå med)
        scrollAndClick(By.name("join"));



    }


    @Then("the registration should be completed successfully")
    public void registrationCompletedSuccessfully() throws InterruptedException {
        WebElement successMessage = driver.findElement(By.cssSelector("h2.bold.gray.text-center.margin-bottom-40"));
        String actual = successMessage.getText();
        String expected = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";

        assertEquals(expected, actual);

        //En 5-sekunders paus så att success-sidan hinner visas innan det stängs ner
        Thread.sleep(5000);

        driver.quit();

    }
}
