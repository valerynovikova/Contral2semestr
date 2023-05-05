package ru.kpfu.novikova.services;

import ru.kpfu.novikova.dto.CreateUserDto;
import ru.kpfu.novikova.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getByEmail(String email);

    UserDto getById(Integer id);

    List<UserDto> getAll();

    UserDto signUp(CreateUserDto createUserDto, String url);

    boolean verify(String verificationCode);

    void sendVerificationMail(String mail, String name, String code, String url);
}
