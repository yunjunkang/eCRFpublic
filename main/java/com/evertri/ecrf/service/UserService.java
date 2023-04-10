package com.evertri.ecrf.service;

import com.evertri.ecrf.model.Role;
import com.evertri.ecrf.model.User;
import com.evertri.ecrf.repository.RoleRepository;
import com.evertri.ecrf.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    /**
     * Method to find all users
     * @return List of users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void User(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    /**
     * Method to create a new user
     * @param user user object
     * @return created user object
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Method to save a user
     * @param user user object
     * @return saved user object
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Method to find a user by its id
     *
     * @param id user id
     * @return user object
     */
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Method to delete a user by its id
     * @param id user id
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Method to update a user
     * @param id user id
     * @param user updated user object
     * @return updated user object
     */
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        user.setId(id);
        return userRepository.save(user);
    }

    // ...
    private final RoleRepository roleRepository;

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }


}
