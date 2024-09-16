package api.tasks;

import api.helper.ApiResources;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateExistingPet implements Task {
    private String body;

    public UpdateExistingPet(String body){
        this.body = body;
    }

    public static UpdateExistingPet with(String body){
        return instrumented(UpdateExistingPet.class, body);
    }

    @Override
    @Step("{0} updates an existing pet: #body")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(ApiResources.PET_UPDATE_ENDPOINT)
                        .with(request -> request.header("Content-Type", "application/json")
                                .body(body))
        );
    }
}
