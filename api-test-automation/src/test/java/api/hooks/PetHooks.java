package api.hooks;

import api.model.Pet;
import api.tasks.DeletePet;
import io.cucumber.java.After;

import static api.helper.RememberConstants.NEW_CREATED_PET;
import static api.helper.RememberConstants.PREV_CREATED_PET;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PetHooks {

    @After(value = "@DeleteCreatedPetAfterScenario")
    public void deleteCreatedPet(){
        Pet createdPet = theActorInTheSpotlight().recall(NEW_CREATED_PET);
        deletePet(Integer.toString(createdPet.getId()));
    }

    @After(value = "@DeletePrevCreatedPetAfterScenario")
    public void deletePreviousCreatedPet(){
        Pet pet = theActorInTheSpotlight().recall(PREV_CREATED_PET);
        deletePet(Integer.toString(pet.getId()));
    }

    private void deletePet(String id){
        theActorInTheSpotlight().attemptsTo(
                DeletePet.withId(id)
        );
    }
}
