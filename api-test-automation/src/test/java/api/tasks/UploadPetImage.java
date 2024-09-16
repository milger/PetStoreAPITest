package api.tasks;

import api.helper.ApiResources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static api.helper.ApiResources.PET_ID_PATH_PARAM;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UploadPetImage implements Task {
    private String petId;
    private File file;

    public UploadPetImage(String id, File file){
        this.petId = id;
        this.file = file;
    }

    public static UploadPetImage with(String id, File file){
        return instrumented(UploadPetImage.class, id, file);
    }

    @Override
    @Step("{0} uploads an image to pet: #petId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiResources.PET_UPLOAD_IMAGE_ENDPOINT)
                        .with(request -> {
                            try {
                                return request.contentType(ContentType.BINARY)
                                        .config(RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                                        .body(Files.readAllBytes(Paths.get(file.toURI())))
                                        .pathParam(PET_ID_PATH_PARAM, petId);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
        );
    }
}
