package movie.project.controller;

import movie.project.model.Movie;
import movie.project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    Long movieID;

    @GetMapping(path = "/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long id) {
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/add", method = RequestMethod.GET)
    public String movieAddPage(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin/addMovie";
    }

    @GetMapping("/movie/listMovie")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/listMovie";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "user/listMovie";
    }

    @PostMapping(value = "/movie/add")
    public String createMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addMovie";
        }
        movieService.createMovie(movie);
        return "admin/listMovie";
    }



    /*@RequestMapping(path = "/movie/edit/{id}")
    public String updateMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        return "admin/add";
    }*/

   /*
    @RequestMapping(path = "/movie/edit/{id}")
    public String updateMovie(Model model, Movie movie) {
        Movie updatedMovie = movieService.updateMovie(movie);
        model.addAttribute("movie", updatedMovie);
        return "admin/addMovie";
    }
*/
    @RequestMapping("/movie/delete/{id}")
    public String deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovieById(id);
        System.out.println("silindi");
        return "redirect:/movie/listMovie";
    }


}
