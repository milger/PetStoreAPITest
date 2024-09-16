package api.stepdefinitions;

import api.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.Map;

public class ParameterDefinitions {

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @DataTableType
    public Pet petEntry(Map<String, String> entry) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Tags[] tags = objectMapper.readValue(entry.get("tags"), Tags[].class);

        Category category = new Category(Integer.parseInt(entry.get("category id")), entry.get("category name"));

        return new Pet(
                Integer.parseInt(entry.get("id")),
                entry.get("name"),
                category,
                entry.get("photoUrls").split(","),
                tags,
                entry.get("status"));
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
