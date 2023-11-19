package max.dev.portfolioapi.service;

import lombok.RequiredArgsConstructor;
import max.dev.portfolioapi.model.TokenModel;
import max.dev.portfolioapi.model.TokenType;
import max.dev.portfolioapi.model.UserModel;
import max.dev.portfolioapi.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

//  -------------------------SAVE TOKEN---------------------------
    public void saveUserToken(UserModel user, String jwtToken) {
        var token = TokenModel.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);

    }

//  -----------------------------REVOKE USER TOKENS ----------------------------------
    public void revokeAllUserTokens(UserModel user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return ;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
