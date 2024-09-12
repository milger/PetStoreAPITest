package api.hooks;

import api.entities.Pet;
import api.tasks.DeletePet;
import io.cucumber.java.After;

import static api.helper.RememberConstants.NEW_CREATED_PET;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PetHooks {

    @After(value = "@DeleteCreatedPetAfterScenario")
    public void deleteCreatedPet(){
        Pet createdPet = theActorInTheSpotlight().recall(NEW_CREATED_PET);
        theActorInTheSpotlight().attemptsTo(
                DeletePet.withId(createdPet.getId())
        );
    }
}
