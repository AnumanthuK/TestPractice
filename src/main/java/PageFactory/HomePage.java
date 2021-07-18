package PageFactory;

import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Scenario scenario;
    private JavascriptExecutor js;


    public HomePage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 30);
        js=(JavascriptExecutor)driver;
        //launchAttempts = 1;
    }

    @FindBy(xpath = "//div[@class='borrow__question']//ul[@role='radiogroup'] //label[@for='application_type_single']")
    private WebElement appTypeSingle;
    @FindBy(xpath = "//div[@class='borrow__question']//ul[@role='radiogroup'] //label[@for='application_type_joint']")
    private WebElement appTypeJoint;
    @FindBy(xpath = "//select[contains(@title,'Number of dependants')]")
    private WebElement noOfDependants;
    @FindBy(xpath = "//label[normalize-space()='Home to live in']")
    private WebElement borrowTypeHome;
    @FindBy(xpath = "//label[normalize-space()='Residential investment']")
    private WebElement borrowTypeResident;
    @FindBy(xpath = "//input[@aria-labelledby='q2q1']")
    private WebElement income;
    @FindBy(xpath = "//input[@aria-labelledby='q2q2']")
    private WebElement otherIncome;
    @FindBy(xpath = "//input[@aria-labelledby='q3q1']")
    private WebElement expenses;
    @FindBy(xpath = "//input[@aria-labelledby='q3q2']")
    private WebElement homeLoanRepayments;
    @FindBy(xpath = "//input[@aria-labelledby='q3q3']")
    private WebElement otherLoanRepayments;
    @FindBy(xpath = "//input[@aria-labelledby='q3q4']")
    private WebElement commitments;
    @FindBy(xpath = "//input[@aria-labelledby='q3q5']")
    private WebElement creditcardLimits;

    @FindBy(xpath = "//div[@class='borrow__question__answer borrow__question__answer--select']")
    private WebElement dependantDropdown;



    @FindBy(xpath = "//button[normalize-space()='Work out how much I could borrow']")
    private WebElement borrowbutton;

    @FindBy(xpath = "//span[@id='borrowResultTextAmount']")
    private WebElement estimates;

    @FindBy(xpath = "//span[@class='icon icon_restart']")
    private WebElement starOverButton;

    @FindBy(xpath = "//span[contains(@class,'borrow__error__text')]")
    private WebElement borrowErrorMessage;


    //div[@class='result']


    public boolean clickOnSingle() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(appTypeSingle));
            appTypeSingle.click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean clickOnJoint() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(appTypeJoint));
            appTypeJoint.click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean selectNoOfDependent(String dependantsCount) {
        try {
            Select s = new Select(noOfDependants);
            List<WebElement> dependents = s.getOptions();
            dependantDropdown.click();
            Iterator<WebElement> itr = dependents.iterator();
            while (itr.hasNext()) {
                WebElement requiredDependant = itr.next();
                String str = requiredDependant.getText();
                if (str.equalsIgnoreCase(dependantsCount)) {
                    s.selectByVisibleText(dependantsCount);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            scenario.log("Exception occured when selecting dependants");
            return false;
        }
        return true;
    }

    public boolean clickOnHome() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(borrowTypeHome));
            borrowTypeHome.click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean clickOnResident() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(borrowTypeResident));
            borrowTypeResident.click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean enterIncome(String income1) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(income));
            income.sendKeys(income1);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean enterOtherIncome(String otherIcome) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(otherIncome));
            otherIncome.sendKeys(otherIcome);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean enterExpenses(String expenses1) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(expenses));
            expenses.sendKeys(expenses1);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    public boolean enterHomeLoanRepayments(String loanRepayments) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homeLoanRepayments));
            homeLoanRepayments.sendKeys(loanRepayments);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean enterOtherLoanRepayments(String otherLoanRepayments1) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(otherLoanRepayments));
            otherLoanRepayments.sendKeys(otherLoanRepayments1);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean enterCommitments(String commitments1) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(commitments));
            commitments.sendKeys(commitments1);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean enterCredits(String credits) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(creditcardLimits));
            creditcardLimits.sendKeys(credits);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean clickOnBorrowIcon() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(borrowbutton));
            borrowbutton.click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean clickOnStartOverButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(starOverButton));
            starOverButton.click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean IsStartOverButtonDisplayed() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(starOverButton));
            return starOverButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return borrowErrorMessage.getText();
    }

    public String getEstimate() {
        return estimates.getText();
    }


    public String getNoOfDependants() {
        Select s = new Select(noOfDependants);
        return s.getFirstSelectedOption().getText();
    }

    public String getIncome() {
        return income.getAttribute("value");
    }

    public String getOtherIncome() {
        return otherIncome.getAttribute("value");
    }

    public String getExpenses() {
        return expenses.getAttribute("value");
    }

    public String getHomeLoanRepayments() {
        return homeLoanRepayments.getAttribute("value");
    }

    public String getOtherLoanRepayments() {
        return otherLoanRepayments.getAttribute("value");
    }

    public String getCommitments() {
        return commitments.getAttribute("value");
    }

    public String getCreditcardLimits() {
        return creditcardLimits.getAttribute("value");
    }




}
