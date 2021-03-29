package movie.project.controller;

import movie.project.config.FileUploadUtil;
import movie.project.model.Movie;
import movie.project.service.MovieCastService;
import movie.project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieCastService movieCastService;

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

    @GetMapping("/movie/list")
    public String list(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "user/listMovie";
    }

    @PostMapping(value = "/movie/add")
    public RedirectView createMovie(@Valid @ModelAttribute("movie") Movie movie, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        movie.setImageUrl(fileName);
        Movie savedMovie = movieService.createMovie(movie);
        String uploadDir = "movie-photos/" + savedMovie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/movie/listMovie", true);
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
        movieCastService.deleteMovieCastById(id);
        System.out.println("silindi");
        return "redirect:/movie/listMovie";
    }




}
