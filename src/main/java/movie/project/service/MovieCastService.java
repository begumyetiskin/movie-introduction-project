package movie.project.service;


import movie.project.model.Movie;
import movie.project.model.MovieCast;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieCastService {
    MovieCast getMovieCastByID(Long id);

    List<MovieCast> getAllMovies();

    MovieCast createMovieCast(MovieCast movieCast);

    void deleteMovieCastById(Long id);

}
