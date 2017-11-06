package pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class Common extends VerificationMethods {

    public Common(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // ======================================== WEB PAGE ELEMENTS ========================================
    
    // LOGIN PAGE
    @FindBy(how = How.ID, using = "username")
    private WebElement usernameInput;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordInput;

    @FindBy(how = How.ID, using = "submit")
    private WebElement loginButton;
    
    @FindBy(how = How.ID, using = "logout")
    private WebElement logoutButton;



 // ======================================== ACTION ======================================================
    public void login(String userName, String password){
        waitThenType(usernameInput, userName, "Username input");
        typeHidden(passwordInput, password, "Password input");
        click(loginButton, "Submit Login button");
    }

}
