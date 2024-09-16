package api.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Pet {
    private int id;

    private String name;

    private Category category;

    private String[] photoUrls;

    private Tags[] tags;

    private String status;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pet other = (Pet) obj;
        return (id == other.id &&
                Objects.equals(name, other.name) &&
                Objects.equals(category, other.category) &&
                Arrays.equals(photoUrls, other.photoUrls) &&
                Arrays.equals(tags, other.tags) &&
                Objects.equals(status, other.status));
    }

    public boolean hasAnyOfTheseTags(List<String> tagList) {
        boolean itHasAnyTag = false;
        for (Tags tag: tags){
            itHasAnyTag = tagList.contains(tag.getName());
            if (itHasAnyTag) break;
        }
        return itHasAnyTag;
    }
}
