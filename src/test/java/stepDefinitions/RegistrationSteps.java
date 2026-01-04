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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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


    //SCENARIO 1 - Happy path!

    @Given("that I am on the registration page")
    public void openRegistrationSite() {
        driver = new ChromeDriver();
        driver.get("file:///C:/Users/malaj/Desktop/MVT 2025 - 2027/04 - Testautomatisering och programmering/INLÄMNING 2/Register.html");
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


    //SCENARIO 2 - Efternamn saknas
    // Utan @Given då den är identisk. Cucumber matchar från feature-filen då det steget redan finns!
    @When("I fill in all required registration fields except last name")
    public void iFillInAllRequiredRegistrationFieldsExceptLastName() {
        //MEMBER DETAILS
        driver.findElement(By.id("dp")).sendKeys("01/04/1991");
        driver.findElement(By.id("member_firstname")).sendKeys("Mala");
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
        //Tvingar till att klicka på elementet
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"))
        );

    }

    @Then("an error message should appear informing me that last name is required")
    public void anErrorMessageShouldAppearInformingMeThatLastNameIsRequired() throws InterruptedException {

        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[for='member_lastname']")));

        String actual = errorMessage.getText();
        String expected = "Last Name is required";

        assertEquals(expected, actual);

        driver.quit();

    }


    //Scenario 3 - mismatchade lösenord
    //Skippar @given och @and då given är definierad och @and inte behövs då det inte ska gå att slutföra registrering
    @When("I fill in all required registration fields but use mismatched passwords {string} and {string}")
    public void iFillInAllRequiredRegistrationFieldsButUseMismatchedPasswords(String password, String confirmPassword) {

        //MEMBER DETAILS
        driver.findElement(By.id("dp")).sendKeys("01/04/1991");
        driver.findElement(By.id("member_firstname")).sendKeys("Mala");
        driver.findElement(By.id("member_lastname")).sendKeys("Jallow Nyström");
        driver.findElement(By.id("member_emailaddress")).sendKeys("mala.jallow@live.se");
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("mala.jallow@live.se");

        //CHOOSE YOUR PASSWORD
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(confirmPassword);

        //WHICH OF THESE BEST DESCRIBE YOUR ROLE/S IN BASKETBALL?
        scrollAndClick(By.cssSelector("label[for='signup_basketballrole_19']"));


        //ACCOUNT INFORMATION
        scrollAndClick(By.cssSelector("label[for='sign_up_25']"));
        scrollAndClick(By.cssSelector("label[for='sign_up_26']"));


        //COMMUNICATION PREFERENCES
        scrollAndClick(By.cssSelector("label[for='sign_up_27']"));
        scrollAndClick(By.cssSelector("label[for='sign_up_28']"));

        //CODE OF ETHICS AND CONDUCT (APPLIES TO ALL MEMBERS)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                driver.findElement(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"))
        );


    }

    @Then("an error message should appear informing me that the passwords do not match")
    public void anErrorMessageShouldAppearInformingMeThatThePasswordsDoNotMatch() {
        WebElement errorMessage = driver.findElement(By.cssSelector("span[for='signupunlicenced_confirmpassword']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", errorMessage);

        String actual = errorMessage.getText();
        String expected = "Password did not match";

        assertEquals(expected, actual);

        driver.quit();
    }




    //SCENARIO 4 - Terms and conditions är inte godkänt
    //Tagit bort delen med kod för CODE OF ETHICS AND CONDUCT där T&C ligger
    @When("I fill in all required registration fields except accepting terms and conditions")
    public void iFillInAllRequiredRegistrationFieldsExceptAcceptingTermsAndConditions() {
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

    }



    @Then("an error message should appear informing me that terms and conditions must be accepted")
    public void errorMessageInformingMeThatTermsAndConditionsMustBeAccepted() {

            WebElement errorMessage = driver.findElement(By.cssSelector("span[for='AgreeToCodeOfEthicsAndConduct']"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", errorMessage);

            String actual = errorMessage.getText();
            String expected = "You must confirm that you have read, understood and agree to the Code of Ethics and Conduct";

            assertEquals(expected, actual);

            driver.quit();
        }


    //Identiskt scenario & steps till Happy path men med Firefox för multi browser
    //Säger att Chrome är standard browsern men att köra FirefoxDriver OM den upptäcks
    @Given("that I am on the registration page using {string}")
    public void openRegistrationSiteWithBrowser(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driver.get("file:///C:/Users/malaj/Desktop/MVT 2025 - 2027/04 - Testautomatisering och programmering/INLÄMNING 2/Register.html");

    }
}





