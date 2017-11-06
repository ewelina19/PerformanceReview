package testreporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;
import sun.rmi.runtime.Log;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extent = new ExtentReports(outputDirectory + File.separator + "TestReport.html", true);
        extent.loadConfig(new File("src/main/java/testreporter/extent-config.xml"));


        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
            }
        }

        extent.close();
    }

    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {

                test = extent.startTest(result.getMethod().getMethodName());

                String currentName = test.getTest().getName();
                test.getTest().setName(currentName);
                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));

                // Add the test Category
                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                // Log Test Result details
                String testResults = "Test " + status.toString().toLowerCase() + "ed ";
                String testResultMessage ="";
                if (result.getThrowable() != null)
                    testResultMessage = result.getThrowable().getMessage();
                test.log(status, testResults + testResultMessage);

                // Add the content for each step/action
                String[] stepOutput = result.getAttribute("steplog").toString().split("TESTSTEP");
                for (String stepDetail : stepOutput) {
                    if (stepDetail.contains("DIFF=true")){
                        String capturePath = stepDetail.split("PATH=")[1].replace("<br>", "").replace("test-output/", "");
                        test.log(LogStatus.FAIL, stepDetail
                                + test.addScreenCapture(capturePath + "_baseline.png")
                                + test.addScreenCapture(capturePath + "_capture.png")
                                + test.addScreenCapture(capturePath + "_diff.png"));
                    } else if (stepDetail.contains("DIFF=false")){
                        String capturePath = stepDetail.split("PATH=")[1].replace("<br>", "").replace("test-output/", "");
                        test.log(LogStatus.PASS, stepDetail
                                + test.addScreenCapture(capturePath + "_baseline.png")
                                + test.addScreenCapture(capturePath + "_capture.png")
                                + test.addScreenCapture(capturePath + "_diff.png"));
                    } else {
                        test.log(LogStatus.INFO, stepDetail);
                    }
                }

                extent.endTest(test);
            }
        }
    }


//    private void buildTestNodes(IResultMap tests, LogStatus status) {
//        ExtentTest test;
//
//        if (tests.size() > 0) {
//            for (ITestResult result : tests.getAllResults()) {
//
//                test = extent.startTest(result.getMethod().getMethodName());
//
//                String currentName = test.getTest().getName();
//                test.getTest().setName(currentName);
//                test.getTest().setStartedTime(getTime(result.getStartMillis()));
//                test.getTest().setEndedTime(getTime(result.getEndMillis()));
//
//                for (String group : result.getMethod().getGroups())
//                    test.assignCategory(group);
//                    String message = "Test " + status.toString().toLowerCase() + "ed";
//
//                    message = message + "<br>" + result.getAttribute("steplog");
//
//                if (result.getThrowable() != null)
//                    message = message + result.getThrowable().getMessage();
//
//                // Log Steps details
//                test.log(status, message);
//
//                // Add any images if captured
//                if (result.getAttribute("imagepath").toString() != "null"){
//                    String path = result.getAttribute("imagepath").toString();
//
//                    test.log(LogStatus.INFO, "Visual Verifications: "
//                            + test.addScreenCapture(path + "_baseline.png")
//                            + test.addScreenCapture(path + "_capture.png")
//                            + test.addScreenCapture(path + "_diff.png"));
//                }
//
//                extent.endTest(test);
//            }
//        }
//    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}