package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AssignEmployeesToParticipateInPerformanceReview extends VerificationMethods {

    public AssignEmployeesToParticipateInPerformanceReview(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ============================       WEB PAGE ELEMENTS         =================================
    
    // Names of employees
    @FindBy(how = How.ID, using = "employeesNames")
    private List<WebElement> employeesNamesColumn;
    
    // Checkbox
    @FindBy(how = How.ID, using = "selectEmployee")
    private List<WebElement> checkboxColumn;

    // Submit button
    @FindBy(how = How.ID, using = "performanceReviewSubmitButton")
    private WebElement performanceReviewSubmitButton;
    
    // Cancel button
    @FindBy(how = How.ID, using = "performanceReviewCancelButton")
    private WebElement performanceReviewCancelButton;
    
    
    // ============================            ACTIONS              =================================

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
    
    //Select checkbox for employee by name  
    public void ClickUpdateEmployeeButton(String employeeName){
    	int employeePosition=getIndexEmployeeName(employeeName);
    	if (employeePosition>=0){
    		click(checkboxColumn.get(employeePosition), "Update Employee button");
    	}
    }

    // ============================         VERIFICATIONS           =================================


    //verify count of employees 
    public void VerifyCountOfEmployees(int expectedSize){
    	VerifyListSize(employeesNamesColumn, expectedSize, "List of employees count does not equal expacted count "+expectedSize);
    }

}