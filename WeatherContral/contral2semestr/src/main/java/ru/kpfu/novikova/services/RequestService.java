package ru.kpfu.novikova.services;

import ru.kpfu.novikova.dto.RequestDto;

import java.util.List;

public interface RequestService {
    List<RequestDto> findAllByUserId(int userId);

    List<RequestDto> findAllByCity(String city);
}
