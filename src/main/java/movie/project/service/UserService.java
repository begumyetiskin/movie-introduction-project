package movie.project.service;

import movie.project.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(User user);

    User getUserByUsername(String username);
}
