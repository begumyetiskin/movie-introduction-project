package movie.project.controller;

import movie.project.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes
@Controller
public class MovieController {

    @Autowired
    MovieService movieService;
}
