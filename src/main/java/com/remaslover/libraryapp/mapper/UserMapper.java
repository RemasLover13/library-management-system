package com.remaslover.libraryapp.mapper;

import com.remaslover.libraryapp.dto.UserDto;
import com.remaslover.libraryapp.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<UserDto, User> {
}
