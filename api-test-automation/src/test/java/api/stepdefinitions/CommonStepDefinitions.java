package api.stepdefinitions;

import api.helper.ApiResources;
import api.helper.EnvironmentVariableReader;
import api.questions.PetResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

public class CommonStepDefinitions {

    @Given("{actor} is an API user")
    public void actorIsAnAPIUser(Actor actor) {
        String theRestApiBaseUrl = EnvironmentVariableReader.getSerenityProperty(ApiResources.REST_API_BASE_URL_PROPERTY);
        actor.whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @Then("{actor} should get a {int} status code")
    public void heShouldGetAStatusCode(Actor actor, int statusCode) {
        actor.should(
                seeThat("Response status code", PetResponse.statusCode(), is(statusCode))
        );
    }

    @And("{actor} should get the following message as response:")
    public void heShouldGetTheFollowingMessageAsResponse(Actor actor, String expectedResponse) {
        actor.should(
                seeThat("Response body", PetResponse.body(), is(equalTo(expectedResponse)))
        );
    }
}
