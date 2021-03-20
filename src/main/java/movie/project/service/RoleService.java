package movie.project.service;

import movie.project.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    void saveRole(Role role);

    Role getRoleByName(String roleName);

   // List<Role> getAllRoles();
}
