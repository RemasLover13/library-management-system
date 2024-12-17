package com.remaslover.libraryapp.mapper;

import java.util.List;

public interface Mappable<D, E> {
    E mapToEntity(D dto);

    D mapToDto(E entity);

    List<D> mapToDto(List<E> entities);

    List<E> mapToEntity(List<D> dtos);
}
