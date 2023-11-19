package max.dev.portfolioapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String email;
    private String password;
    private String name;
    private String lastName;
    private String githubProfileUrl;
    private String linkedinProfileUrl;
    private String twitterProfileUrl;

}
