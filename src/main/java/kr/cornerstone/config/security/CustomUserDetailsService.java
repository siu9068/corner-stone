package kr.cornerstone.config.security;

import kr.cornerstone.entity.User;
import kr.cornerstone.enums.UseType;
import kr.cornerstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userRepository.findByGoogleIdAndUseFlag(userId, UseType.USE.getIsUse()).orElseThrow(() ->
                    new UsernameNotFoundException("유저가 존재하지 않습니다 : " + userId)
            );
        return UserPrincipal.create(user);
    }

}
