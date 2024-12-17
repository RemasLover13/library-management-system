package com.remaslover.libraryapp.mapper;

import com.remaslover.libraryapp.dto.BookDto;
import com.remaslover.libraryapp.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends Mappable<BookDto, Book> {
}
