package com.remaslover.libraryapp.service.impl;


import com.remaslover.libraryapp.entity.User;
import com.remaslover.libraryapp.repository.UserRepository;
import com.remaslover.libraryapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
