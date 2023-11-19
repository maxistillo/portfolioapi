package max.dev.portfolioapi.mapper;

import max.dev.portfolioapi.dto.UserDTO;
import max.dev.portfolioapi.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(UserModel userModel);


}
