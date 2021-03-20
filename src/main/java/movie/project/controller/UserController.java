package movie.project.controller;

import movie.project.model.Role;
import movie.project.model.User;
import movie.project.security.ApplicationUserDetailsService;
import movie.project.service.RoleService;
import movie.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

@SessionAttributes
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    ApplicationUserDetailsService userDetailsService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(@Valid User user, BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes,
                                        @RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletRequest request) throws UnsupportedEncodingException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword;

        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> params = new HashMap<>();

        User newUser = new User();

        User userExists = userService.getUserByUsername(user.getUsername());

        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
            redirectAttributes.addFlashAttribute("messages", "Username already exists! Please type another username.");
            params.put("messages1", "Username already exists! Please type another username.");
        }
        if (bindingResult.hasErrors()) {
            ModelAndView mvv = new ModelAndView("register", params);
            return mvv;
        } else {
            encodedPassword = passwordEncoder.encode(password);
            System.out.println(password);
            System.out.println(encodedPassword);
            newUser.setPassword(encodedPassword);
            newUser.setUsername(username);
            newUser.setEnabled(true);

            Role roleUserExist = roleService.getRoleByName("USER");


            if (roleUserExist == null) {
                Role role = new Role();
                role.setName("USER");
                roleService.saveRole(role);
            }

            Role roleAdminExist = roleService.getRoleByName("ADMIN");

            if (roleAdminExist == null) {
                Role role = new Role();
                role.setName("ADMIN");
                roleService.saveRole(role);
            }

            newUser.setRole(roleService.getRoleByName("USER"));
            userService.saveUser(newUser);

            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping("/")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User currentUser = userService.getUserByUsername(auth.getName());
        /* boolean hasUserRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));

        boolean hasAdminRole = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));*/

        if (currentUser.getRole().getId() == 1) {
            return "movie/show";
        }
        if (currentUser.getRole().getId() == 2) {
            return "movie/list";
        }

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
}


