package max.dev.portfolioapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import lombok.RequiredArgsConstructor;
import max.dev.portfolioapi.model.Role;

@Data
@RequiredArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String githubProfileUrl;
    private String linkedinProfileUrl;
    private String twitterProfileUrl;
    @Enumerated(EnumType.STRING)
    private Role role;

}
