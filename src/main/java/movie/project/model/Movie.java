package movie.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 254)
    private Long releaseYear;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @Column(name="image", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String language;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="movies_casts", joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="cast_id"))
    private List<Cast> casts;
}
