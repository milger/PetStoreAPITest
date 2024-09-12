package api.helper;

public class ApiResources {
    // Properties paths
    public static final String REST_API_BASE_URL_PROPERTY = "restapi.baseurl";

    public static final String PET_CREATE_PROPERTY = "restapi.pet.create";
    public static final String PET_FIND_BY_ID_PROPERTY = "restapi.pet.findById";
    public static final String PET_DELETE_PROPERTY = "restapi.pet.delete";

    // Pet endpoints
    public static final String PET_CREATE_ENDPOINT = EnvironmentVariableReader.serenityProperty(PET_CREATE_PROPERTY);
    public static final String PET_FIND_BY_ID_ENDPOINT = EnvironmentVariableReader.serenityProperty(PET_FIND_BY_ID_PROPERTY);
    public static final String PET_DELETE_ENDPOINT = EnvironmentVariableReader.serenityProperty(PET_DELETE_PROPERTY);

    // Path params
    public static final String PET_ID_PATH_PARAM = "petId";
}
