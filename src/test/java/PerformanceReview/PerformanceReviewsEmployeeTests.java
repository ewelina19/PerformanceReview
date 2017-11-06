package PerformanceReview;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.Common;
import pageobjects.EmployeeDetails;
import pageobjects.ListOfEmployees;
import pageobjects.ListOfPerformanceReviewsRequiringFeedback;
import pageobjects.PerformanceReviewDetails;
import testconfiguration.TestRunSettings;


public class PerformanceReviewsEmployeeTests {
    public WebDriver driver;

    Common common;
    ListOfPerformanceReviewsRequiringFeedback listOfPerformanceReviewsRequiringFeedback;
    PerformanceReviewDetails performanceReviewDetails;

    
    @BeforeClass(alwaysRun = true)
    @Parameters({})
    public void setupClass() {

        common = new Common(driver);
        listOfPerformanceReviewsRequiringFeedback = new ListOfPerformanceReviewsRequiringFeedback(driver);
        performanceReviewDetails = new PerformanceReviewDetails(driver);
        
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "baseURL", "user", "userPassword"})
    public void setupMethod(String browser, String baseURL, String user,  String userPassword) {
    	
        // Initialise the driver
        this.driver = new testconfiguration.TestDriver().Initialise(browser, baseURL);

        // login 
         common.login(user, userPassword);
         listOfPerformanceReviewsRequiringFeedback.NavigateToListOfPerformanceReviewsRequiringFeedback();
    }
      
    //Verify number of employees displayed on list of employees 
    @Test(description = "", groups = {""})
    public void VerifyEmployeesCount() {

        // Click Next
    	listOfPerformanceReviewsRequiringFeedback.VerifyCountOfEmployees(0);
    }

    //Verify list of employees is updated after a new employee added
    @Test(description = "", groups = {""})
    public void VerifySubmitFeedback() {
    	listOfPerformanceReviewsRequiringFeedback.VerifyEmployeeOnList("employeeName");
    	listOfPerformanceReviewsRequiringFeedback.SubmitFeedback("employeeName");
    	performanceReviewDetails.EnterPerformanceReviewDetails("review");
    	listOfPerformanceReviewsRequiringFeedback.VerifyEmployeeNotOnList("employeeName");
    }
    

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
       driver.quit();
    }
}
