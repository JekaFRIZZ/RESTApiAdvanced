package com.epam.esm.controller;

import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.User;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping( "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns all {@link User} .
     *
     * @param limit - a number of {@link User} objects to return
     * @param offset - a number of {@link User} objects to skip when returning
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link List} of {@link User} objects
     */
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam int limit,
                                    @RequestParam int offset) {
        List<User> users = userService.getAll(limit, offset);
        for(User user : users) {
            user.add(linkTo(methodOn(UserController.class).getById(user.getId())).withSelfRel());
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Returns {@link User} object
     *
     * @param id - the {@link User} object's id that is to be retrieved from a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link User} object or a {@link com.epam.esm.entity.ErrorData} object
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        user.add(linkTo(methodOn(UserController.class).getById(id)).withSelfRel());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Creates {@link User} object or throws {@link com.epam.esm.exception.FieldExistenceException} if some fields
     *      * required for creation are missing.
     *
     * @param userDTO - dto object which will be mapped to {@link User}
     * @return {@link ResponseEntity} with a {@link HttpStatus} alone or additionally with a {@link com.epam.esm.entity.ErrorData} object.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        userService.create(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    /**
     * Deletes {@link User} by its id or throws {@link com.epam.esm.exception.ResourceExistenceException} if the object
     * with such id doesn't exist
     *
     * @param id`s object {@link User} which will be deleted
     * @return @link ResponseEntity} with a {@link HttpStatus} alone or additionally with a {@link com.epam.esm.entity.ErrorData} object.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
