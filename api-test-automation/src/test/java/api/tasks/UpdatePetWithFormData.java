package api.tasks;

import api.helper.ApiResources;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static api.helper.ApiResources.PET_ID_PATH_PARAM;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdatePetWithFormData implements Task {
    private int petId;
    private Map<String, String> params;

    public UpdatePetWithFormData(int id, Map<String, String> params){
        this.petId = id;
        this.params = params;
    }

    public static UpdatePetWithFormData update(int id, Map<String, String> params){
        return instrumented(UpdatePetWithFormData.class, id, params);
    }

    @Override
    @Step("{0} updates an existing pet: #body")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiResources.PET_UPDATE_WITH_FORM_ENDPOINT)
                        .with(request -> request.queryParams(params).pathParam(PET_ID_PATH_PARAM, petId))
        );
    }
}
