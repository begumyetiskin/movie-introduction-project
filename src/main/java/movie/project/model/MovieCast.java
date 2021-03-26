package movie.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "movies_casts")
public class MovieCast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "cast_id")
    Cast cast;

}
