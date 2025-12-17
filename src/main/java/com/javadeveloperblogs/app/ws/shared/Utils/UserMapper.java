package com.javadeveloperblogs.app.ws.shared.Utils;
import com.javadeveloperblogs.app.ws.io.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(CreateUserRequestDto dto) {
        UserEntity entity = new UserEntity();
      //  entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
      //  entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }

    public UserResponseDto toResponseDto(UserEntity entity) {
        UserResponseDto dto = new UserResponseDto();
     //   dto.setId(entity.getId());
      //  dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
     //   dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
