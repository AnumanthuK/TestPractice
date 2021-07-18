package stepDefinitions;

import PageFactory.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LoanCalucation {

    private WebDriver driver;
    private Scenario scenario;
    private HomePage homePage;
    private static final String expectedMsg = "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.";

    @Before()
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver, scenario);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before()
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @After()
    public void afterScenario() {
        //driver.close();
    }

    @Given("navigate to loan estimate application")
    public void navigate_to_loan_estimate_application() throws IOException, InterruptedException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config/urls.properties");
        prop.load(fis);
        driver.get(prop.getProperty("url"));
        Thread.sleep(3000);
        scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "png", "Taken screenShot for given step ");

    }

    @Then("enter following details")
    public void enter_following_details(DataTable dataTable) {

        List<Map<String, String>> listOfMaps = dataTable.asMaps();
        for (Map<String, String> map : listOfMaps) {
            if (map.containsKey("AppType")) {
                if (map.get("AppType").contains("Single"))
                    homePage.clickOnSingle();
                else
                    homePage.clickOnJoint();

            }
            if (map.containsKey("No_of_dependants")) {
                homePage.selectNoOfDependent(map.get("No_of_dependants"));
            }

            if (map.containsKey("buy_property")) {
                if (map.get("buy_property").contains("Home"))
                    homePage.clickOnHome();
                else
                    homePage.clickOnResident();
            }

            if (map.containsKey("Income")) {
                homePage.enterIncome(map.get("Income"));
            }

            if (map.containsKey("Other_income")) {
                homePage.enterOtherIncome(map.get("Other_income"));
            }

            if (map.containsKey("Expenses")) {
                homePage.enterExpenses(map.get("Expenses"));
            }

            if (map.containsKey("Loan_repayment")) {
                homePage.enterHomeLoanRepayments(map.get("loan_repayment"));
            }

            if (map.containsKey("Other_loan_repayment")) {
                homePage.enterOtherLoanRepayments(map.get("Other_loan_repayment"));
            }
            if (map.containsKey("Commitments")) {
                homePage.enterCommitments(map.get("Commitments"));
            }
            if (map.containsKey("Credit_card_limit")) {
                homePage.enterCredits(map.get("Credit_card_limit"));
            }


        }


    }


    @Then("capture the estimate")
    public void capture_the_estimate() {
        homePage.getEstimate();
    }

    @Then("Asert estimate you borrow should be {string}")
    public void asert_estimate_you_borrow_should_be(String expectedEstimate) throws InterruptedException {
        Thread.sleep(3000);
        String actualEstimate = homePage.getEstimate();
        scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "png", "Taken screenShot for given step ");
        Assert.assertEquals(actualEstimate, expectedEstimate, " Actaul estimate from Application : " + actualEstimate + " and your Expected estimate is " + expectedEstimate + " The Expected estimate is not equals to Expected Estimate and check manully");


    }


    @When("click on how much I could borrow")
    public void click_on_how_much_i_could_borrow() {
        homePage.clickOnBorrowIcon();

    }

    @Then("validate start over button appears or not")
    public void validate_start_over_button_appears_or_not() {
        Assert.assertTrue(homePage.IsStartOverButtonDisplayed(), "Failed Start Over button is not displayed");
    }

    @Then("click on start over button")
    public void click_on_start_over_button() {

        homePage.clickOnStartOverButton();

    }

    @Then("validate the form is cleared or not")
    public void validate_the_form_is_cleared_or_not() {
        //Check Imp fields cleared or not due to time constraint
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(homePage.getExpenses().equalsIgnoreCase("0"), " The Living expenses is Not cleared");
        sa.assertTrue(homePage.getIncome().equalsIgnoreCase("0"), " Income is Not cleared");
        sa.assertTrue(homePage.getOtherIncome().equalsIgnoreCase("0"), " Other Income is Not cleared");
        sa.assertTrue(homePage.getHomeLoanRepayments().equalsIgnoreCase("0"), " Loan Payments Not cleared");
        sa.assertTrue(homePage.getOtherLoanRepayments().equalsIgnoreCase("0"), " Other Loan Payments Not cleared");
        sa.assertTrue(homePage.getCommitments().equalsIgnoreCase("0"), " Commitments Not cleared");
        sa.assertTrue(homePage.getCreditcardLimits().equalsIgnoreCase("0"), " Credit card limits Not cleared");
        sa.assertTrue(homePage.getNoOfDependants().equalsIgnoreCase("0"), " Dependants dropdown Not cleared");

        sa.assertAll();
    }


    @When("enter dollar {string} for Living expenses and other fields are zero")
    public void enter_dollar_for_living_expenses_and_other_fields_are_zero(String strDollar) {
        homePage.enterExpenses(strDollar);
    }

    @Then("validate the required message appeared correctly or not")
    public void validate_the_required_message_appeared_correctly_or_not() throws InterruptedException {
        Thread.sleep(2000);
        String actualMsg = homePage.getErrorMessage();
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is displayed is Not as expected");
    }

}
