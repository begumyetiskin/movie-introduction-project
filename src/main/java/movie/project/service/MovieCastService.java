package movie.project.service;


import movie.project.model.MovieCast;
import org.springframework.stereotype.Service;

@Service
public interface MovieCastService {
    MovieCast getMovieCastByID(Long id);

    MovieCast createMovieCast(MovieCast movieCast);
}
