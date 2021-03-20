package movie.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "casts")
public class Cast {
    @Id
    @Column(name = "cast_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;


}