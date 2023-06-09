package kr.cornerstone.domain.user;

import jakarta.persistence.*;
import kr.cornerstone.global.enums.UseType;
import kr.cornerstone.global.model.BaseDateTime;
import kr.cornerstone.domain.user.enums.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class User extends BaseDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String birth;

    @Enumerated(EnumType.STRING)
    private AuthType authType;

    private String googleId;

    private String appleId;

    @ColumnDefault("true")
    private Boolean useFlag;

    private String refreshToken;

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void membershipCancellation() {
        this.useFlag = UseType.NOT_USE.getIsUse();
    }
}
