package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PerformanceReviewDetails extends VerificationMethods {

    public PerformanceReviewDetails(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    
    // ============================      WEB PAGE ELEMENTS           =================================

    // Name of employee which is reviewed
    @FindBy(how = How.ID, using = "employeeName")
    private WebElement employeeNameInput;
    
    // Details of performance review
    @FindBy(how = How.ID, using = "performanceReview")
    private WebElement performanceReviewInput;

    // Submit performance review button
    @FindBy(how = How.ID, using = "performanceReviewSubmitButton")
    private WebElement performanceReviewSubmitButton;
    
    // Cancel performance review button
    @FindBy(how = How.ID, using = "performanceReviewCancelButton")
    private WebElement performanceReviewCancelButton;

    // ============================          ACTIONS                 =================================

    // Click Performance Review  Submit button 
    public void ClickPerformanceReviewSubmitButton(){
    	waitThenClick(performanceReviewSubmitButton, "Performance Review Submit button");
    }

    // Click Performance Review  Cancel button 
    public void ClickPerformanceReviewCancelButton(){
    	waitThenClick(performanceReviewSubmitButton, "Performance Review Submit button");
    }

    // Enter details of performance review    
    public void EnterPerformanceReviewDetails(String review){
    	waitThenType(performanceReviewInput, review, "Performance Review Input");
    }

    // ============================       VERIFICATIONS              =================================

    // Verify that name of employee which is reviewed is disabled
    public void VerifyEmployeeNameDisabled(){
    	VerifyElementNotEnabled(employeeNameInput, "Employee Name Input", "Element is enabled but expected disabled");
    }

    // Verify details of performance review is disabled on View page
    public void VerifyPerformanceReviewDisabled(){
    	VerifyElementNotEnabled(employeeNameInput, "Employee Name Input", "Element is enabled but expected disabled");
    }

    // Verify details of performance review is enabled on Update page
    public void VerifyPerformanceReviewEnabled(){
    	VerifyElementNotEnabled(employeeNameInput, "Employee Name Input", "Element is disabled but expected enabled");
    }


}