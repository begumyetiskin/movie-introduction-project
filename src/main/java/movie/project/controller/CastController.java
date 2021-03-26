package movie.project.controller;

import movie.project.model.Cast;
import movie.project.model.Movie;
import movie.project.model.MovieCast;
import movie.project.service.CastService;
import movie.project.service.MovieCastService;
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
import java.util.List;

@Controller
public class CastController {

    @Autowired
    CastService castService;

    @Autowired
    MovieCastService movieCastService;

    @Autowired
    MovieService movieService;

    Long movieID,castID;

    @GetMapping(path = "/cast/{id}")
    public ResponseEntity<Cast> getCastById(@PathVariable(value = "id") Long id) {
        Cast cast = castService.getCastById(id);
        return new ResponseEntity<>(cast, HttpStatus.OK);
    }

    @RequestMapping(value = "/cast/add", method = RequestMethod.GET)
    public String castAddPage(Model model) {
        model.addAttribute("cast", new Cast());
        return "admin/addCast";
    }

    @GetMapping("/cast/list")
    public String listCasts(Model model) {
        model.addAttribute("casts", castService.getAllCasts());
        return "admin/listCast";
    }

    @PostMapping(value = "/cast/add")
    public String createCastAndMovieCast(@Valid @ModelAttribute("cast") Cast cast, @Valid @ModelAttribute("movieCast") MovieCast movieCast, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/addCast";
        }
        castService.createCast(cast);
        List<Cast> listCasts = castService.getAllCasts();

        for (int i=0; i<listCasts.size(); i++){
            if(i==listCasts.size()-1){
                castID = listCasts.get(i).getId();
            }
        }
        System.out.println(castID);
        // MovieCast tablosuna veri ekleme:

        movieCast.setMovie(movieService.getMovieById(movieID));
        movieCast.setCast(castService.getCastById(castID));

        movieCastService.createMovieCast(movieCast);


        System.out.println(castService.getCastById(cast.getId()));
        return "redirect:/cast/list";
    }

    @RequestMapping(value = "/movie-action", method = RequestMethod.POST)
    public String viewHomePage(@ModelAttribute(value="moviecast") Movie movie, Model model, RedirectAttributes redirectAttributes) {
        movieID = movie.getId();

        System.out.println("Film ID: "+movieID);

        return "redirect:/cast/add";
    }



}
