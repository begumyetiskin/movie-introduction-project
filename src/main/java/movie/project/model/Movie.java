package movie.project.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movie_id",unique=true, nullable = false)

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

}
