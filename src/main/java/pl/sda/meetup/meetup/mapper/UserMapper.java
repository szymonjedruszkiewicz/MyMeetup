package pl.sda.meetup.meetup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.sda.meetup.meetup.dto.UserDto;
import pl.sda.meetup.meetup.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDTO);

    UserDto userToDto(User user);


}
