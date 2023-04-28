package kr.cornerstone;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

//@SpringBootTest
class CornerStoneApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("프로젝트 암호화 테스트")
    public void testEncryption() {
        // 암호화 키 설정
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("");

        // 암호화할 문자열
        String input = "1234";

        // 암호화
        String encrypted = encryptor.encrypt(input);
        System.out.println("Encrypted: " + encrypted);

        // 복호화
        String decrypted = encryptor.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

}
