package cloud.benchflow.testmanager.constants;

import cloud.benchflow.testmanager.models.User;

/**
 * @author Jesper Findahl (jesper.findahl@usi.ch)
 *         created on 16.02.17.
 */
public class BenchFlowConstants {

    // TODO - this is necessary for current version of Test Manager
    private static final String YAML_EXTENSION = ".yml";

    // REST API
    public static final String VERSION_1_PATH = "/v1";
    public static final String USERS_PATH = "/users/";
    public static final String TESTS_PATH = "/tests/";
    public static final String EXPERIMENTS_PATH = "/experiments/";
    public static final String TRIALS_PATH = "/trials/";

    // Archive
    public static final String DEPLOYMENT_DESCRIPTOR_NAME = "docker-compose";
    public static final String TEST_EXPERIMENT_DEFINITION_NAME = "benchflow-test";
    public static final String BPMN_MODELS_FOLDER_NAME = "models";
    public static final String MINIO_ID_DELIMITER = "/";

    // Minio
    public static final String TESTS_BUCKET = "tests";
    public static final String TEST_EXPERIMENT_DEFINITION_FILE_NAME = TEST_EXPERIMENT_DEFINITION_NAME + YAML_EXTENSION;
    public static final String DEPLOYMENT_DESCRIPTOR_FILE_NAME = DEPLOYMENT_DESCRIPTOR_NAME + YAML_EXTENSION;

    public static final String GENERATED_BENCHMARK_FILE_NAME = "benchflow-benchmark.jar";

    // MongoDB
    public static final String DB_NAME = "benchflow-test-manager";
    public static final String MODEL_ID_DELIMITER = ".";
    public static final User BENCHFLOW_USER = new User("benchflow");

    public static final String MODEL_ID_DELIMITER_REGEX = "\\.";

    public static String getTestID(String username, String testName, int testNumber) {
        return username + MODEL_ID_DELIMITER + testName + MODEL_ID_DELIMITER + testNumber;
    }

    public static String getExperimentID(String username, String testName, int testNumber, int experimentNumber) {
        return getTestID(username, testName, testNumber) + MODEL_ID_DELIMITER + experimentNumber;
    }

    public static String getTrialID(String username, String testName, int testNumber, int experimentNumber, int trialNumber) {
        return getExperimentID(username, testName, testNumber, experimentNumber) + MODEL_ID_DELIMITER + trialNumber;
    }

    public static String getPathFromUsername(String username) {

        return VERSION_1_PATH
                + USERS_PATH + username;

    }

    public static String getPathFromTestID(String testID) {

        String[] testIDArray = testID.split(MODEL_ID_DELIMITER_REGEX);
        String username = testIDArray[0];
        String testName = testIDArray[1];
        String testNumber = testIDArray[2];

        return VERSION_1_PATH
                + USERS_PATH + username
                + TESTS_PATH + testName + "/" + testNumber;

    }

    public static String getPathFromExperimentID(String experimentID) {

        String[] experimentIDArray = experimentID.split(MODEL_ID_DELIMITER_REGEX);
        String username = experimentIDArray[0];
        String testName = experimentIDArray[1];
        String testNumber = experimentIDArray[2];
        String experimentNumber = experimentIDArray[3];

        return VERSION_1_PATH
                + USERS_PATH + username
                + TESTS_PATH + testName + "/" + testNumber
                + EXPERIMENTS_PATH + experimentNumber;

    }

    public static String getPathFromTrialID(String trialID) {

        String[] trialIDArray = trialID.split(MODEL_ID_DELIMITER_REGEX);
        String username = trialIDArray[0];
        String testName = trialIDArray[1];
        String testNumber = trialIDArray[2];
        String experimentNumber = trialIDArray[3];
        String trialNumber = trialIDArray[4];

        return VERSION_1_PATH
                + USERS_PATH + username
                + TESTS_PATH + testName + "/" + testNumber
                + EXPERIMENTS_PATH + experimentNumber
                + TRIALS_PATH + trialNumber;

    }
}
