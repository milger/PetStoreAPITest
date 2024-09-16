package api.helper;

import java.io.File;

public class FileUtils {
    public static File getDataFile(String fileName) {
       String dataBasePath = EnvironmentVariableReader.getSerenityProperty("data.path");
       File testDataFile = new File(dataBasePath + fileName);
       return new File(testDataFile.getAbsolutePath());
    }
}
