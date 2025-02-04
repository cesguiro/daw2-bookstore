package es.cesguiro.dao.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publishers")
public class PublisherEntityJpa {

    @Id
    private Long id;
    private String name;
    private String slug;

    @OneToMany(mappedBy = "publisher")
    private List<BookEntityJpa> books;
}
