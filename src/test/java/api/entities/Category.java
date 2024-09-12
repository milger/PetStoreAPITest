package api.entities;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    private int id;

    private String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category other = (Category) obj;
        return (id == other.id &&
                Objects.equals(name, other.name));
    }
}
