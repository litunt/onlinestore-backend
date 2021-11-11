package com.online.store.repository;

import com.online.store.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    boolean existsByUsernameAndPassword(String username, String password);

    @Query("select uc.user.id from UserCredentials uc where uc.username = :username and uc.password = :password")
    Long findUserIdByUsernameAndPassword(@Param("username") String username,
                                         @Param("password") String password);
}
