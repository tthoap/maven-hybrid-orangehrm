package core;

public class GlobalConstants {
    //System info
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String SEPARATOR = System.getProperty("file.separator");

    //JIRA info
    public static final String JIRA_SITE_URL = "https://orange-hrm-auto.atlassian.net/";
    public static final String JIRA_USERNAME = "tthoaptit@gmail.com";
    public static final String JIRA_PROJECT_KEY= "SCRUM";
    public static final String JIRA_API_KEY = "ATATT3xFfGF0OP5m0tU4Egz-R6Tlat3uQzZ92DlHB5YPG1tJUq8iarI2QBRAWMLQSEANxIwpdDrJWkU_wefdVv2p8uxyM8lbc1N_zSs9ttMpCDB9XbjUUSB9UobQhWLr2amuYEn7YBjdk9jBXzv27A_b6sroOOhCni-AXlXWx3mEQtIA_p6HoI4=B604C314";


    // Wait info
    public static final int SHORT_TIMEOUT = 5;
    public static final int LONG_TIMEOUT = 10;

    // Upload / download file
    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + SEPARATOR + "downloadFiles" + SEPARATOR;

    //HTML Report folder
    public static final String REPORTING_PATH = PROJECT_PATH + SEPARATOR + "htmlReportNG" + SEPARATOR;
    public static final String EXTENT_PATH = PROJECT_PATH + SEPARATOR + "htmlExtent" + SEPARATOR;
    public static final String ALLURE_PATH = PROJECT_PATH + SEPARATOR + "htmlAllure" + SEPARATOR;

}
