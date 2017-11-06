package testconfiguration;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class TestRunSettings {

    // GENERIC METHOD - gets the property from testconfig.properties file
    public static String GetProperty(String propertyName) {
        Properties testsettings = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("testconfig.properties");

            // load a properties file
            testsettings.load(input);

            // get the property value and print it out
            return testsettings.getProperty(propertyName);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "TESTRUNPROPERTY '" + propertyName + "' DOES NOT EXIST";
    }


    // USER INFO
    public static String GetValidUsername(){
        return GetProperty("Username");
    }

    public static String GetValidPassword() {
    	return GetProperty("Password"); 
    }

    // TEST ENV
    public static String GetTestSiteURL(){
        return GetProperty("baseURL");
    }
    public static String GetBrowserUnderTest(){
        return GetProperty("TestBrowser");
    }


    // DATA FILES - Yaml Files
    public static Map GetRunData(String TEST_DATA_FILE){
        YamlReader datareader = null;
        try {
            datareader = new YamlReader(new FileReader("src//data//" + TEST_DATA_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Object dataObj = null;
        try {
            dataObj = datareader.read();
        } catch (YamlException e) {
            e.printStackTrace();
        }
        Map map = (Map)dataObj;

        return map;
    }
    public static Map GetNestedData(Map testData, String parentKey){
        // Data Sources
        ArrayList tagComponents = new ArrayList();
        tagComponents = (ArrayList) testData.get(parentKey);

        Map<String, Object> tagComponentData = (Map<String, Object>) tagComponents.get(1);
        //System.out.println(tagComponentData.get("datasources"));

        return tagComponentData;
    }
}
