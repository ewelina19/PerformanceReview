package testconfiguration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import testreporter.TestReporter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestDriver {

    private WebDriver driver;
    public String URL, Node;


    public WebDriver Initialise(String browser, String URL) {

        System.out.println("DRIVER - Initialising Locally");
        String browserDriver = "";

        // Get the browser under test and set the appropriate driver
        String browserUnderTest = browser;
        if (URL == null) {
            URL = TestRunSettings.GetTestSiteURL();
            System.out.println("INFO: URL value 'null'. Using testconfig value of: " + browser.toUpperCase());
        }

        if ("firefox".equals(browserUnderTest)) {
        	browserDriver = "geckodriver";
        	if(System.getProperty("os.name").toLowerCase().contains("windows")){
        		browserDriver.concat(".exe");
        	}
            System.setProperty("webdriver.firefox.marionette", GetOSPath() +"geckodriver");
            driver = new FirefoxDriver();
            driver.manage().window().setPosition(new Point(2000,1));
            driver.manage().window().maximize();
        }
        else if ("chrome".equals(browserUnderTest)) {
        	browserDriver = "chromedriver";
        	if(System.getProperty("os.name").toLowerCase().contains("windows")){
        		browserDriver.concat(".exe");
        	}
            System.setProperty("webdriver.chrome.driver", GetOSPath() + browserDriver);
            driver = new ChromeDriver();

            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().window().maximize();
        }

        else if ("internet explorer".equals(browserUnderTest)) {
            System.setProperty("webdriver.ie.driver", GetOSPath() + "IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        else if ("edge".equals(browserUnderTest)) {
            System.setProperty("webdriver.edge.driver", GetOSPath() + "MicrosoftWebDriver.exe");
            driver = new EdgeDriver();
        }
        String OSName = System.getProperty("os.name");

        // configure browser settings for test execution
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        TestReporter.logTestStep("Starting Browser, navigating to " + URL);
        driver.get(URL);
        return driver;
    }

    private String GetOSPath(){
        String currentPath = new File(".").getAbsolutePath();
        String OSName = System.getProperty("os.name");

        String driverDIR = "NO_OS_PATH_SET";
        if (OSName.toLowerCase().contains("linux")) {
            driverDIR = "\\src\\drivers\\linux\\";
        }
        else if (OSName.toLowerCase().contains("mac")) {
            driverDIR = "/src/drivers/mac/";
        }
        else if (OSName.toLowerCase().contains("windows")) {
            driverDIR = "\\src\\drivers\\win\\";
        }

        //return the driver path of the relevant OS
        return currentPath.substring(0,currentPath.length()-1) + driverDIR;
    }

}


