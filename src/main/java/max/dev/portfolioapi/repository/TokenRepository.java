package max.dev.portfolioapi.repository;

import max.dev.portfolioapi.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Long> {

//    @Query("SELECT t FROM Token t JOIN t.user u " +
//            "WHERE u.id = :id AND (t.expired = false OR t.revoked = false)")
//    List<TokenModel> findAllValidTokenByUser(Long id);

    @Query("SELECT t FROM TokenModel t WHERE t.user.id = :id AND (t.expired = false OR t.revoked = false)")
    List<TokenModel> findAllValidTokenByUser(@Param("id") Long id);
    Optional<TokenModel> findByToken(String token);

}
