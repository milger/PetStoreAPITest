package api.stepdefinitions;

import api.helper.Utils;
import api.model.Pet;
import api.questions.PetResponse;
import api.tasks.UpdateExistingPet;
import api.tasks.UpdatePetWithFormData;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.helper.RememberConstants.PET_REQUEST_BODY;
import static api.helper.RememberConstants.UPDATED_PET;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UpdatePetStepDefinitions {

    @When("{actor} updates the pet with following information:")
    public void heUpdatesThePetWithFollowingInformation(Actor actor, List<Pet> petsList) {
        Pet petToUpdate = petsList.getFirst();
        String petJson = Utils.objectToJsonString(petToUpdate);

        actor.attemptsTo(
                UpdateExistingPet.with(petJson)
        );

        actor.remember(UPDATED_PET, petToUpdate);
    }

    @And("{actor} should get the updated pet information in the response body")
    public void heShouldGetTheUpdatedPetInformationInTheResponse(Actor actor) {
        Pet expectedPet = actor.recall(UPDATED_PET);

        actor.should(
                seeThat("Pet response body", PetResponse.petObject(), is(equalTo(expectedPet)))
        );
    }

    @And("{actor} updates the pet using the modified information")
    @And("{actor} updates the pet with missing information")
    public void heUpdatesThePetUsingTheModifiedInformation(Actor actor) {
        ObjectNode jsonBody = actor.recall(PET_REQUEST_BODY);

        actor.attemptsTo(
                UpdateExistingPet.with(jsonBody.toString())
        );
    }

    @When("{actor} updates the pet {int} with following form data:")
    public void heUpdatesThePetWithFollowingFormData(Actor actor, int petId, DataTable table) {
        Map<String, String> formData = table.asMap();

        actor.attemptsTo(
                UpdatePetWithFormData.update(petId, formData)
        );
    }

    @And("{actor} should get the following updated pet information:")
    public void heShouldGetTheFollowingUpdatedPetInformation(Actor actor, List<Pet> petsList) {
        Pet expectedPet = petsList.getFirst();

        actor.should(
                seeThat("Pet response body", PetResponse.petObject(), is(equalTo(expectedPet)))
        );
    }

    @When("{actor} updates the pet {int} without any form data")
    public void heUpdatesThePetWithoutAnyFormData(Actor actor, int petId) {
        Map<String, String> emptyFormData = new HashMap<>();

        actor.attemptsTo(
                UpdatePetWithFormData.update(petId, emptyFormData)
        );
    }
}
