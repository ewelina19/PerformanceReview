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
import testconfiguration.TestRunSettings;


public class PerformanceReviewsAdminTests {
    public WebDriver driver;

    Common common;
    ListOfEmployees listOfEmployees;
    EmployeeDetails employeeDetails;

    
    @BeforeClass(alwaysRun = true)
    @Parameters({})
    public void setupClass() {

        common = new Common(driver);
        listOfEmployees = new ListOfEmployees(driver);
        employeeDetails = new EmployeeDetails(driver);
        
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "baseURL", "user", "userPassword"})
    public void setupMethod(String browser, String baseURL, String user,  String userPassword) {
    	
        // Initialise the driver
        this.driver = new testconfiguration.TestDriver().Initialise(browser, baseURL);

        // login 
         common.login(user, userPassword);
         listOfEmployees.NavigateToEmployeesPage();
    }
      
    //Verify number of employees displayed on list of employees 
    @Test(description = "", groups = {""})
    public void VerifyEmployeesCount() {

        // Click Next
    	listOfEmployees.VerifyCountOfEmployees(0);
    }

    //Verify list of employees is updated after a new employee added
    @Test(description = "", groups = {""})
    public void VerifyListOfEmployeesUpdatedAfterNewAdded() {
    	employeeDetails.VerifyCreateEmployee(TestRunSettings.GetRunData("Employees.yaml"));
        
    }

    //Verify View employee
    @Test(description = "", groups = {""}, dependsOnMethods={"VerifyListOfEmployeesUpdatedAfterNewAdded"})
    public void VerifyViewEmployee() {
    	employeeDetails.VerifyViewEmployee(TestRunSettings.GetRunData("Employees.yaml"));
        
    }
    //Verify Update employee
    @Test(description = "", groups = {""}, dependsOnMethods={"VerifyViewEmployee"})
    public void VerifyUpdateEmployee() {
    	employeeDetails.VerifyUpdateEmployee(TestRunSettings.GetRunData("Employees.yaml"));
        
    }
    
    //Verify list of employees is updated after employee removed
    @Test(description = "", groups = {""}, dependsOnMethods={"VerifyUpdateEmployee"})
    public void VerifyListOfEmployeesUpdatedAfterEmployeeRemoved() {
    	employeeDetails.VerifyRemoveEmployee(TestRunSettings.GetRunData("Employees.yaml"));
    }
    
    
    //Verify Create employee - all mandatory data entered
    @Test(description = "", groups = {""})
    public void VerifyCreateEmployeeAllMandatoryDataEntered() {

        
    }
    
    //Verify Create employee - not all mandatory data entered
    @Test(description = "", groups = {""})
    public void VerifyCreateEmployeeNotAllMandatoryDataEntered() {

        
    }

    

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
       driver.quit();
    }
}
