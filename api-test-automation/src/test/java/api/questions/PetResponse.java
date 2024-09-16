package api.questions;

import api.model.Pet;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

import java.util.Arrays;
import java.util.List;

public class PetResponse {

    public static Question<String> body(){
        return actor -> SerenityRest.lastResponse().getBody().asString();
    }

    public static Question<Pet> petObject(){
        return actor -> SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", Pet.class);
    }

    public static Question<String[]> photosUrls(){
        return actor -> SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", Pet.class)
                .getPhotoUrls();
    }

    public static Question<Integer> statusCode(){
        return actor -> SerenityRest.lastResponse().statusCode();
    }

    public static Question<List<Pet>> petsObjectList() {
        return actor -> {
            Pet[] pets = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("", Pet[].class);
            return Arrays.asList(pets);
        };
    }
}
