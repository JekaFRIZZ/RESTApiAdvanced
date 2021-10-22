package com.epam.esm.service;

import com.epam.esm.dao.UserRepository;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.User;
import com.epam.esm.exception.DuplicateResourceException;
import com.epam.esm.exception.ResourceExistenceException;
import com.epam.esm.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements com.epam.esm.service.Service<User> {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }


    public Integer create(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.getByName(userDTO.getName());
        if (optionalUser.isPresent()) {
            throw new DuplicateResourceException("The user with name '" +
                    userDTO.getName() + "' already exist", 7777);
        }
        userValidator.validate(userDTO);

        User user = toUser(userDTO);
        return userRepository.create(user);
    }

    private User toUser(UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());

        return user;
    }

    public List<User> getAll(int limit, int offset) {
        validatePaginationParams(limit, offset);
        return userRepository.getAll(limit, offset);
    }

    public User getById(Integer id) {
        Optional<User> user = userRepository.getById(id);
        return user.orElseThrow(
                () -> new ResourceExistenceException("User with id = '" + id + "' doesn't exist", 777));
    }

    public void deleteById(Integer id) {
        Optional<User> userOptional = userRepository.getById(id);
        User user = userOptional.orElseThrow(
                () -> new ResourceExistenceException("User with id = '" + id + "' doesn't exist", 777));
        userRepository.deleteById(user);
    }
}
