package api.questions;

import api.entities.Pet;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class PetResponse {

    public static Question<String> bodyAsString(){
        return actor -> SerenityRest.lastResponse().getBody().asString();
    }

    public static Question<Pet> bodyAsPetObject(){
        return actor -> SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", Pet.class);
    }

    public static Question<Integer> statusCode(){
        return actor -> SerenityRest.lastResponse().statusCode();
    }
}
