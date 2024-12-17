package com.remaslover.libraryapp.service;

import com.remaslover.libraryapp.entity.User;

import java.util.List;

public interface UserService {

     User getUserById(long id);

     List<User> getUsers();

     User save(User user);

     User update(User user);

     void delete(Long id);
}
