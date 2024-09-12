package api.stepdefinitions;

import api.entities.Pet;
import api.helper.Utils;
import api.questions.PetResponse;
import api.tasks.CreateANewPet;
import api.tasks.FindPetById;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import java.util.List;

import static api.helper.RememberConstants.*;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PetStepDefinitions {

    @When("{actor} creates a new pet with following information:")
    public void heCreatesANewPetWithFollowingInformation(Actor actor, List<Pet> petsList) {
        Pet newPet = petsList.getFirst();
        String body = Utils.objectToJsonString(newPet);

        actor.attemptsTo(
                CreateANewPet.withBody(body)
        );
        actor.remember(NEW_CREATED_PET, newPet);
    }

    @And("{actor} should get the same pet information in the response")
    public void heShouldGetTheSamePetInformationInTheResponse(Actor actor) {
        Pet expectedPet = actor.recall(NEW_CREATED_PET);

        actor.should(
                seeThat(PetResponse.bodyAsPetObject(), is(equalTo(expectedPet)))
        );
    }

    @And("{actor} already has an existing pet with following information:")
    public void heHasAnExistingPetWithFollowingInformation(Actor actor, List<Pet> petsList) {
        actor.remember(EXISTING_PET, petsList.getFirst());
    }

    @And("{actor} looks for a pet with id equal to {int}")
    public void heLooksForAPetWithIdEqualTo(Actor actor, int petId) {
        actor.attemptsTo(
                FindPetById.with(petId)
        );
    }

    @Then("{actor} should get the existing pet information without modifications")
    public void heShouldGetTheExistingPetInformationWithoutModifications(Actor actor) {
        Pet expectedPet = actor.recall(EXISTING_PET);

        actor.should(
                seeThat(PetResponse.bodyAsPetObject(), is(equalTo(expectedPet)))
        );
    }

    @When("{actor} prepares the following pet information:")
    public void hePreparesTheFollowingPetInformation(Actor actor, List<Pet> petsList) throws JsonProcessingException {
        Pet newPet = petsList.getFirst();
        String body = Utils.objectToJsonString(newPet);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonBody = (ObjectNode) objectMapper.readTree(body);

        actor.remember(PET_REQUEST_BODY, jsonBody);
    }

    @And("{actor} modifies the {string} pet field to {string}")
    public void heModifiesTheFieldPetFieldToInvalidValue(Actor actor, String field, String value) {
        ObjectNode jsonBody = actor.recall(PET_REQUEST_BODY);
        jsonBody.put(field, value);
    }

    @And("{actor} creates a new pet using the modified information")
    @And("{actor} creates a new pet with missing information")
    public void heCreatesANewPetUsingTheModifiedInformation(Actor actor) {
        ObjectNode jsonBody = actor.recall(PET_REQUEST_BODY);

        actor.attemptsTo(
                CreateANewPet.withBody(jsonBody.toString())
        );
    }

    @And("{actor} removes the {string} pet field")
    public void heRemovesThePetField(Actor actor, String fieldName) {
        ObjectNode jsonBody = actor.recall(PET_REQUEST_BODY);
        jsonBody.remove(fieldName);
    }

}
