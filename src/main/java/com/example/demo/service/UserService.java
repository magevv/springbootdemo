package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exeptions.UserAlreadyExistException;
import com.example.demo.exeptions.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByName(user.getName()) != null) {
            throw new UserAlreadyExistException("User already exists");
        }
        return userRepo.save(user);
    }

    public Iterable<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public User getOne(Long id) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepo.findById(id);
        if (userEntity.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id %d does not exist", id));
        }
        return User.toModel(userEntity.get());
    }

    public void delete(Long id) throws UserNotFoundException {
        Optional<UserEntity> userEntity = userRepo.findById(id);
        if (userEntity.isEmpty()) {
            throw new UserNotFoundException(String.format("Not possible to delete a non-existing user with id %d", id));
        }
        userRepo.deleteById(id);
    }
}
