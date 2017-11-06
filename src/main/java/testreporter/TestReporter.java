package testreporter;

import org.testng.Reporter;


public class TestReporter {

    public static void logTestStep(String stepDetails){
        System.out.println(stepDetails);

        if (Reporter.getCurrentTestResult().getAttribute("steplog") != null) {
            String currentLog = Reporter.getCurrentTestResult().getAttribute("steplog").toString();

            Reporter.getCurrentTestResult().setAttribute("steplog", currentLog + "<br>TESTSTEP" +  stepDetails);
        } else {
            Reporter.getCurrentTestResult().setAttribute("steplog", stepDetails);
        }
    }

    public static void logTestStep(String stepDetails, String imagePath, Boolean bResult){
        System.out.println(stepDetails);

        if (Reporter.getCurrentTestResult().getAttribute("steplog") != null) {
            // Get the current steps log
            String currentLog = Reporter.getCurrentTestResult().getAttribute("steplog").toString();

            // Add new step info to steplog attribute (log with existing content)
            Reporter.getCurrentTestResult().setAttribute("steplog", currentLog
                    + "<br>TESTSTEP" +  stepDetails
                    + "<br>DIFF=" +  bResult.toString()
                    + "<br>PATH=" +  imagePath);
        } else {
            // Add new step info to steplog attribute (empty log)
            Reporter.getCurrentTestResult().setAttribute("steplog","<br>TESTSTEP" +  stepDetails
                    + "<br>DIFF=" +  bResult.toString()
                    + "<br>PATH=" +  imagePath);
        }

        Reporter.getCurrentTestResult().setAttribute("imagepath", imagePath);
    }



    public static String getTestSteps(){
        String currentLog = Reporter.getCurrentTestResult().getAttribute("steplog").toString();
        return currentLog;
    }

}
