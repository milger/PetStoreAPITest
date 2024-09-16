package api.helper.matchers;

import api.model.Pet;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class PetTags extends TypeSafeMatcher<List<Pet>> {
    private List<String> tags;

    public PetTags(List<String> status){
        this.tags = status;
    }

    public static Matcher<List<Pet>> havePetTags(List<String> status) {
        return new PetTags(status);
    }

    @Override
    protected boolean matchesSafely(List<Pet> pets) {
        boolean doesItMatch = false;
        for (Pet pet: pets){
            doesItMatch = pet.hasAnyOfTheseTags(tags);
            if (!doesItMatch) break;
        }
        return doesItMatch;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(tags + " tags");
    }
}
