package kr.cornerstone.repository;

import kr.cornerstone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByIdAndUseFlag(Long id,Boolean useFlag);

    Optional<User> findByGoogleIdAndUseFlag(String googleId,Boolean useFlag);
}