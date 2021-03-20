package movie.project.service;

import movie.project.model.User;
import movie.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> currentUser = userRepository.getUserByUsername(username);
        if(currentUser.isPresent()){
            return currentUser.get();
        }
        return null;
    }
}
