package org.example.actiontest.pagemodels;

/**
 * TO get properties key/value While reading value First preference will be
 * System property if set before run.
 */
public enum ApplicationProperties {

    /**
     * Browser Key used to instantiate the selenium browser.
     */
    BROWSER("browser"),
    /**
     * Browser Key used to instantiate the selenium browser.
     */
    JENKINS_BUILD_NUMBER("JENKINS_BUILD_NUMBER"),
    /**
     * Browser Key used to instantiate a headless chrome browser.
     */
    HEADLESS("headless"),
    /**
     * Test Environment Key.
     */
    TEST_ENVIRONMENT("environment"),
    /**
     * base URL of AUT to be used.
     */
    APPLICATION_BASE_URL("baseurl"),
    /**
     * dir to place generated result files.
     */
    REPORT_DIR("test.results.dir"),
    /**
     * dir to place generated result files.
     */
    REPORT_LOG_FILE_NAME("test.log.name"),
    /**
     * directory for reporting results.
     */
    TESTDATA_DIR("test.data.dir"),
    /**
     * remote workspace dir.
     */
    REMOTE_WORKSPACE_DIR("remote.workspace.dir"),
    /**
     * dir to place screen-shots.
     */
    SCREENSHOT_DIR("selenium.screenshots.dir"),
    /**
     * Enables or disables email results.
     */
    EMAIL_EXECUTION_REPORT("test.results.emailExceutionReport"),
    /**
     * Email Report Directory.
     */
    EMAIL_EXECUTION_REPORT_DIR("test.results.report.html.dir"),
    EMAIL_EXECUTION_OPTIONAL_REPORT_DIR("test.results.report.optional.report.html.dir"),
    /**
     * The default locale
     */
    DEFAULT_LOCALE("env.default.locale"),
    /**
     * The local character encoding.
     */
    LOCALE_CHAR_ENCODING("locale.char.encoding"),
    /**
     * integer to specify how many times a test action/step should be retried on
     * failure by default.
     */
    DEFAULT_RETRY_ACTIONS_CNT("retry.action.count"),
    /**
     * integer to specify how many times test should be retried on.
     */
    RETRY_CNT("retry.count"),
    /**
     * Saucelabs username.
     */
    SAUCE_USERNAME("SAUCE_USERNAME"),
    /**
     * Saucelabs key.
     */
    SAUCE_ACCESS_KEY("SAUCE_ACCESS_KEY"),
    /**
     * SauceLabs Tunnel identifier
     */
    TUNNEL_IDENTIFIER("TUNNEL_IDENTIFIER"),
    /**
     * Default timeout in seconds.
     */
    DEFAULT_TIMEOUT("DefaultTimeout"),
    /**
     * Default timeout in seconds.
     */
    WAIT_POLL_TIMEOUT("WaitPollTime"),
    /**
     * Set to true to use a grid instance.
     */
    REMOTE("remote"),
    /**
     * The remote host type
     */
    REMOTE_HOST("remote.host"),
    /**
     * The Selenium Grid host for the driver to connect to.
     */
    SELENIUM_HOST("seleniumHost"),
    /**
     * The Selenium Grid port for the driver to connect to.
     */
    SELENIUM_PORT("seleniumPort"),
    /**
     * The platform of the OS.
     */
    PLATFORM("platform"),
    /**
     * The version of the browser
     */
    VERSION("version"),
    /**
     * The width of the browser.
     */
    BROWSER_WIDTH("browser.width"),
    /**
     * The heigth of the browser.
     */
    BROWSER_HEIGHT("browser.height"),
    /**
     * The web service uri.
     */
    WEB_SERVICE_URI("web.service.uri"),
    /**
     * The web service base port.
     */
    WEB_SERVICE_PORT("web.service.port"),
    /**
     * The web service base path.
     */
    WEB_SERVICE_PATH("web.service.path"),
    /**
     * The web service timeout.
     */
    WEB_SERVICE_TIMEOUT("web.service.timeout"),
    /**
     * Determines if system should log web service tests.
     */
    WEB_SERVICE_LOG("web.service.log"),
    /**
     * Determines if system should log web service only if test fail.
     */
    WEB_SERVICE_LOG_ON_FAIL_ONLY("web.service.log.on.fail.only"),
    /**
     * Database connection url.
     */
    DB_URL("db_url"),
    /**
     * Database username.
     */
    DB_USERNAME("db_username"),
    /**
     * Database password.
     */
    DB_PASSWORD("db_password"),
    /**
     * Database provider driver
     */
    DB_PROVIDER("db_provider"),
    /**
     * Database host
     */
    DB_HOST("db_host"),
    /**
     * Database port
     */
    DB_PORT("db_port"),

    /**
     * Splunk report info
     */
    APPLICATION("application"),
    TEAM("team"),
    DEPARTMENT("department"),

    /**
     * Saucelabs prerun script details
     */
    PRERUN_EXECUTABLE("PRERUN_EXECUTABLE"),
    PRERUN_ARGS("PRERUN_ARGS"),
    PRERUN_USERNAME("PRERUN_USERNAME"),
    PRERUN_PASSWORD("PRERUN_PASSWORD"),
    PRERUN_BACKGROUND("PRERUN_BACKGROUND"),
    PRERUN_TIMEOUT("PRERUN_TIMEOUT"),

    /**
     * Jira Required Fields
     */
    JIRA_URL("jira_url"),
    JIRA_USERNAME("jira_username"),
    JIRA_PASSWORD("jira_password"),
    JIRA_VERSION("jira_version"),
    JIRA_EXISTINGCYCLENAME("jira_existing_cycle_name"),

    /**
     * Jira Optional Fields
     */
    UPLOAD_RESULTS_TO_JIRA("upload_results_to_Jira"),
    JIRA_CYCLEID("jira_update_cycle_id"),
    JIRA_CYCLEDESCRIPTION("jira_cycle_description"),
    JIRA_STARTDATE("jira_startdate"),
    JIRA_ENDDATE("jira_enddate"),
    JIRA_CLONECYCLEID("jira_clone_cycle_id"),

    /**
     * Jira folder optional fields
     */

    JIRA_USE_FOLDER("jira_use_folder"),
    JIRA_FOLDER_MAKE("jira_folder_make"),
    JIRA_FOLDER_NAME("jira_folder_name"),


    /**
     * trust store.
     */
    TRUST_STORE("trustStore");

    /**
     * Key of this enum.
     */
    private String key;

    /**
     * Constructor for enum class.
     *
     * @param appKey The key to set.
     */
    ApplicationProperties(String appKey) {
        this.key = appKey;
    }

    /**
     * Gets the Key.
     *
     * @return returns the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return string value of the key
     */
    public String getStringVal() {

        return ConfigurationManager.getKeyStringVal(key, "");
    }

    /**
     * @param defaultVal optional
     * @return string value of the key
     */
    public String getStringVal(String defaultVal) {

        return ConfigurationManager.getKeyStringVal(key, defaultVal);
    }

    /**
     * @return integer value of the key or 0 if key is not an integer
     */
    public int getIntVal() {
        return ConfigurationManager.getKeyIntVal(key, 0);
    }

    /**
     * @param defaultVal optional
     * @return integer value of the key or 0 if key is not an integer
     */
    public int getIntVal(int defaultVal) {
        return ConfigurationManager.getKeyIntVal(key, defaultVal);
    }

    /**
     * @return boolean value of the key
     */
    public boolean getBooleanVal() {
        return ConfigurationManager.getKeyBooleanVal(key, false);
    }

    /**
     * @param defaultVal optional
     * @return boolean value of the key
     */
    public boolean getBooleanVal(boolean defaultVal) {
        return ConfigurationManager.getKeyBooleanVal(key, defaultVal);
    }

    /**
     * Returns the object key.
     *
     * @param defaultVal optional default value.
     * @return the object of the key.
     */
    public Object getObject(Object... defaultVal) {
        Object objToReturn = ConfigurationManager.getBundle().getObject(key);

        if (objToReturn != null) {
            return objToReturn;
        }
        return (null != defaultVal) && (defaultVal.length > 0)
                ? defaultVal[0] : null;
    }
}
