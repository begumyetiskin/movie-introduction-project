package movie.project.service;

import movie.project.model.Role;
import movie.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        Optional<Role> currentRole = roleRepository.getRoleByName(roleName);
        if(currentRole.isPresent()){
            return currentRole.get();
        }
        return null;
    }
/*
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }*/

}