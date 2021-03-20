package movie.project.service;

import movie.project.model.Movie;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    void saveMovie(Movie movie);
}
