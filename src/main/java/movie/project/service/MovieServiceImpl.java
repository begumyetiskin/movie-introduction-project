package movie.project.service;

import movie.project.model.Movie;
import movie.project.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl (MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public void saveMovie(Movie movie){
        movieRepository.save(movie);
    }
}
