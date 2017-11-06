package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ListOfEmployees extends VerificationMethods {

    public ListOfEmployees(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // =========================         WEB PAGE ELEMENTS          =================================

    // Create employee button
    @FindBy(how = How.ID, using = "CreateEmployee")
    private WebElement createEmployeeButton;
    
    // List of employees - column: Names
    @FindBy(how = How.CLASS_NAME, using = "EmployeeName")
    private List<WebElement> listOfEmployeesNamesColumn;

    // List of employees - column: Actions - Update
    @FindBy(how = How.CLASS_NAME, using = "ActionUpdate")
    private List<WebElement> listOfEmployeesUpdateColumn;
    
    // List of employees - column: Actions - View
    @FindBy(how = How.CLASS_NAME, using = "ActionView")
    private List<WebElement> listOfEmployeesViewColumn;
    
    // List of employees - column: Actions - Remove
    @FindBy(how = How.CLASS_NAME, using = "ActionRemove")
    private List<WebElement> listOfEmployeesRemoveColumn;
    
    // List of employees - column: Actions - Performance Reviews
    @FindBy(how = How.CLASS_NAME, using = "ActionPerformanceReview")
    private List<WebElement> listOfEmployeesPerformanceReviewColumn;

    
    // ===========================            ACTIONS              =================================

    //Navigate to List of Employees page
    
    public void NavigateToEmployeesPage(){
    	
    }
    
    // Click Create Employee button   
    public void ClickCreateEmployeeButton(){
    	waitThenClick(createEmployeeButton, "Create Employee button");
    }

    //searching for the position on the list of a particular employee by name
    public int getIndexEmployeeName(String employeeName){
    	
    	int employeePosition= -1;
    	   	
    	for(int item=0; item<listOfEmployeesNamesColumn.size(); item++){
    		if(listOfEmployeesNamesColumn.get(0).getText().equals(employeeName)){
    			employeePosition=item;
    		};
    	}
    	return(employeePosition);
    }
    
    //Click 'Update Employee' for employee with a particular name  
    public void ClickUpdateEmployeeButton(String employeeName){
    	int employeePosition=getIndexEmployeeName(employeeName);
    	if (employeePosition>=0){
    		waitThenClick(listOfEmployeesUpdateColumn.get(employeePosition), "Update Employee button");
    	}
    }

    //Click 'View Employee' for employee with a particular name  
    public void ClickViewEmployeeButton(String employeeName){
    	int employeePosition=getIndexEmployeeName(employeeName);
    	if (employeePosition>=0){
    		waitThenClick(listOfEmployeesViewColumn.get(employeePosition), "View Employee button");
    	}
    }

    //Click 'Remove Employee' for employee with a particular name  
    public void ClickRemoveEmployeeButton(String employeeName){
    	int employeePosition=getIndexEmployeeName(employeeName);
    	if (employeePosition>=0){
    		waitThenClick(listOfEmployeesRemoveColumn.get(employeePosition), "Remove Employee");
    	}
    }

    //Click 'Performance Reviews' for employee with a particular name  
    public void ClickPerformanceReviewButton(String employeeName){
    	int employeePosition=getIndexEmployeeName(employeeName);
    	if (employeePosition>=0){
    		waitThenClick(listOfEmployeesPerformanceReviewColumn.get(employeePosition), "Performance Reviews button");
    	}
    }

    // ============================        VERIFICATIONS          =================================

    //verify count of employees 
    public void VerifyCountOfEmployees(int expectedSize){
    	VerifyListSize(listOfEmployeesNamesColumn, expectedSize, "List of employees size does not equal expacted size "+expectedSize);
    }

    //verify employee not on list by employee's name 
    public void VerifyEmployeeNotOnList(String employeeName){
    	VerifyElementNotOnListByTextNotCaseSensitive(listOfEmployeesNamesColumn, employeeName, "Employee '"+employeeName+"' exists on the list of employees but not expected");
    }

    //verify employee exists on list by employee's name 
    public void VerifyEmployeeOnList(String employeeName){
    	VerifyElementNotOnListByTextNotCaseSensitive(listOfEmployeesNamesColumn, employeeName, "Employee '"+employeeName+"' doesn't exist on the list of employees but is expected");
    }
    

}