package api.stepdefinitions;

import api.helper.FileUtils;
import api.questions.PetResponse;
import api.tasks.UploadPetImage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import java.io.File;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayWithSize;

public class UploadImageStepDefinitions {

    @When("{actor} uploads the photo {string} to pet with id {string}")
    public void heUploadsThePhotoToPetWithId(Actor actor, String fileName, String petId) {
        File imageFile = FileUtils.getDataFile(fileName);

        actor.attemptsTo(
                UploadPetImage.with(petId, imageFile)
        );
    }

    @And("{actor} should get a pet with {int} photos in the response body")
    public void heShouldGetAPetWithPhotosInTheResponseBody(Actor actor, int expectedPhotosLength) {
        actor.should(
                seeThat("Photos URLs", PetResponse.photosUrls(), is(arrayWithSize(expectedPhotosLength)))
        );
    }
}
