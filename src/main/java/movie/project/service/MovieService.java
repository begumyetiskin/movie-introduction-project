package movie.project.service;

import movie.project.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    Movie getMovieById(Long id);

    List<Movie> getAllMovies();

    Movie createMovie(Movie movie);

    Movie updateMovie(Movie movie);

    void deleteMovieById(Long id);
}
