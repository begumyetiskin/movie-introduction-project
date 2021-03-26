package movie.project.service;

import movie.project.model.Movie;
import movie.project.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl (MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> currentMovie = movieRepository.findById(id);
        if (currentMovie.isPresent()) {
            return currentMovie.get();
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        long id = movie.getId();
        Optional<Movie> currentMovie = movieRepository.findById(id);
            Movie updatedMovie = currentMovie.get();
            updatedMovie.setName(movie.getName());
            updatedMovie.setReleaseYear(movie.getReleaseYear());
            updatedMovie.setCategory(movie.getCategory());
            updatedMovie.setDescription(movie.getDescription());
            updatedMovie.setImageUrl(movie.getImageUrl());
            updatedMovie.setLanguage(movie.getLanguage());
            movieRepository.save(updatedMovie);
            return updatedMovie;
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }
}
