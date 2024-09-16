package api.tasks;

import api.helper.ApiResources;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FindPetByStatus implements Task {
    private List<String> params;
    private final String statusParamName = "status";

    public FindPetByStatus(List<String> params){
        this.params = params;
    }

    public static FindPetByStatus withParams(List<String> params){
        return instrumented(FindPetByStatus.class, params);
    }

    @Override
    @Step("{0} finds pets by status: #params")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(ApiResources.PET_FIND_BY_STATUS_ENDPOINT)
                        .with(request -> request.queryParam(statusParamName, params))
        );
    }
}
