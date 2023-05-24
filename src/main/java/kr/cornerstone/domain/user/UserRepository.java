package kr.cornerstone.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByIdAndUseFlag(Long id,Boolean useFlag);
    Optional<User> findByRefreshTokenAndUseFlag(String refreshToken, Boolean useFlag);
    Optional<User> findByGoogleIdAndUseFlag(String googleId,Boolean useFlag);
    Optional<User> findByAppleIdAndUseFlag(String appleId,Boolean useFlag);
    boolean existsByGoogleId(String googleId);
    boolean existsByAppleId(String appleId);
}
