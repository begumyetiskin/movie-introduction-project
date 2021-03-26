package movie.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "casts")
public class Cast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cast_id",unique=true, nullable = false)

    private Long id;

    private String firstName;
    private String lastName;

}