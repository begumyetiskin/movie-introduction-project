package movie.project.service;


import movie.project.model.Movie;
import movie.project.model.MovieCast;
import movie.project.repository.MovieCastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieCastServiceImpl implements MovieCastService {

    @Autowired
    MovieCastRepository movieCastRepository;

    @Autowired
    public MovieCastServiceImpl (MovieCastRepository movieCastRepository){
        this.movieCastRepository = movieCastRepository;
    }

    @Override
    public MovieCast createMovieCast(MovieCast movieCast){
        return movieCastRepository.save(movieCast);
    }

    @Override
    public MovieCast getMovieCastByID(Long id) {
        Optional<MovieCast> currentCast = movieCastRepository.findById(id);
        if (currentCast.isPresent()) {
            return currentCast.get();
        }
        return null;
    }

    @Override
    public List<MovieCast> getAllMovies(){
        return movieCastRepository.findAll();
    }

    @Override
    public void deleteMovieCastById(Long id) {
        movieCastRepository.deleteById(id);
    }


}
