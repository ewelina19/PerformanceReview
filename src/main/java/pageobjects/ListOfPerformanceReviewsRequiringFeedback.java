package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ListOfPerformanceReviewsRequiringFeedback extends VerificationMethods {

    public ListOfPerformanceReviewsRequiringFeedback(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ============================       WEB PAGE ELEMENTS         =================================
    
    // Names of employees
    @FindBy(how = How.ID, using = "employeesNames")
    private List<WebElement> employeesNamesColumn;
    
    // Submit feedback button
    @FindBy(how = How.ID, using = "submitFeedback")
    private List<WebElement> submitFeedbackColumn;

        
    // ============================            ACTIONS              =================================

    //navigate to List of Performance Reviews Requiring Feedback page
    public void NavigateToListOfPerformanceReviewsRequiringFeedback(){
    	
    }
    
    //searching for the position on the list of a particular employee by name
    public int getIndexEmployeeName(String employeeName){
    	
    	int employeePosition= -1;
    	   	
    	for(int item=0; item<employeesNamesColumn.size(); item++){
    		if(employeesNamesColumn.get(0).getText().equals(employeeName)){
    			employeePosition=item;
    		};
    	}
    	return(employeePosition);
    }
    
    //Click Submit feedback for employee by name  
    public void ClickSubmitFeedback(String employeeName){
    	int employeePosition=getIndexEmployeeName(employeeName);
    	if (employeePosition>=0){
    		click(submitFeedbackColumn.get(employeePosition), "Submit Feedback button");
    	}
    }

    // ============================         VERIFICATIONS           =================================


    //verify count of employees 
    public void VerifyCountOfEmployees(int expectedSize){
    	VerifyListSize(employeesNamesColumn, expectedSize, "List of employees count does not equal expacted count "+expectedSize);
    }
    
    //verify employee not on list by employee's name 
    public void VerifyEmployeeNotOnList(String employeeName){
    	VerifyElementNotOnListByTextNotCaseSensitive( employeesNamesColumn, employeeName, "Employee '"+employeeName+"' exists on the list of employees but not expected");
    }

    //verify employee exists on list by employee's name 
    public void VerifyEmployeeOnList(String employeeName){
    	VerifyElementOnListByTextNotCaseSensitive(employeesNamesColumn, employeeName, "Employee '"+employeeName+"' doesn't exist on the list of employees but is expected");
    }

}