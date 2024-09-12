package api.helper;

import net.serenitybdd.core.di.SerenityInfrastructure;
import net.thucydides.model.util.EnvironmentVariables;

public class EnvironmentVariableReader {
    public static String serenityProperty(String propertyName) {
        EnvironmentVariables environmentVariables = SerenityInfrastructure.getEnvironmentVariables();
        return environmentVariables.getProperty(propertyName);
    }
}
