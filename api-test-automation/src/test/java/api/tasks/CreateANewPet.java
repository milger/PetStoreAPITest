package api.tasks;

import api.helper.ApiResources;
import io.restassured.http.ContentType;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateANewPet implements Task {
    private String body;

    public CreateANewPet(String body){
        this.body = body;
    }

    public static CreateANewPet withBody(String body){
        return instrumented(CreateANewPet.class, body);
    }

    @Override
    @Step("{0} creates a new pet: #body")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(ApiResources.PET_CREATE_ENDPOINT)
                        .with(request -> request.contentType(ContentType.JSON)
                                .body(body))
        );
    }
}
