package api.stepdefinitions;

import api.model.Pet;
import api.questions.PetResponse;
import api.tasks.FindPetById;
import api.tasks.FindPetByStatus;
import api.tasks.FindPetByTags;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import java.util.*;

import static api.helper.matchers.PetStatus.havePetStatus;
import static api.helper.matchers.PetTags.havePetTags;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;

public class FindPetStepDefinitions {

    @And("{actor} finds a pet with id equal to {string}")
    public void heFindsAPetWithIdEqualTo(Actor actor, String petId) {
        actor.attemptsTo(
                FindPetById.with(petId)
        );
    }

    @When("{actor} finds pets by status without any param")
    public void heFindsPetsByStatusWithoutAnyParam(Actor actor) {
        List<String> emptyParams = new ArrayList<>();

        actor.attemptsTo(
                FindPetByStatus.withParams(emptyParams)
        );
    }

    @And("{actor} should get a list of pets with {string} status by default")
    @And("{actor} should get a list of pets with {string} status")
    public void heShouldGetAListOfPetsWithStatus(Actor actor, String status) {
        List<String> expectedStatus = Arrays.asList(status);

        actor.should(
                seeThat("All Pet status", PetResponse.petsObjectList(), havePetStatus(expectedStatus))
        );
    }

    @When("{actor} finds pets by status using the following status param:")
    public void heFindsPetsByStatusUsingTheFollowingParam(Actor actor, DataTable table) {
        List<String> params = table.asList();

        actor.attemptsTo(
                FindPetByStatus.withParams(params)
        );
    }

    @And("{actor} should get a list of pets with following status:")
    public void heShouldGetAListOfPetsWithFollowingStatus(Actor actor, DataTable table) {
        List<String> expectedStatus = table.asList();

        actor.should(
                seeThat("All Pet status", PetResponse.petsObjectList(), havePetStatus(expectedStatus))
        );
    }

    @And("{actor} should not see the following data in the list of pets returned:")
    public void heShouldNotSeeTheFollowingDataInTheListOfPetsReturned(Actor actor, List<Pet> petsList) {
        Pet deletedPet = petsList.getFirst();

        actor.should(
                seeThat("Pet list", PetResponse.petsObjectList(), not(contains(deletedPet)))
        );
    }

    @When("{actor} finds pets by tags using the following tags params:")
    public void heFindsPetsByTagsUsingTheFollowingTagsParams(Actor actor, DataTable table) {
        List<String> params = table.asList();

        actor.attemptsTo(
                FindPetByTags.withParams(params)
        );
    }

    @And("{actor} should get a list of pets that has {string} tag")
    public void heShouldGetAListOfPetsThatHasTag(Actor actor, String tagName) {
        List<String> expectedTags = Arrays.asList(tagName);

        actor.should(
                seeThat("All Pet tags", PetResponse.petsObjectList(), havePetTags(expectedTags))
        );
    }

    @And("{actor} should get a list of pets that has any of the following tags:")
    public void heShouldGetAListOfPetsThatHasAnyOfTheFollowingTags(Actor actor, DataTable table) {
        List<String> expectedTags = table.asList();

        actor.should(
                seeThat("All Pet tags", PetResponse.petsObjectList(), havePetTags(expectedTags))
        );
    }

    @When("{actor} finds pets by tags without any tags param")
    public void heFindsPetsByTagsWithoutAnyTagsParam(Actor actor) {
        List<String> emptyParams = new ArrayList<>();

        actor.attemptsTo(
                FindPetByTags.withParams(emptyParams)
        );
    }
}
