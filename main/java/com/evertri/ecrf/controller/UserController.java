package com.evertri.ecrf.controller;

import com.evertri.ecrf.model.Role;
import com.evertri.ecrf.model.Study;
import com.evertri.ecrf.model.User;
import com.evertri.ecrf.service.StudyService;
import com.evertri.ecrf.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final StudyService studyService;


    @Autowired
    public UserController(UserService userService, StudyService studyService) {
        this.userService = userService;
        this.studyService = studyService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //add user to a study
    @PostMapping("/{userId}/studies/{studyId}")
    public User addStudyToUser(@PathVariable Long userId, @PathVariable Long studyId) {
        User user = userService.findUserById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Study study = studyService.getStudyById(studyId);
        if (study == null) {
            throw new EntityNotFoundException("Study not found with id: " + studyId);
        }
        user.addStudy(study);
        return userService.saveUser(user);
    }

    //remove user from a study
    @DeleteMapping("/{userId}/studies/{studyId}")
    public User removeStudyFromUser(@PathVariable Long userId, @PathVariable Long studyId) {
        User user = userService.findUserById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Study study = studyService.getStudyById(studyId);
        if (study == null) {
            throw new EntityNotFoundException("Study not found with id: " + studyId);
        }
        user.removeStudy(study);
        return userService.saveUser(user);
    }


    // Get all roles
    @GetMapping("/roles")
    public List<Role> findAllRoles() {
        return userService.findAllRoles();
    }

    // Save new role
    @PostMapping("/roles")
    public Role saveRole(@RequestBody Role role) {
        return userService.saveRole(role);
    }

    // Assign a role to a user
    @PutMapping("/{userId}/roles/{roleId}")
    public User assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        User user = userService.findUserById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Role role = userService.findRoleById(roleId);
        if (role == null) {
            throw new EntityNotFoundException("Role not found with id: " + roleId);
        }
        user.getRoles().add(role);
        return userService.saveUser(user);
    }

    // Remove a role from a user
    @DeleteMapping("/{userId}/roles/{roleId}")
    public User removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        User user = userService.findUserById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Role role = userService.findRoleById(roleId);
        if (role == null) {
            throw new EntityNotFoundException("Role not found with id: " + roleId);
        }
        user.getRoles().remove(role);
        return userService.saveUser(user);
    }


}
