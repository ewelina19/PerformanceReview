package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeDetails extends VerificationMethods {	
	
	ListOfEmployees listOfEmployees;
	
    public EmployeeDetails(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    
    // ========================         WEB PAGE ELEMENTS       =================================

    // Create employee - Submit button
    @FindBy(how = How.ID, using = "Submit")
    private WebElement submitButton;
    
    // Create employee - Cancel button
    @FindBy(how = How.ID, using = "Cancel")
    private WebElement cancelButton;

    
    // First Name input
    @FindBy(how = How.ID, using = "EmployeeName")
    private WebElement employeeNameInput;

    // Surname input
    @FindBy(how = How.ID, using = "EmployeeSurname")
    private WebElement employeeSurnameInput;
    
    // Position
    @FindBy(how = How.ID, using = "Position")
    private WebElement employeePositionInput;
    
    // Date Started
    @FindBy(how = How.ID, using = "DateStarted")
    private WebElement employeeDateStartedInput;
        
    
    
    // ========================              ACTIONS            =================================

    public void ClickSubmitCreateEmployee(){
    	click(submitButton, "Submit Create Employee");
    }

    public void ClickCancelCreateEmployee(){
    	click(submitButton, "Submit Create Employee");
    }

    public void SetName(String employeeName){
    	waitThenType(employeeNameInput, employeeName, "First Name input");
    }
    
    public void SetSurname(String employeeSurname){
    	waitThenType(employeeSurnameInput, employeeSurname, "Surname input");
    }

    public void SetPosition(String employeePosition){
    	waitThenType(employeePositionInput, employeePosition, "Position input");
    }

    public void SetDateStarted(String employeeStartDate){
    	waitThenType(employeeDateStartedInput, employeeStartDate, "Start Date input");
    }
    

    //update employee data
    public void UpdateEmployee(){
    	
    }

    // ========================        VERIFICATION METHODS     =================================

    //verify create employee using data from YAML file
    public void VerifyCreateEmployee(Map employeeDataFile){
    	
    	listOfEmployees = new ListOfEmployees(driver);
    	ArrayList employeesData;
        employeesData = (ArrayList) employeeDataFile.get("employees");
        
        for (Object employeeData : employeesData) {
        	
        	//click Create Employee button on the list of employees
        	listOfEmployees.ClickCreateEmployeeButton();
        	
        	Map<String, String> employeeDataDetails = (Map<String, String>) employeeData;
        	
        	//enter data from YAML file
        	SetName((String) employeeDataDetails.get("employee-first-name"));
        	SetSurname((String) employeeDataDetails.get("employee-surname"));
        	SetPosition((String) employeeDataDetails.get("employee-position"));
        	SetDateStarted((String) employeeDataDetails.get("employee-date-started"));
        	
        	//click Submit button
        	ClickSubmitCreateEmployee();
        	listOfEmployees.VerifyEmployeeOnList("(String) employeeDataDetails.get(\"employee-first-name\")"
        											+" "+"SetSurname((String) employeeDataDetails.get(\"employee-surname\"))");
        }
    }

    
    //verify employee's data on View page using data from YAML file
    //verify data on View page are disabled
    public void VerifyViewEmployee(Map employeeDataFile){
    	listOfEmployees = new ListOfEmployees(driver);
    	ArrayList employeesData;
        employeesData = (ArrayList) employeeDataFile.get("employees");
        
        for (Object employeeData : employeesData) {
        	
        	Map<String, String> employeeDataDetails = (Map<String, String>) employeeData;
        	//click View button  
        	listOfEmployees.ClickViewEmployeeButton("(String) employeeDataDetails.get(\"employee-first-name\")"
													+" "+"SetSurname((String) employeeDataDetails.get(\"employee-surname\"))");        	
        	//verify data on View employee page equals data in YAML file
        	VerifyText(employeeNameInput, (String) employeeDataDetails.get("employee-first-name"), "Employee Name input", "Data not as expected");
        	VerifyText(employeeSurnameInput, (String) employeeDataDetails.get("employee-surname"), "Employee Surname input", "Data not as expected");
          	VerifyText(employeePositionInput, (String) employeeDataDetails.get("employee-position"), "Employee Position input", "Data not as expected");
          	VerifyText(employeeDateStartedInput, (String) employeeDataDetails.get("employee-date-started"), "Employee Date Started input", "Data not as expected");
          	VerifyElementsDisabledOnViewEmployee();
        	//Click Cancel button
        	ClickCancelCreateEmployee();
        }
    }

    //verify employee's data on Update page using data from YAML file
    //verify data on View page are enabled
    public void VerifyUpdateEmployee(Map employeeDataFile){
    	listOfEmployees = new ListOfEmployees(driver);
    	ArrayList employeesData;
        employeesData = (ArrayList) employeeDataFile.get("employees");
        
        for (Object employeeData : employeesData) {
        	
        	Map<String, String> employeeDataDetails = (Map<String, String>) employeeData;
        	//click View button  
        	listOfEmployees.ClickUpdateEmployeeButton("(String) employeeDataDetails.get(\"employee-first-name\")"
													+" "+"SetSurname((String) employeeDataDetails.get(\"employee-surname\"))");        	
        	//verify data on View employee page equals data in YAML file
        	VerifyText(employeeNameInput, (String) employeeDataDetails.get("employee-first-name"), "Employee Name input", "Data not as expected");
        	VerifyText(employeeSurnameInput, (String) employeeDataDetails.get("employee-surname"), "Employee Surname input", "Data not as expected");
          	VerifyText(employeePositionInput, (String) employeeDataDetails.get("employee-position"), "Employee Position input", "Data not as expected");
          	VerifyText(employeeDateStartedInput, (String) employeeDataDetails.get("employee-date-started"), "Employee Date Started input", "Data not as expected");
          	VerifyElementsEnabledOnUpdateEmployee();
          	
        	//Click Cancel button
        	ClickCancelCreateEmployee();
        }
    }

    //verify if all elements are enabled on Update Employee page
    public void VerifyElementsEnabledOnUpdateEmployee(){
    	VerifyElementEnabled(employeeNameInput, "Employee Name input", "Element is disabled but expected enabled");
    	VerifyElementEnabled(employeeNameInput, "Employee Surname input", "Element is disabled but expected enabled");
      	VerifyElementEnabled(employeeNameInput, "Employee Position input", "Element is disabled but expected enabled");
      	VerifyElementEnabled(employeeNameInput, "Employee Date Started input", "Element is disabled but expected enabled");    	
    }

  //verify if all elements are disabled on View Employee page
    public void VerifyElementsDisabledOnViewEmployee(){
    	VerifyElementNotEnabled(employeeNameInput, "Employee Name input", "Element is enabled but expected disabled");
    	VerifyElementNotEnabled(employeeNameInput, "Employee Surname input", "Element is enabled but expected disabled");
    	VerifyElementNotEnabled(employeeNameInput, "Employee Position input", "Element is enabled but expected disabled");
    	VerifyElementNotEnabled(employeeNameInput, "Employee Date Started input", "Element is enabled but expected disabled");    	
    }

    
    //verify remove employee's data  using data from YAML file
    public void VerifyRemoveEmployee(Map employeeDataFile){
    	listOfEmployees = new ListOfEmployees(driver);
    	ArrayList employeesData;
        employeesData = (ArrayList) employeeDataFile.get("employees");
        
        for (Object employeeData : employeesData) {
        	
        	Map<String, String> employeeDataDetails = (Map<String, String>) employeeData;
        	//click Remove button  
        	listOfEmployees.ClickRemoveEmployeeButton("(String) employeeDataDetails.get(\"employee-first-name\")"
													+" "+"SetSurname((String) employeeDataDetails.get(\"employee-surname\"))");        	
        	listOfEmployees.VerifyEmployeeNotOnList("(String) employeeDataDetails.get(\"employee-first-name\")"
					+" "+"SetSurname((String) employeeDataDetails.get(\"employee-surname\"))");         	
        }
    }
   

}