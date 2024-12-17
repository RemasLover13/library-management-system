package com.remaslover.libraryapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotNull
    @NotEmpty(message = "fio should be between 20 and 100")
    @Size(min = 20, max = 100)
    private String fio;
    @NotNull
    @NotEmpty(message = "year should be between 1900 and 2025")
    private Integer birthYear;
    private List<BookDto> books;


}
