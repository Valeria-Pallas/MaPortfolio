package com.example.util;

import java.util.Collection;
import java.util.List;

public interface ApplicationMapper<T1, T2> {

  T2 mapToDto(T1 entity);

  T1 mapToEntity(T2 dto);

  List<T2> mapToDtoList(Collection<T1> entities);

  List<T1> mapToEntityList(Collection<T2> dtos);


}
