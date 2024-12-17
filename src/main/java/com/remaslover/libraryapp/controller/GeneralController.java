package com.remaslover.libraryapp.controller;


import com.remaslover.libraryapp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/")
public class GeneralController {

    private final BookService bookService;

    public GeneralController (BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String index(Model model) {
        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        model.addAttribute("dateTime", formattedDateTime);
        model.addAttribute("books", bookService.getTop5Books());
        return "homepage";
    }
}
