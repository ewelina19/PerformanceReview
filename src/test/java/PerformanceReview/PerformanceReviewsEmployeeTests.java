package PerformanceReview;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.Common;
import pageobjects.ListOfPerformanceReviewsRequiringFeedback;
import pageobjects.PerformanceReviewDetails;


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

    	listOfPerformanceReviewsRequiringFeedback.VerifyCountOfEmployees(0);
    }

    //Verify submit feedback by employee's name
    @Test(description = "", groups = {""})
    public void VerifySubmitFeedback() {
    	listOfPerformanceReviewsRequiringFeedback.VerifyEmployeeOnList("employeeName");
    	listOfPerformanceReviewsRequiringFeedback.ClickSubmitFeedback("employeeName");
    	performanceReviewDetails.EnterPerformanceReviewDetails("review");
    	listOfPerformanceReviewsRequiringFeedback.VerifyEmployeeNotOnList("employeeName");
    }
    

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
       driver.quit();
    }
}
