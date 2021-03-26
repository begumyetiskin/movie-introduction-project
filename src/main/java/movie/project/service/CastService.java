package movie.project.service;

import movie.project.model.Cast;
import movie.project.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CastService {
    Cast getCastById(Long id);

    List<Cast> getAllCasts();

    Cast createCast(Cast cast);

    Cast updateCast(Cast cast);

    void deleteCastById(Long id);
}
