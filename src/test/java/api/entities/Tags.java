package api.entities;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tags {
    private int id;

    private String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tags other = (Tags) obj;
        return (id == other.id &&
                Objects.equals(name, other.name));
    }
}
