package api.tasks;

import api.helper.ApiResources;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static api.helper.ApiResources.PET_ID_PATH_PARAM;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeletePet implements Task {
    private String petId;

    public DeletePet(String petId){
        this.petId = petId;
    }

    public static DeletePet withId(String id){
        return instrumented(DeletePet.class, id);
    }

    @Override
    @Step("{0} deletes a pet: #petId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(ApiResources.PET_DELETE_ENDPOINT)
                        .with(request -> request.pathParam(PET_ID_PATH_PARAM, petId))
        );
    }
}
