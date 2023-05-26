package kr.cornerstone.global.config.security;

import kr.cornerstone.domain.user.enums.AuthType;
import kr.cornerstone.global.config.jwt.JwtRequestFilter;
import kr.cornerstone.global.config.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final AuthenticationConfiguration authConfiguration;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CorsConfig corsConfig;
    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .formLogin().disable()
                // 쿠키 기반이 아닌 JWT 기반이므로 사용하지 않음
                .csrf().disable()
                // CORS 설정
                .cors(c -> corsConfig.corsConfigurationSource())
                // Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 조건별로 요청 허용/제한 설정
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(openURI()).permitAll()
                        .requestMatchers(userURI()).hasAuthority(AuthType.USER.getCode())
                        .requestMatchers(adminURI()).hasAuthority(AuthType.ADMIN.getCode())
                        .anyRequest().permitAll()
                )
                // JWT 인증 필터 적용
                .addFilterBefore(new JwtRequestFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                // 에러 핸들링
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint);
        return http.build();
    }

    private String[] openURI() {
        String[] uris = {
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/v3/api-docs/**",
                "/api/v1/user/google/sign-in",
                "/api/v1/user/google/sign-up",
                "/api/v1/user/apple/sign-in",
                "/api/v1/user/apple/sign-up"
        };
        return uris;
    }

    private String[] userURI(){
        String[] uris = {
               "/api/**"
        };
        return uris;
    }

    private String[] adminURI(){
        String[] uris = {
        };
        return uris;
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(jwtUtil);
    }
}
