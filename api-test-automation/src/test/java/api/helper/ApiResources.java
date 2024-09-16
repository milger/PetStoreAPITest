package api.helper;

public class ApiResources {
    // Properties paths
    public static final String REST_API_BASE_URL_PROPERTY = "restapi.baseurl";

    private static final String PET_CREATE_PROPERTY = "restapi.pet.create";
    private static final String PET_FIND_BY_ID_PROPERTY = "restapi.pet.findById";
    private static final String PET_FIND_BY_STATUS_PROPERTY = "restapi.pet.findByStatus";
    private static final String PET_FIND_BY_TAGS_PROPERTY = "restapi.pet.findByTags";
    private static final String PET_DELETE_PROPERTY = "restapi.pet.delete";
    private static final String PET_UPDATE_PROPERTY = "restapi.pet.update";
    private static final String PET_UPDATE_WITH_FORM_PROPERTY = "restapi.pet.updateWithForm";
    private static final String PET_UPLOAD_IMAGE_PROPERTY = "restapi.pet.uploadImage";

    // Pet endpoints
    public static final String PET_CREATE_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_CREATE_PROPERTY);
    public static final String PET_FIND_BY_ID_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_FIND_BY_ID_PROPERTY);
    public static final String PET_FIND_BY_STATUS_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_FIND_BY_STATUS_PROPERTY);
    public static final String PET_FIND_BY_TAGS_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_FIND_BY_TAGS_PROPERTY);
    public static final String PET_DELETE_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_DELETE_PROPERTY);
    public static final String PET_UPDATE_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_UPDATE_PROPERTY);
    public static final String PET_UPDATE_WITH_FORM_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_UPDATE_WITH_FORM_PROPERTY);
    public static final String PET_UPLOAD_IMAGE_ENDPOINT = EnvironmentVariableReader.getSerenityProperty(PET_UPLOAD_IMAGE_PROPERTY);

    // Path params
    public static final String PET_ID_PATH_PARAM = "petId";
}
