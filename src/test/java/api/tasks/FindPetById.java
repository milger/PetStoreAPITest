package api.tasks;

import api.helper.ApiResources;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static api.helper.ApiResources.PET_ID_PATH_PARAM;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FindPetById implements Task {
    private int petId;

    public FindPetById(int petId){
        this.petId = petId;
    }

    public static FindPetById with(int id){
        return instrumented(FindPetById.class, id);
    }

    @Override
    @Step("{0} fetches a pet with ID #petId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(ApiResources.PET_FIND_BY_ID_ENDPOINT)
                        .with(request -> request.pathParam(PET_ID_PATH_PARAM, petId))
        );
    }
}
