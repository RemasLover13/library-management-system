package com.remaslover.libraryapp.util;

import com.remaslover.libraryapp.dto.BookDto;
import com.remaslover.libraryapp.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BookDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto bookDto = (BookDto) target;

        if (bookDto.getTitle() == null || bookDto.getTitle().isEmpty()) {
            errors.rejectValue("title", "title.empty", "Title should not be empty");
        } else if (bookDto.getTitle().length() < 2 || bookDto.getTitle().length() > 50) {
            errors.rejectValue("title", "title.size", "Title should be between 2 and 50 characters");
        }

        if (bookDto.getYear() == null) {
            errors.rejectValue("year", "year.null", "Year should not be null");
        } else if (bookDto.getYear() < 0 || bookDto.getYear() > 2025) {
            errors.rejectValue("year", "year.range", "Year should be between 0 and 2025");
        }

        if (bookDto.getAuthor() == null || bookDto.getAuthor().isEmpty()) {
            errors.rejectValue("author", "author.empty", "Author should not be empty");
        } else if (bookDto.getAuthor().length() < 2 || bookDto.getAuthor().length() > 50) {
            errors.rejectValue("author", "author.size", "Author should be between 2 and 50 characters");
        }
    }
}
