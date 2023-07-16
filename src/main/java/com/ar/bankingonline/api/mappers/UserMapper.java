package com.ar.bankingonline.api.mappers;

import com.ar.bankingonline.api.dtos.UserDto;
import com.ar.bankingonline.domain.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User dtoToUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserDto userMapToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
    public UserDto UserToDtoMap(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());

        return dto;
    }

    public User dtoMapToUser(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }

}
