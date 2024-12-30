package com.remaslover.libraryapp.controller;

import com.remaslover.libraryapp.dto.UserDto;
import com.remaslover.libraryapp.entity.Book;
import com.remaslover.libraryapp.entity.User;
import com.remaslover.libraryapp.mapper.BookMapper;
import com.remaslover.libraryapp.mapper.UserMapper;
import com.remaslover.libraryapp.service.BookService;
import com.remaslover.libraryapp.service.UserService;
import com.remaslover.libraryapp.util.BookValidator;
import com.remaslover.libraryapp.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    private final BookValidator bookValidator;
    private final UserValidator userValidator;

    public UserController(UserService userService, BookService bookService, BookMapper bookMapper, UserMapper userMapper, BookValidator bookValidator, UserValidator userValidator) {
        this.userService = userService;
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.userMapper = userMapper;
        this.bookValidator = bookValidator;
        this.userValidator = userValidator;
    }


    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userMapper.mapToDto(userService.getUsers()));
        return "user/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
//        model.addAttribute("user", userMapper.mapToDto(userService.getUserById(id)));
//        return "user/user_info";

        // Получаем книги с информацией о просроченности

        List<Book> books = bookService.getBooksByUserId(id);
        model.addAttribute("books", books);
        model.addAttribute("user", userMapper.mapToDto(userService.getUserById(id)));
        return "user/user_info";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/add_user";
        }
        userService.save(userMapper.mapToEntity(userDto));
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add_user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {

        userValidator.validate(userDto, bindingResult);


        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "user/edit_user";
        }

        userService.update(userMapper.mapToEntity(userDto));
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);

        if (user != null) {
            UserDto userDto = userMapper.mapToDto(user);
            model.addAttribute("userDto", userDto);
            return "user/edit_user";
        }
        return "redirect:/users";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
