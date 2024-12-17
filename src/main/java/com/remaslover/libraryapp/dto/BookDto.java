package com.remaslover.libraryapp.dto;

import com.remaslover.libraryapp.entity.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    @NotNull
    @NotEmpty(message = "Title should be between 2 and 50")
    @Size(min = 2, max = 50)
    private String title;
    @NotNull
    @Min(0)
    @Max(2025)
    private Integer year;
    @NotNull
    @NotEmpty(message = "Author should be between 2 and 50")
    @Size(min = 2, max = 50)
    private String author;
    private User user;


}
