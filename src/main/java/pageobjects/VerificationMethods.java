package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import testreporter.TestReporter;

public class VerificationMethods {

	int delay = 10000;

    public WebDriver driver;

    public VerificationMethods(WebDriver driver) {
        this.driver = driver;
    }

    //-------------------Actions------------------------------------------------------
       
    //wait for element to be enabled 
    protected void WaitForElementToBeEnabled(WebElement element, String elementName) {
      Boolean isVisibleAndEnabled = false;
      long startTime = System.currentTimeMillis();
      
      while(isVisibleAndEnabled.equals(false))
          {
    	  	  try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
              Long elapsed = System.currentTimeMillis()-startTime;
              if (elapsed > delay){
                  TestReporter.logTestStep("Element '" + elementName + "' could not find element after " + elapsed/1000 + " secs");
                  return;
              }

              try {
              	  element.isEnabled();
                  isVisibleAndEnabled = true;
              } catch (Exception err){
                  System.out.print("   Error: " + err.getMessage());
                  isVisibleAndEnabled = false;
              }
          }
      }

    //wait for element to be enabled and then click it   
    protected void waitThenClick(WebElement element, String elementName){
        TestReporter.logTestStep("Trying to click '"   + elementName + "'");
        WaitForElementToBeEnabled(element, elementName);
        element.click();
        TestReporter.logTestStep("Element '"   + elementName + "' clicked");
    }

    // click element   
    protected void click(WebElement element, String elementName){
        TestReporter.logTestStep("Trying to click '"   + elementName + "'");
        element.click();
        TestReporter.logTestStep("Element '"   + elementName + "' clicked");
    }

    //wait for element to be enabled and then send text
    protected void waitThenType(WebElement element, String text, String elementName){
        TestReporter.logTestStep("Entering text '" + text +"' to element '"+elementName+"'");
        WaitForElementToBeEnabled(element, elementName);

        element.clear();
        element.sendKeys(text);
    }
    
    //send text to element but not include the text in report
    protected void typeHidden(WebElement element, String text, String elementName){
        TestReporter.logTestStep("Entering text '*********' to element '"+elementName+"'");

        element.click();
        element.clear();
        element.sendKeys(text);
    }



    
    //--------------------Verification methods------------------------------------------------
   
    //Verify text of element
    protected void VerifyText(WebElement element, String expectedText, String elementName, String errorMessage) {
        TestReporter.logTestStep(elementName+" expected text is: '" + expectedText + "' but found: '" + element.getText() +"'");
        Assert.assertEquals(element.getText(), expectedText, errorMessage);
    }
    
    //Verify element is enabled
    protected void VerifyElementEnabled(WebElement element, String elementName, String errorMessage){
    	TestReporter.logTestStep("Verifying element "+elementName+" is enabled");
    	Assert.assertTrue(element.isEnabled() , errorMessage);
    }

    //Verify element is not enabled
    protected void VerifyElementNotEnabled(WebElement element, String elementName, String errorMessage){
    	TestReporter.logTestStep("Verifying element "+elementName+" is not enabled");
    	Assert.assertFalse(element.isEnabled() , errorMessage);
    }
   
    //List - verify list is not empty
    protected void VerifyListNotEmpty(List<WebElement> element, String errorMessage){
    	TestReporter.logTestStep("Verifying list is not empty");
    	Assert.assertTrue(!element.isEmpty(), errorMessage);
    }
    
    //List - verify list is empty
    protected void VerifyListEmpty(List<WebElement> element, String errorMessage){
    	TestReporter.logTestStep("Verifying list is not empty");
    	Assert.assertTrue(element.isEmpty(), errorMessage);
    }
    
    //List - verify list size equals an expected number
    protected void VerifyListSize(List<WebElement> element, Integer expectedSize, String errorMessage){
    	TestReporter.logTestStep("Verifying list size  is "+ expectedSize );
    	Assert.assertEquals((Integer)element.size(), expectedSize , errorMessage);
    }

    //List - verify list contains element with a particular text
    protected void VerifyElementOnListByText(List<WebElement> list, String text, String errorMessage){
    	TestReporter.logTestStep("Verifying element '"+text+"' exists on list");
    	List <String> textOfElementsOnList = new ArrayList<String>();
    	for(WebElement el : list){
    		textOfElementsOnList.add(el.getText());
    	}
    	Assert.assertTrue(textOfElementsOnList.contains(text), errorMessage);
    }
 
    //List - verify list contains element with a particular text - not case sensitive
    protected void VerifyElementOnListByTextNotCaseSensitive(List<WebElement> list, String text, String errorMessage){
    	TestReporter.logTestStep("Verifying element '"+text+"' exists on list");
    	List <String> textOfElementsOnList = new ArrayList<String>();
    	for(WebElement el : list){
    		textOfElementsOnList.add(el.getText().toLowerCase());
    	}
    	Assert.assertTrue(textOfElementsOnList.contains(text.toLowerCase()), errorMessage);
    }
   
    //List - verify list doesn't contain element with a particular text
    protected void VerifyElementNotOnListByText(List<WebElement> list, String text, String errorMessage){
    	TestReporter.logTestStep("Verifying element '"+text+"' doesnot exist on list");
    	List <String> textOfElementsOnList = new ArrayList<String>();
    	for(WebElement el : list){
    		textOfElementsOnList.add(el.getText());
    	}
    	Assert.assertTrue(!textOfElementsOnList.contains(text), errorMessage);
    }

    //List - verify list doesn't contain element with a particular text - not case sensitive
    protected void VerifyElementNotOnListByTextNotCaseSensitive(List<WebElement> list, String text, String errorMessage){
    	TestReporter.logTestStep("Verifying element '"+text+"' doesnot exist on list");
    	List <String> textOfElementsOnList = new ArrayList<String>();
    	for(WebElement el : list){
    		textOfElementsOnList.add(el.getText().toLowerCase());
    	}
    	Assert.assertTrue(!textOfElementsOnList.contains(text), errorMessage);
    }


}
