package com.javadeveloperblogs.app.ws.io.Repository;

import com.javadeveloperblogs.app.ws.io.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javadeveloperblogs.app.ws.io.entity.RefreshTokenEntity;
import com.javadeveloperblogs.app.ws.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);

    void deleteByUser(UserEntity user);

    void deleteByExpiryDateBefore(LocalDateTime date);

    void deleteByRevokedTrue();
}
