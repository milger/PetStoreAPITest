package api.helper.matchers;

import api.model.Pet;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class PetStatus extends TypeSafeMatcher<List<Pet>> {
    private List<String> status;

    public PetStatus(List<String> status){
        this.status = status;
    }

    public static Matcher<List<Pet>> havePetStatus(List<String> status) {
        return new PetStatus(status);
    }

    @Override
    protected boolean matchesSafely(List<Pet> pets) {
        boolean doesItMatch = false;
        for (Pet pet: pets){
            doesItMatch = status.contains(pet.getStatus());
            if (!doesItMatch) break;
        }
        return doesItMatch;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(status + " status");
    }
}
