package kr.cornerstone.entity;

import jakarta.persistence.*;
import kr.cornerstone.enums.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseDateTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgId;

    private String password;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    private String googleId;

    private String appleId;

    private Boolean useFlag;
}
