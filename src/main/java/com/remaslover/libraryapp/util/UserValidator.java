package com.remaslover.libraryapp.util;

import com.remaslover.libraryapp.dto.UserDto;
import com.remaslover.libraryapp.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        if (userDto.getFio() == null || userDto.getFio().isEmpty()) {
            errors.rejectValue("fio", "fio.empty", "Fio should not be empty");
        } else if (userDto.getFio().length() < 2 || userDto.getFio().length() > 50) {
            errors.rejectValue("fio", "fio.size", "Fio should be between 2 and 50 characters");
        }

        if (userDto.getBirthYear() == null) {
            errors.rejectValue("birthYear", "birthYear.null", "birthYear should not be null");
        } else if (userDto.getBirthYear() < 0 || userDto.getBirthYear() > 2025) {
            errors.rejectValue("birthYear", "birthYear.range", "birthYear should be between 0 and 2025");
        }

    }
}
