package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class PerformanceReviews extends VerificationMethods {

    public PerformanceReviews(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


  // ============================         WEB PAGE ELEMENTS         =================================

    //  Add Performance Review button
    @FindBy(how = How.ID, using = "AddPerformanceReview")
    private WebElement addPerformanceReviewButton;
    
    // Column: Date
    @FindBy(how = How.CLASS_NAME, using = "PerformanceDate")
    private List<WebElement> performanceReviewDateColumn;

    // Column: Actions - Update
    @FindBy(how = How.CLASS_NAME, using = "PerformanceActionUpdate")
    private List<WebElement> performanceReviewUpdateColumn;
    
    // Column: Actions - View
    @FindBy(how = How.CLASS_NAME, using = "PerformanceActionView")
    private List<WebElement> performanceReviewViewColumn;
    
    //  Assign Employee to participate in Performance Review button
    @FindBy(how = How.ID, using = "AssignEmployee")
    private WebElement assignEmployeesButton;
    
    // Assigned Employee Column: Date
    @FindBy(how = How.CLASS_NAME, using = "AssignedEmployeePerformanceDate")
    private List<WebElement> assignedEmployeePerformanceReviewDateColumn;
    
    // Assigned Employee Column: Actions - View
    @FindBy(how = How.CLASS_NAME, using = "PerformanceActionView")
    private List<WebElement> assignedEmployeePerformanceReviewViewColumn;
    
    
  // ============================              ACTIONS              =================================
    
    // Click Add Performance Review   
    public void ClickCreateEmployeeButton(){
    	waitThenClick(addPerformanceReviewButton, "Add Performance Review button");
    }
    
    // Click Assign employees to participate Performance Review   
    public void ClickAssignEmployeesButton(){
    	waitThenClick(assignEmployeesButton, "Assign employees to participate Performance Review");
    }
   

    //searching for the position on the list of a particular review by date
    public int getIndexEmployeeName(String employeeReviewDate){
    	
    	int employeePosition= -1;
    	   	
    	for(int item=0; item<performanceReviewDateColumn.size(); item++){
    		if(performanceReviewDateColumn.get(0).getText().equals(employeeReviewDate)){
    			employeePosition=item;
    		};
    	}
    	return(employeePosition);
    }

    //Click 'Update Review' 
    public void ClickUpdateEmployeeButton(String employeeReviewDate){
    	int employeePosition=getIndexEmployeeName(employeeReviewDate);
    	if (employeePosition>=0){
    		waitThenClick(performanceReviewUpdateColumn.get(employeePosition), "Update Performance Review button");
    	}
    }

    //Click 'View Review'  
    public void ClickViewEmployeeButton(String employeeReviewDate){
    	int employeePosition=getIndexEmployeeName(employeeReviewDate);
    	if (employeePosition>=0){
    		waitThenClick(performanceReviewViewColumn.get(employeePosition), "View Performance Review button");
    	}
    }

    
    
  // ============================            VERIFICATION           =================================


}