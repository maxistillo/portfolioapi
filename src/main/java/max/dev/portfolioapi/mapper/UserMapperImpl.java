package max.dev.portfolioapi.mapper;

import max.dev.portfolioapi.dto.UserDTO;
import max.dev.portfolioapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(UserModel userModel) {
        UserDTO u = new UserDTO();
        u.setId(userModel.getId());
        u.setName(userModel.getName());
        u.setLastName(userModel.getLastName());
        u.setEmail(userModel.getEmail());
        u.setGithubProfileUrl(userModel.getGithubProfileUrl());
        u.setLinkedinProfileUrl(userModel.getLinkedinProfileUrl());
        u.setTwitterProfileUrl(userModel.getTwitterProfileUrl());
        u.setRole(userModel.getRole());

        return u;
    }

}
