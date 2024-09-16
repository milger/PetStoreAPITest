package api.stepdefinitions;

import api.tasks.DeletePet;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

public class DeletePetStepDefinitions {

    @When("{actor} deletes the pet with {string} id")
    @And("{actor} has deleted the pet with {string} id")
    public void heDeletesThePetWithId(Actor actor, String petId) {
        actor.attemptsTo(
                DeletePet.withId(petId)
        );
    }
}
